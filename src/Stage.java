import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<GameObject> gameObjects;

  public Stage() {
    grid = new Grid();
    gameObjects = new ArrayList<GameObject>();
    
    gameObjects.add(new Player(grid.cellAtColRow('A', 0).get()));
    gameObjects.add(new Dog(grid.cellAtColRow('P', 0).get()));
    gameObjects.add(new Dog(grid.cellAtColRow('J', 12).get()));
    gameObjects.add(new Item(grid.cellAtColRow('F', 5).get()));
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    for (GameObject obj : gameObjects) {
      obj.paint(g);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if (underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }

  public void handleInput(int keyCode) {
    Player player = (Player) gameObjects.get(0);
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
    }
  }
}