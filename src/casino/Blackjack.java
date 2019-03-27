package casino;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Blackjack.");
		
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		while(playerMoney > 0) {
			System.out.println("You have $" + playerMoney + ", how much do you want to bet?");
			double playerBet = userInput.nextDouble();
			if(playerBet > playerMoney) {
				System.out.println("You can't bet more money than you have.");
				break;
			}
			
			boolean endRound = false;
			
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true) {
				System.out.println("Your hand:");
				System.out.println(playerDeck.toString());
				System.out.println("Your hand is valued at: " + playerDeck.cardsValue());
				
				System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
				
				System.out.println("Do you want to (1)Hit or (2)Stand?");
				int response = userInput.nextInt();
				
				if(response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("You drew a " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					
					if(playerDeck.cardsValue() > 21) {
						System.out.println("Bust.  Currently valued at: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
					}
				}
				
				if(response == 2) {
					break;
				}
			}
			
			System.out.println("Dealer Cards: " + dealerDeck.toString());
			
			if(dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			while(dealerDeck.cardsValue() < 17 && endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			
			System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());
			
			if((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer busts.  You win.");
				playerMoney += playerBet;
				endRound = true;
			}
			
			if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("Push.");
				endRound = true;
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			}
			
			else if(endRound == false) {
				System.out.println("You lose the hand.");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("End of hand.");
			
		}
		
		System.out.println("Game over.  You're out of money.");
		
	}

}
