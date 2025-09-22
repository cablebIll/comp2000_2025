import java.awt.Color;
import java.awt.Graphics;

public class Item extends GameObject {

  public Item(Cell inLoc) {
    this.loc = inLoc;
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.RED);
    int offset = 5;
    g.fillOval(loc.x + offset, loc.y + offset, Cell.size - (2 * offset), Cell.size - (2 * offset));
  }
}