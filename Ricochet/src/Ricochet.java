import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Ricochet implements ActionListener, KeyListener {

    public static Ricochet ricochet;

    public static Renderer render;

    public static int width = 700, height = 450;

    public Ship ship;

    public boolean left, right;

    public ball ball;

    public int gameStatus = 0;

    public Ricochet(){

        Timer timer = new Timer(20, this);
        JFrame frame = new JFrame("Ricochet");
        render = new Renderer();


        frame.setSize(width, height);
        frame.setVisible(true);
        frame.add(render);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);

        start();

        timer.start();
    }

    public void start(){
        ship = new Ship(this);
        ball = new ball(this);
    }

    public void update(){

        if(left){
            ship.move(true);
        }

        if(right){
            ship.move(false);
        }
        ball.update(ship);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameStatus == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("RICOCHET" , width / 2 - 140, height / 2 - 100);

            g.setFont(new Font("Arial", 1, 25));
            g.drawString("Press SPACE to play!", width / 2 - 140, height /2);
        }

        if (gameStatus == 2 || gameStatus == 1 || gameStatus == 4) {
            ship.render(g);
            ball.render(g);
        }

        if (gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("PAUSED" , width / 2 - 110, height / 2 - 100);
        }

        if (gameStatus == 3){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("GAME OVER" , width / 2 - 160, height / 2 - 100);

            g.setFont(new Font("Arial", 1, 30));
            g.drawString("FINAL SCORE:      " + ship.score, width / 2 - 145, height / 2);

        }

        if (gameStatus == 4){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 10));
            g.drawString("ball y: " + String.valueOf(ball.y), 1, 10);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameStatus == 2 || gameStatus == 4) {
        update();
        }

        render.repaint();
    }

    public static void main(String[] args) {

        ricochet = new Ricochet();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();

        if (id == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (id == KeyEvent.VK_RIGHT) {
            right = true;
        }

        if (id == KeyEvent.VK_SPACE) {

            if (gameStatus == 0) {
                gameStatus = 2;
            }

            else if (gameStatus == 2) {
                gameStatus = 1;
            }

            else if (gameStatus == 1) {
                gameStatus = 2;
            }

        }

        if (id == KeyEvent.VK_1) {
                gameStatus = 4;

                if (gameStatus == 4) {
                    gameStatus = 2;
                }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int id = e.getKeyCode();

        if (id == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (id == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }
}
