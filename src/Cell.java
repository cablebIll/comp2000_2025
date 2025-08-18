import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle{
  static int size = 35;

  // constructors
  public Cell(int x, int y) {
    super(x, y, size, size);
  }

  // methods
  public void paint(Graphics g, Point mousePos) {
    g.setColor(Color.WHITE);
      if(contains(mousePos)) {
        g.setColor(Color.GRAY);
      } 
    
      g.fillRect(x, y, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
      
  }

  public boolean contains(Point p) {
    if(p != null) {
      return(super.contains(p));

    } else {
      return false;
    }
  }
}
