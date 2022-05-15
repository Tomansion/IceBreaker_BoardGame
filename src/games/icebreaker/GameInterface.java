package games.icebreaker;

import java.util.Set;

public class GameInterface {
    public static int SIZE = 5;

    private Player red = new Player("red", SIZE);
    private Player black = new Player("black", SIZE);
    private Board board = new Board(SIZE);
    private String _currentPlayer = "red";


    boolean move(String player, String move) {
        String[] moveArray = move.split("-");
        int[] origin = stringToCoord(moveArray[0]);
        int[] dest = stringToCoord(moveArray[1]);

        if (!player.equals(_currentPlayer)) {
            System.out.println("It is not " + player + "'s turn.");
            return false;
        }
        if (_currentPlayer.equals("red")) {
            for(int i = 0; i < 3; i++) {
                if(red._boats[i] == origin) {
                    red._boats[i] = dest;
                    break;
                }
            }
        } else {
            for(int i = 0; i < 3; i++) {
                if(black._boats[i] == origin) {
                    black._boats[i] = dest;
                    break;
                }
            }
        }
        
        _currentPlayer = (_currentPlayer.equals("red")) ? "black" : "red";
        return true;
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return 'A1'
     */
    public String coordToString(int x, int y) {
        return (char) (y + 'A') + "" + (x + 1);
    }

    /**
     * @param move 'A1'
     * @return [x, y]
     */
    public int[] stringToCoord(String move) {
        return new int[]{move.charAt(0) - 'A', move.charAt(1) - '1'};
    }

    int getScore(String player) {
        return player.equals("red") ? red._score : black._score;
    }

    Set<String> possibleMoves(String player) {
        return null;
    }
}
