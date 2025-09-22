import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Enemy extends Actor {
  private Random rand = new Random();
  private int moveCooldown;
  private int moveTimer;

  public Enemy(Cell inLoc) {
    loc = inLoc;
    color = Color.YELLOW;
    moveCooldown = 30;
    moveTimer = moveCooldown;
    reDisplay();
  }

  public void update(Grid grid) {
  moveTimer--;
  if (moveTimer <= 0) {
    move(grid);
    moveTimer = moveCooldown;
  }
}
private void move(Grid grid) {
  int direction = rand.nextInt(4);
  char newCol = loc.col;
  int newRow = loc.row;

  switch (direction) {
    case 0: newRow--; break;
    case 1: newRow++; break;
    case 2: newCol--; break;
    case 3: newCol++; break;
  }

  Optional<Cell> targetCell = grid.cellAtColRow(newCol, newRow);
  if (targetCell.isPresent()) {
    updateLoc(targetCell.get());
  }
}

  @Override
public void reDisplay() {
  display = new ArrayList<Polygon>();
  Polygon ear1 = new Polygon();
  ear1.addPoint(loc.x + 5, loc.y + 5);
  ear1.addPoint(loc.x + 15, loc.y + 5);
  ear1.addPoint(loc.x + 5, loc.y + 15);
  Polygon ear2 = new Polygon();
  ear2.addPoint(loc.x + 20, loc.y + 5);
  ear2.addPoint(loc.x + 30, loc.y + 5);
  ear2.addPoint(loc.x + 30, loc.y + 15);
  Polygon face = new Polygon();
  face.addPoint(loc.x + 8, loc.y + 7);
  face.addPoint(loc.x + 27, loc.y + 7);
  face.addPoint(loc.x + 27, loc.y + 25);
  face.addPoint(loc.x + 8, loc.y + 25);
  display.add(face);
  display.add(ear1);
  display.add(ear2);
}
}
