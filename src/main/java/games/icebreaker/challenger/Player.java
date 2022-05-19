package games.icebreaker.challenger;

import java.util.Objects;

public class Player {
    public int[][] _boats;
    public int _score;

    // copy constructor
    public Player(Player player) {
        _score = player._score;
        _boats = new int[3][2];
        for (int i = 0; i < 3; i++) {
            _boats[i][0] = player._boats[i][0];
            _boats[i][1] = player._boats[i][1];
        }
    }

    public Player(String name, int size) {
        _score = 0;
        if (Objects.equals(name, GameInterface.blackPlayer)){
            _boats = new int[][]{{size - 1, 0}, {2 * (size - 1), 2 * (size - 1)}, {0, size - 1}};
            return;
        } else if (Objects.equals(name, GameInterface.redPlayer)) {
            _boats = new int[][]{{0, 0}, {2 * (size - 1), size - 1}, {size - 1, 2 * (size - 1)}};
            return;
        }
        System.out.println("Wrong name");
    }
}
