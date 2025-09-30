/**
 * Initializes the implementation of the Strategy interface
 * */

public class Cheat implements Strategy{
    /**
     * Uses the player's move to pick a certain move for the computer
     * that will beat the player
     * @param playerMove - The player's move (R,P, or S)
     * @return - Computer's chosen move
     */
    @Override
    public String getMove(String playerMove)
    {
        String computerMove = "";
        switch (playerMove)
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
