import java.awt.Graphics;
import java.awt.Point;

public abstract class CellDecorator extends Cell {
    protected Cell decoratedCell;

    public CellDecorator(Cell decoratedCell) {
        super(decoratedCell.col, decoratedCell.row, decoratedCell.x, decoratedCell.y);
        this.decoratedCell = decoratedCell;
    }

    @Override
    public void paint(Graphics g, Point mousePos) {
        decoratedCell.paint(g, mousePos);
    }
}