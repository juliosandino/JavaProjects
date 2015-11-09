import javax.swing.*;
import java.awt.*;


public class Renderer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Ricochet.ricochet.render((Graphics2D) g);
    }
}
