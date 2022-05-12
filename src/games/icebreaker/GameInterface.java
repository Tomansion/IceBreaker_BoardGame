package games.icebreaker;

import java.util.Set;

public class GameInterface {
    boolean move(String player, String move) {
        return false;
    }

    int getScore(String player) {
        return 0;
    }

    Set<String> possibleMoves(String player) {
        return null;
    }
}
