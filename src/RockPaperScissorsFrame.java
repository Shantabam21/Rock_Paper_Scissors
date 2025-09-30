import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class creates the GUI for the Rock-Paper-Scissors game.
 * It manages the panels, buttons, and game logic such as tracking wins,
 * ties, and using different computer strategies.
 */
public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl;
    JPanel statsPnl;
    JPanel resultPnl;
    JPanel bottomPnl;

    JLabel playerLabel;
    JLabel computerLabel;
    JLabel tieLabel;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton quitButton;

    ImageIcon rockIcon = new ImageIcon("src/closedFist.png");
    Image originalImg = rockIcon.getImage();
    Image scaleImg = originalImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon rockScaleIcon = new ImageIcon(scaleImg);

    ImageIcon paperIcon = new ImageIcon("src/openHand.png");
    Image original = paperIcon.getImage();
    Image img = original.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon paperScaleIcon = new ImageIcon(img);


    ImageIcon scissorsIcon = new ImageIcon("src/scissor.png");
    Image oGImg = scissorsIcon.getImage();
    Image sImg = oGImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon scissorsScaleIcon = new ImageIcon(sImg);

    JTextArea displayTa;
    JScrollPane displayScroll;

    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;

    int rockUsed = 0;
    int paperUsed = 0;
    int scissorsUsed = 0;

    String lastUsedPlayer = "";

    /**
     * Initializes the RockPaperScissorsFrame, sets up the frame title,
     * and creates the stats, result, and bottom panels.
     */
    public RockPaperScissorsFrame() {
        super("Rock Paper Scissors");
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createStatsPnl();

        createResultPnl();

        createBottomPanel();



        add(mainPnl);
        //mainPnl.setBackground(new Color(165,200,210));
    }


    /**
     * Strategy that returns the computer move based on the player's least used move.
     */
    class LeastUsed implements Strategy {
        public String getMove(String playerMove) {
            String computerMove = "";
            if (rockUsed == 1 && (scissorsUsed== 0 && paperUsed == 0)) {
                computerMove = "P";
            }
            else if (scissorsUsed == 1 && (rockUsed == 0 && paperUsed == 0)) {
                computerMove = "R";
            }
            else if (paperUsed == 1 && (scissorsUsed == 0 && rockUsed == 0)) {
                computerMove = "S";
            } else {
                int lowestUsed = Math.min(rockUsed, Math.min(paperUsed, scissorsUsed));

                if (lowestUsed == rockUsed) {
                    computerMove = "P";
                } else if (lowestUsed == paperUsed) {
                    computerMove = "S";
                } else {
                    computerMove = "R";
                }
            }

            return computerMove;
        }
    }

    /**
     * Strategy that returns the computer move based on the player's most used move.
     */
    class MostUsed implements Strategy {
        public String getMove(String playerMove) {
            String computerMove = "";
            if (rockUsed == 1 && (scissorsUsed== 0 && paperUsed == 0)) {
                computerMove = "P";
            }
            else if (scissorsUsed == 1 && (rockUsed == 0 && paperUsed == 0)) {
                computerMove = "R";
            }
            else if (paperUsed == 1 && (scissorsUsed == 0 && rockUsed == 0)) {
                computerMove = "S";
            } else {
                int lowestUsed = Math.max(rockUsed, Math.max(paperUsed, scissorsUsed));

                if (lowestUsed == rockUsed) {
                    computerMove = "P";
                } else if (lowestUsed == paperUsed) {
                    computerMove = "S";
                } else {
                    computerMove = "R";
                }
            }

            return computerMove;
        }
    }

    /**
     * Strategy that returns the computer move based on the player's last used move.
     */
    class LastUsed implements Strategy {
        public String getMove(String playerMove) {
            String computerMove = "";
            switch (lastUsedPlayer)
            {
                case "R":
                    computerMove = "P";
                    break;
                case "P":
                    computerMove = "S";
                    break;
                case "S":
                    computerMove = "R";
                    break;
                default:
                    computerMove = "X";
                    break;
            }
            return computerMove;
        }

    }

    /**
     * Determines the computer's move using different strategies based on random chance,
     * updates the display with the chosen strategy and result.
     * @param playerMove the player's chosen move as a String ("R", "P", or "S")
     */
    public void returnComputerMove(String playerMove) {
        int randomNum = (int) (Math.random() * 100) + 1;
        String computerMove = "";

        if (randomNum <= 10) {
            Cheat cheat = new Cheat();
            computerMove = cheat.getMove(playerMove);
            setDisplayText(computerMove, "Cheat", playerMove);
        } else if (randomNum <= 30) {
            LeastUsed lowestUsed = new LeastUsed();
            computerMove = lowestUsed.getMove(playerMove);
            setDisplayText(computerMove, "Lowest Used", playerMove);

        } else if (randomNum <= 50) {
            MostUsed mostUsed = new MostUsed();
            computerMove = mostUsed.getMove(playerMove);
            setDisplayText(computerMove, "Most Used", playerMove);
        } else if (randomNum <= 70) {
            if ((rockUsed == 1 && (scissorsUsed== 0 && paperUsed == 0)
            || (scissorsUsed == 1 && (rockUsed == 0 && paperUsed == 0)
            || (paperUsed == 1 && (scissorsUsed == 0 && rockUsed == 0)))))
             {
                 Random random = new Random();
                 computerMove = random.getMove(playerMove);
                 setDisplayText(computerMove, "Random", playerMove);

            }else {
                LastUsed lastUsed = new LastUsed();
                computerMove = lastUsed.getMove(playerMove);
                setDisplayText(computerMove, "Last Used", playerMove);
            }
        } else {
            Random random = new Random();
            computerMove = random.getMove(playerMove);
            setDisplayText(computerMove, "Random", playerMove);
        }
    }


    /**
     * Creates and lays out the statistics panel showing player wins,
     * computer wins, and ties.
     */
    public void createStatsPnl() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(3,1));
        playerLabel = new JLabel("Player Wins: " + playerWins + "\n" );
        playerLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        computerLabel = new JLabel("Computer Wins: " + computerWins + "\n");
        computerLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        tieLabel= new JLabel("Ties: " + ties + "\n");
        tieLabel.setFont(new Font("Times New Roman",Font.BOLD,25));



        statsPnl.add(playerLabel);
        statsPnl.add(computerLabel);
        statsPnl.add(tieLabel);
        mainPnl.add(statsPnl,BorderLayout.WEST);
        statsPnl.setBackground(Color.white);
    }

    /**
     * Creates the result panel containing a scrollable text area to display
     * game outcomes.
     */
    public void createResultPnl() {
        resultPnl = new JPanel();

         displayTa = new JTextArea(30,40);
        displayTa.setEditable(false);
         displayScroll = new JScrollPane(displayTa);
        resultPnl.add(displayScroll);
        mainPnl.add(resultPnl,BorderLayout.CENTER);


    }

    /**
     * Creates the bottom panel with buttons for Rock, Paper, Scissors, and Quit,
     * and sets up their action listeners.
     */
    public void createBottomPanel() {
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1,4));
        bottomPnl.setBackground(Color.black);

        rockButton = new JButton("Rock", rockScaleIcon);
        rockButton.setHorizontalTextPosition(JButton.CENTER);
        rockButton.setVerticalTextPosition(JButton.BOTTOM);
        rockButton.addActionListener((ActionEvent ae) -> {
            String playerMove = "R";
            rockUsed++;
            returnComputerMove(playerMove);
            lastUsedPlayer = playerMove;
        });

        paperButton = new JButton("Paper", paperScaleIcon);
        paperButton.setHorizontalTextPosition(JButton.CENTER);
        paperButton.setVerticalTextPosition(JButton.BOTTOM);
        paperButton.addActionListener((ActionEvent e) -> {
            String playerMove = "P";
            paperUsed++;
            returnComputerMove(playerMove);
            lastUsedPlayer = playerMove;
        });

        scissorsButton = new JButton("Scissors", scissorsScaleIcon);
        scissorsButton.setHorizontalTextPosition(JButton.CENTER);
        scissorsButton.setVerticalTextPosition(JButton.BOTTOM);
        scissorsButton.addActionListener((ActionEvent ae) -> {
            String playerMove = "S";
            scissorsUsed++;
            returnComputerMove(playerMove);
            lastUsedPlayer = playerMove;
        });

        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        bottomPnl.add(rockButton);
        bottomPnl.add(paperButton);
        bottomPnl.add(scissorsButton);
        bottomPnl.add(quitButton);

        mainPnl.add(bottomPnl,BorderLayout.SOUTH);
    }

    /**
     * Updates the display text area and statistics labels based on the outcome of a round
     * which inlcudes the moves played, the winner, and the strategy used by the computer
     * @param computerMove - The computer's chosen move ("R", "S", or "P")
     * @param strategy - The strategy that was randomly chosen for the computer to use
     * @param playerMove - The player's chosen move as a string ("R", "S", or "P")
     */
    public void setDisplayText(String computerMove, String strategy, String playerMove) {
        if (computerMove.equals(playerMove)) {
            if (playerMove.equals("S")) {
                displayTa.append("Scissors cuts Scissors " +  "(Tie! " + "Computer: "  + strategy+ ")\n");
            } else if (playerMove.equals("R")) {
                displayTa.append("Rock breaks Rock " +  "(Tie! Computer: " +strategy  + ")\n");
            } else {
                displayTa.append("Paper covers Paper " +  "(Tie! Computer: " + strategy + ")\n");
            }
            ties += 1;
            tieLabel.setText("Ties: " + ties + "\n");
        } else if ((playerMove.equals("S") && computerMove.equals("P")) ||
                (playerMove.equals("R") && computerMove.equals("S")) ||
                (playerMove.equals("P") && computerMove.equals("R"))
        )
        {
            if (playerMove.equals("S")) {
                displayTa.append("Scissors cuts Paper " +  "(Player Wins! " + "Computer: "  + strategy+ ")\n");
            } else if (playerMove.equals("R")) {
                displayTa.append("Rock breaks Scissors " +  "(Player Wins! Computer: " +strategy  + ")\n");
            } else {
                displayTa.append("Paper covers Rock " +  "(Player Wins! Computer: " + strategy + ")\n");
            }

            playerWins += 1;
            playerLabel.setText("Player Wins: " + playerWins + "\n");
        } else {
            if (computerMove.equals("S")) {
                displayTa.append("Scissors cuts paper (Computer Wins! Computer: " +  strategy + ")\n");
            } else if (computerMove.equals("R")) {
                displayTa.append("Rock breaks Scissors (Computer Wins! Computer: " +  strategy + ")\n");
            } else {
                displayTa.append("Paper covers Rock (Computer Wins! Computer: " +  strategy + ")\n");
            }


            computerWins += 1;
            computerLabel.setText("Computer Wins: " + computerWins + "\n");
        }
    }


}
