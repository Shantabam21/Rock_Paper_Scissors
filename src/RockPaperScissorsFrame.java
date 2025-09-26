import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl;
    JPanel statsPnl;
    JPanel resultPnl;
    JPanel bottomPnl;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton quitButton;

    ImageIcon rockIcon = new ImageIcon("src/rockImage.png");
    Image originalImg = rockIcon.getImage();
    Image scaleImg = originalImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon rockScaleIcon = new ImageIcon(scaleImg);

    ImageIcon paperIcon = new ImageIcon("src/paperImage.png");
    Image original = paperIcon.getImage();
    Image img = original.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon paperScaleIcon = new ImageIcon(img);


    ImageIcon scissorsIcon = new ImageIcon("src/scissorsImage.png");
    Image oGImg = scissorsIcon.getImage();
    Image sImg = oGImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon scissorsScaleIcon = new ImageIcon(sImg);

    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;

    public RockPaperScissorsFrame() {
        super("Rock Paper Scissors");
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createStatsPnl();

        createBottomPanel();

        add(mainPnl);
    }

    public void createStatsPnl() {
        statsPnl = new JPanel();
        JLabel playerLbl = new JLabel("Player Wins: \n");
        JLabel computerLbl = new JLabel("Computer Wins:\n");
        JLabel tieLbl = new JLabel("Ties: ");


        statsPnl.add(playerLbl);
        statsPnl.add(computerLbl);
        statsPnl.add(tieLbl);
        mainPnl.add(statsPnl,BorderLayout.WEST);
    }

    public void createBottomPanel() {
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1,4));
        bottomPnl.setBackground(Color.black);
        rockButton = new JButton("Rock", rockScaleIcon);
        rockButton.setHorizontalTextPosition(JButton.CENTER);
        rockButton.setVerticalTextPosition(JButton.BOTTOM);
        paperButton = new JButton("Paper", paperScaleIcon);
        paperButton.setHorizontalTextPosition(JButton.CENTER);
        paperButton.setVerticalTextPosition(JButton.BOTTOM);
        scissorsButton = new JButton("Scissors", scissorsScaleIcon);
        scissorsButton.setHorizontalTextPosition(JButton.CENTER);
        scissorsButton.setVerticalTextPosition(JButton.BOTTOM);
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        bottomPnl.add(rockButton);
        bottomPnl.add(paperButton);
        bottomPnl.add(scissorsButton);
        bottomPnl.add(quitButton);

        mainPnl.add(bottomPnl,BorderLayout.SOUTH);
    }


}
