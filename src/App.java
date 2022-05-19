import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import com.sun.tools.jconsole.JConsoleContext;
import games.icebreaker.*;

/*
*
*/
public class App {
    public static void main(String[] args) {
        // Playing a game with random players

        // Game interface
        int count = 0;

        for (int h = 0 ; h < 100 ; h++) {
            GameInterface gi = new GameInterface();
            for (int i = 0; i < 1000; i++) {
                // Red turn
                Set<String> possibleMoves = gi.possibleMoves("RED");
                String move = getRandom(possibleMoves);
//                System.out.print("Moving RED - to: " + move);
                gi.move(move);
//                System.out.println(" score  " + gi._red._score);

                if (gi._red._score == 23) {
                    System.out.println("RED HAS WON !");
                    break;
                }

                // Black turn$
                String bestMove = MinMax.bestMove(gi, 3, "BLACK");
//                System.out.println("Recommended move: " + bestMove.move + " with score: " + bestMove.score);
                String move2 = bestMove;
//                System.out.print("Moving BLACK to: " + move2);
                gi.move(move2);
//                System.out.println(" score  " + gi._black._score);


                if (gi._black._score == 23) {
                    System.out.println("BLACK HAS WON !");
                    count++;
                    break;
                }
            }
        }
        System.out.println("Number of games black won: " + count);
    }

    public static String getRandom(Set<String> array) {
        int rnd = new Random().nextInt(array.size());
        return array.toArray()[rnd].toString();
    }
}