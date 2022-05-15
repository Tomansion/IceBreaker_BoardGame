package games.icebreaker;

import java.util.Set;

public class GameInterface {
    public static int SIZE = 5;

    private Player red = new Player("red", SIZE);
    private Player black = new Player("black", SIZE);
    private Board board = new Board(SIZE);
    private String _currentPlayer = "red";


    boolean move(String player, String move) {
        int origin[] = stringToCoord(move.charAt(0) + move.charAt(1) + "");
        int dest[] = stringToCoord(move.charAt(3) + move.charAt(4) + "");
        if (!player.equals(_currentPlayer)) {
            System.out.println("It is not " + player + "'s turn.");
            return false;
        }
        
        _currentPlayer = (_currentPlayer.equals("red")) ? "black" : "red";
        return true;
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return 'A1'
     */
    String coordToString(int x, int y) {
        return (char) (y + 'A') + "" + (x + 1);
    }

    /**
     * @param move 'A1'
     * @return [x, y]
     */
    int[] stringToCoord(String move) {
        return new int[]{move.charAt(0) - 'A', move.charAt(1) - '1' + 1};
    }

    int getScore(String player) {
        return 0;
    }

    Set<String> possibleMoves(String player) {
        return null;
    }
}
