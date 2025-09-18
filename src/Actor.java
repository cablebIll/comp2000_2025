import java.awt.*;

public abstract class Actor {
    //fields
    Color color;
    Cell loc;
    //constructors
    //methods
    public void paint(Graphics g, Color color){
        g.setColor(color);
        g.fillRect(loc.x+1, loc.y+1, loc.width-1, loc.height-1);
    }
}
