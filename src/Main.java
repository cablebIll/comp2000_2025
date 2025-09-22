import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel implements KeyListener {
      Stage stage = new Stage();
      public Canvas() {
        setPreferredSize(new Dimension(1024, 720));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
      }

      @Override
      public void keyPressed(KeyEvent e) {
        System.out.print(e.getKeyCode());
        stage.handleInput(e.getKeyCode()); 
      }

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

      @Override
      public void paint(Graphics g) {
        stage.paint(g, getMousePosition());
      }
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
  Timer timer = new Timer(1000 / 60, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      ((Canvas) getContentPane().getComponent(0)).stage.update();
      getContentPane().getComponent(0).repaint();

    }
  });
  timer.start();
}
}
