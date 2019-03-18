package casino;

public class Blackjack {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Blackjack.");
		
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		
		System.out.println(playingDeck);

	}

}
