import javax.swing.*;
import java.awt.*;


public class RockPaperScissorsRunner {
    /**
     * Initializes the JFrame window and sets the dimensipns, and its visibility
     */
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) dimension.getWidth() * 3/4;
        int height = (int) dimension.getHeight() * 3/4;
        JFrame frame = new RockPaperScissorsFrame();
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
