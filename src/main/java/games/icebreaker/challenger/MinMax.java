package games.icebreaker.challenger;

import java.util.Set;

public class MinMax {
    public static String bestMove(GameInterface game, int depth, String color) {
        String[] moves = game.possibleMoves(color).toArray(String[]::new);

        GameInterface newGame = new GameInterface(game);
        String bestMoveString = moves[0];
        newGame.move(moves[0]);
        float bestMove = min(newGame, depth - 1, color);

        for (int i = 1; i < moves.length; i++) {
            newGame = new GameInterface(game);
            newGame.move(moves[i]);
            float newMove = max(newGame, depth - 1, color);
            if (newMove > bestMove) {
                bestMove = newMove;
                bestMoveString = moves[i];
            }
        }
        return bestMoveString;
    }

    public static float max(GameInterface game, int depth, String color) {
        if (depth == 0) {
            return game.getCurrentScore(color);
        }
        if (game.getCurrentAllyScore(color) == 23) {
            return Float.POSITIVE_INFINITY;
        }
        if (game.getCurrentEnemyScore(color) == 23) {
            return Float.NEGATIVE_INFINITY;
        }

        Set<String> possibleMoves = game.possibleMoves(game.getCurrentPlayer());
        float bestMove = Float.NEGATIVE_INFINITY;
        for (String move : possibleMoves) {
            GameInterface newGame = new GameInterface(game);
            newGame.move(move);
            float newMove = min(newGame, depth - 1, color);
            bestMove = Math.max(newMove, bestMove);
        }
        return bestMove;
    }

    public static float min(GameInterface game, int depth, String color) {
        if (depth == 0) {
            return game.getCurrentScore(color);
        }
        if (game.getCurrentAllyScore(color) == 23) {
            return Float.POSITIVE_INFINITY;
        }
        if (game.getCurrentEnemyScore(color) == 23) {
            return Float.NEGATIVE_INFINITY;
        }

        Set<String> possibleMoves = game.possibleMoves(game.getCurrentPlayer());
        float bestMove = Float.POSITIVE_INFINITY;
        for (String move : possibleMoves) {
            GameInterface newGame = new GameInterface(game);
            newGame.move(move);
            float newMove = max(newGame, depth - 1, color);
            bestMove = Math.min(newMove, bestMove);
        }
        return bestMove;
    }
}
