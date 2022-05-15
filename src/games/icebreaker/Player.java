package games.icebreaker;

import java.util.Objects;

public class Player {
    public int[][] _boats;
    public int _score;

    public Player(String name, int size) {
        _score = 0;
        if (Objects.equals(name, GameInterface.blackPlayer)){
            _boats = new int[][]{{size - 1, 0}, {0, size - 1}, {size - 1, 2 * (size - 1)}};
            return;
        } else if (Objects.equals(name, GameInterface.redPlayer)) {
            _boats = new int[][]{{0, 0}, {2 * (size - 1), size - 1}, {0, 2 * (size - 1)}};
            return;
        }
        System.out.println("Wrong name");
    }
}
