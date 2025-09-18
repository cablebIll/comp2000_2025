import java.awt.*;

public class Stage {
    //fields
    Grid grid;
    Actor cat;
    Actor dog;
    Actor bird;
    //constructors
public Stage(){
    grid = new Grid();
    cat = new Cat(grid.cellAtLoc(0, 0));
    dog = new Dog(grid.cellAtLoc(12, 12));
    bird = new Bird(grid.cellAtLoc(4, 4));
}
    //methods
    void paint(Graphics g, Point MousePos){
    grid.paint(g, MousePos);
    cat.paint(g, cat.color);
    dog.paint(g, dog.color);
    bird.paint(g, bird.color);
    }
}
 