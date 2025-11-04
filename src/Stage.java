import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Stage implements Observer {
  Grid grid;
  Player player;
  List<Enemy> enemies;
  List<Item> items;
  private int score;
  private Random rand = new Random();
  private int freezeTimer;
  private boolean gameOver;
  private final ConcurrentLinkedQueue<WeatherData> weatherDataQueue = new ConcurrentLinkedQueue<>();

  public Stage() {
    grid = new Grid();
    enemies = new ArrayList<>();
    items = new ArrayList<>();
    score = 0;
    gameOver = false;

    player = new Player(grid.cellAtColRow('A', 0).get());

    enemies.add(new Enemy(grid.cellAtColRow('P', 0).get()));
    enemies.add(new Enemy(grid.cellAtColRow('J', 12).get()));
    
    items.add(new Objective(grid.cellAtColRow('F', 5).get()));
    items.add(new PowerUp(grid.cellAtColRow('K', 10).get()));
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    
    for (Item item : items) {
      item.paint(g);
    }

    for (Enemy enemy : enemies) {
      enemy.paint(g);
    }

    player.paint(g);

    g.drawString("Score: " + score, 740, 60);
    if (gameOver){
      g.drawString("Game Over, final score: " + score, 740, 100);
    }
    
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if (underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }

  public void handleInput(int keyCode) {
    if (gameOver){return;}
    Cell currentLoc = player.loc;
    char newCol = currentLoc.col;
    int newRow = currentLoc.row;

    switch (keyCode) {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        newCol = (char) (newCol + 1);
        break;

      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        newRow -= 1;
        break;

      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        newCol = (char) (newCol - 1);
        break;

      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        newRow += 1;
        break;

      default:
        return;
    }
    Optional<Cell> targetCell = grid.cellAtColRow(newCol, newRow);

    if (targetCell.isPresent()) {
      player.updateLoc(targetCell.get());
      checkForInteractions();
    }
  }
  
  private void checkForInteractions() {
  for (Enemy enemy : enemies) {
    if (player.loc == enemy.loc) {
      gameOver = true;
    }
  }

  List<Item> itemsToRemove = new ArrayList<>();
  int objectivesToSpawn = 0;

  for (Item item : items) {
    if (player.loc == item.loc) {
      if (item instanceof Objective) {
        itemsToRemove.add(item);
        score++;
        spawnEnemy();
        objectivesToSpawn++;
      } else if (item instanceof PowerUp) {
        freezeTimer = 300;
        itemsToRemove.add(item);
      }
    }
  }

  items.removeAll(itemsToRemove);

  for (int i = 0; i < objectivesToSpawn; i++) {
    spawnObjective();
  }
}
  
  private Cell findEmptyCell() {
  Cell emptyCell;
  do {
    int randCol = rand.nextInt(grid.cells.length);
    int randRow = rand.nextInt(grid.cells[0].length);
    emptyCell = grid.cellAtColRow(randCol, randRow).get();
  } while (isCellOccupied(emptyCell));
  return emptyCell;
}

private void spawnEnemy() {
  Cell spawnCell = findEmptyCell();
  enemies.add(new Enemy(spawnCell));
}

private void spawnObjective() {
  Cell spawnCell = findEmptyCell();
  items.add(new Objective(spawnCell));
}

private boolean isCellOccupied(Cell cell) {
  if (player.loc == cell) {
    return true;
  }
  for (Enemy enemy : enemies) {
    if (enemy.loc == cell) {
      return true;
    }
  }
  for (Item item : items) {
    if (item.loc == cell) {
      return true;
    }
  }
  return false;
}

  public void update() {
    if (!gameOver){
      if (freezeTimer <= 0){
        for (Enemy enemy : enemies) {
          enemy.update(grid);
        }
      }
    }
    if (freezeTimer > 0) {
      freezeTimer--;
    }
    updateCellWeatherEffects();
    processWeatherData();
  }

  private void updateCellWeatherEffects() {
    final double FADE_RATE = 0.001;

    grid.stream().forEach(cell -> {
      if (cell.getTemperature() > 0) { 
        cell.setTemperature(Math.max(0.0, cell.getTemperature() - FADE_RATE));
      }
    });
  }

  private void processWeatherData() {
    WeatherData data;
    while ((data = weatherDataQueue.poll()) != null) {
      final WeatherData currentData = data;
      int gridCols = grid.cells.length;
      int gridRows = grid.cells[0].length;
      int col = currentData.getX() + gridCols / 2;
      int row = currentData.getY() + gridRows / 2;

      grid.cellAtColRow(col, row).ifPresent(cell -> {
        switch (currentData.getAttribute()) {
          case "temp":
            cell.setTemperature(currentData.getValue());
            break;
        }
      });
    }
  }

  @Override
  public void update(WeatherData data) {
    weatherDataQueue.add(data);
  }
}
