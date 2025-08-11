import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
public class Cell {
    int x;
    int y;
    static int size = 35;

    // constructor
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Methods
    public void paint(Graphics g, Point mousePos){
        // highlight
        if(contains(mousePos)){
            g.setColor(Color.BLUE);
        }
        else{g.setColor(Color.WHITE);}

        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    // contains point

    public boolean contains(Point point){
        if (point != null){
            return point.x > x && point.x < x+size && point.y > y && point.y < y+size;
        }
        else {return false;}
    }
}
