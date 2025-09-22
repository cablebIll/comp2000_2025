import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor extends GameObject {
  Color color;
  List<Polygon> display;

  @Override
  public void paint(Graphics g) {
    for (Polygon p : display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.BLACK);
      g.drawPolygon(p);
    }
  }

  public abstract void reDisplay();

  public void updateLoc(Cell newLoc) {
    this.loc = newLoc; 
    reDisplay();
  }
}