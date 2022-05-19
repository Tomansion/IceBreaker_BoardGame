package games.icebreaker;

import java.util.Set;

import iialib.games.model.IChallenger;

import java.util.Scanner;

public class Human implements IChallenger {

	private final String name;

	public Human() {
		name = "TOTO";
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
		System.out.println("[CLIENT] Received move: " + move);
	}

	@Override
	public void otherPlay(String move) {
		System.out.println("[CLIENT] Received move: " + move);
	}

	@Override
	public String bestMove() {
		System.out.println("[CLIENT] Asking for move");
		String entreeClavier = new Scanner(System.in).nextLine();
		return entreeClavier;
	}

	@Override
	public String victory() {
		System.out.println("[CLIENT] Victory");
		return "Youpi !";
	}

	@Override
	public String defeat() {
		System.out.println("[CLIENT] Defeat");
		return "Snif...";
	}

	@Override
	public String tie() {
		System.out.println("[CLIENT] Tie");
		return "Ouf !";
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
		return null;
	}
}
