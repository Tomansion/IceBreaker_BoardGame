import java.util.Random;
import java.util.Set;

import games.icebreaker.GameInterface;

/*
*
*/
public class App {
    public static void main(String[] args) {
        // Playing a game with random players

        // Game interface
        GameInterface gi = new GameInterface();

        for (int i = 0; i < 1000; i++) {
            // Red turn
            Set<String> possibleMoves = gi.possibleMoves("RED");
            String move = getRandom(possibleMoves);
            System.out.print("Moving RED - to: " + move);
            gi.move("RED", move);
            System.out.println(" score  " + gi._red._score);

            if (gi._red._score == 23 ) {
                System.out.println("RED HAS WON !");
                break;
            }

            // Black turn
            Set<String> possibleMoves2 = gi.possibleMoves("BLACK");
            String move2 = getRandom(possibleMoves2);
            System.out.print("Moving BLACK to: " + move2);
            gi.move("BLACK", move2);
            System.out.println(" score  " + gi._black._score);

            if (gi._black._score == 23 ) {
                System.out.println("RED HAS WON !");
                break;
            }
        }

    }

    public static String getRandom(Set<String> array) {
        int rnd = new Random().nextInt(array.size());
        return array.toArray()[rnd].toString();
    }
}