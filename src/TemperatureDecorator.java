import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TemperatureDecorator extends CellDecorator {
    private double temperature;

    public TemperatureDecorator(Cell decoratedCell, double temperature) {
        super(decoratedCell);
        this.temperature = temperature;
    }

    @Override
    public void paint(Graphics g, Point mousePos) {
        super.paint(g, mousePos);
        int alpha = (int) (temperature * 100);
        g.setColor(new Color(255, 0, 0, Math.min(alpha, 255)));
        g.fillRect(x, y, width, height);
    }
}