import java.awt.*;

public class Cell {
    public static int size = 35;
    public int x;
    public int y;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g, Point MousePos){
        if (MousePos.x >= x && 
            MousePos.x <= x+35 && 
            MousePos.y >= y && 
            MousePos.y <= y+35){
            g.setColor(Color.GRAY);
        }
        else {g.setColor(Color.WHITE);}
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }
}
