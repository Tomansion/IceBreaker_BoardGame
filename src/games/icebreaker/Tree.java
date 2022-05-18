package games.icebreaker;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Tree {
    private GameInterface game;
    private Tree[] children;
    private int depth;
    private String alliedPlayer;
    private String chosenMove;
    private float score;

    Tree(GameInterface game, int depth, String alliedPlayer, String chosenMove) {
        this.game = new GameInterface(game);
        this.depth = depth;
        this.alliedPlayer = alliedPlayer;
        this.chosenMove = chosenMove;
        String currentPlayer = game.getCurrentPlayer();

        if (depth > 0) {
            Set<String> possibleMoves = game.possibleMoves(currentPlayer);
            children = possibleMoves.stream()
                    .map(move -> {
                        GameInterface newGame = new GameInterface(game);
                        newGame.move(currentPlayer, move);
                        return new Tree(newGame, depth - 1, currentPlayer, move);
                    })
                    .toArray(Tree[]::new);
        } else {
            score = game.getCurrentScore();
        }
    }
}
