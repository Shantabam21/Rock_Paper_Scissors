public class Random implements Strategy {
    public String getMove(String playerMove) {
        String[] arr = {"R","P","S"};
        return arr[(int)(Math.random() * arr.length)];
    }

}
