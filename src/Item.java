import java.awt.Color;
import java.awt.Graphics;

public abstract class Item extends GameObject {
  protected Color color;

  public Item(Cell inLoc) {
    this.loc = inLoc;
  }

  @Override
  public void paint(Graphics g) {
  int offset = 5;
  g.setColor(this.color); 
  g.fillOval(loc.x + offset, loc.y + offset, Cell.size - (2 * offset), Cell.size - (2 * offset));
  
  g.setColor(Color.BLACK);
  g.drawOval(loc.x + offset, loc.y + offset, Cell.size - (2 * offset), Cell.size - (2 * offset));
    }
}