import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  Player player;
  List<Enemy> enemies;
  List<Item> items;

  public Stage() {
    grid = new Grid();
    enemies = new ArrayList<>();
    items = new ArrayList<>();

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
    
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if (underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }

  public void handleInput(int keyCode) {
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
        System.out.println("Collided with an enemy!");
      }
    }

    List<Item> itemsToRemove = new ArrayList<>();
    for (Item item : items) {
      if (player.loc == item.loc) {
        if (item instanceof Objective) {
          System.out.println("Objective collected!");
          itemsToRemove.add(item);
        } else if (item instanceof PowerUp) {
          System.out.println("PowerUp collected!");
          itemsToRemove.add(item);
        }
      }
    }
    items.removeAll(itemsToRemove);
  }
  public void update() {
  for (Enemy enemy : enemies) {
    enemy.update(grid);
  }
}
}