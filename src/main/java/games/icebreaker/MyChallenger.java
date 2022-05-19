package games.icebreaker;
import java.util.Random;
import games.icebreaker.challenger.GameInterface;
import games.icebreaker.challenger.ScoredMove;
import games.icebreaker.challenger.MinMax;

import java.util.Set;

import iialib.games.model.IChallenger;


public class MyChallenger implements IChallenger {

	private final String name = "PSTAR_Turing";

	private GameInterface gi;

	public MyChallenger() {
		this.gi = new GameInterface();
	}

	@Override
	public String teamName() {
		return name;
	}

	@Override
	public void setRole(String role) {
		System.out.println("[CLIENT] Received role: " + role);
	}

	@Override
	public void iPlay(String move) {
		System.out.println("[CLIENT] confirmed move " + move + " (" + gi.getCurrentPlayer() + ")");
		gi.move(gi.originalToNew(move));
	}

	@Override
	public void otherPlay(String move) {
		System.out.println("[CLIENT] Received move: from other " + move + " (" + gi.getCurrentPlayer() + ")");
		gi.move(gi.originalToNew(move));
	}

	@Override
	public String bestMove() {
		// System.out.println("[CLIENT] Asking for move, we are " + gi.getCurrentPlayer());
		// Set<String> possibleMoves = gi.possibleMoves(gi.getCurrentPlayer());
		// return gi.moveToOriginal(getRandom(possibleMoves));
		String bestMove = MinMax.bestMove(gi, 4, gi.getCurrentPlayer());
		return gi.moveToOriginal(bestMove);
	}

    public static String getRandom(Set<String> array) {
        int rnd = new Random().nextInt(array.size());
        return array.toArray()[rnd].toString();
    }

	@Override
	public String victory() {
		System.out.println("[CLIENT] Victory");
		return "EZ";
	}

	@Override
	public String defeat() {
		System.out.println("[CLIENT] Defeat");
		return "Rigged... The opposant was using a bot...";
	}

	@Override
	public String tie() {
		System.out.println("[CLIENT] Tie");
		return "Finaly, a worthy opponent...";
	}

	@Override
	public String boardToString() {
		System.out.println("[CLIENT] Board to string");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoardFromFile(String fileName) {
		System.out.println("[CLIENT] Received board from file: " + fileName);
	}

	@Override
	public Set<String> possibleMoves(String role) {
		System.out.println("[CLIENT] Asking for possible moves with role: " + role);
		return gi.possibleMoves(role);
	}
}
