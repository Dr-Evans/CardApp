import java.util.ArrayList;


public class War extends Game{
	private RankDecoder decoder = new RankDecoder(this);
	
	War(){
		super("War");
	}
	
	War(ArrayList<Player> players){
		super("War", players);
	}
	
	/* In War, a 52 card deck is split between two players. The players simultaneously reveal the top card from their deck.
	 * The two cards battle each other, in general the the higher rank card wins (ex: a King beats a 4, a 3 loses to an Ace).
	 * Exception: A 2 BEATS an Ace, but loses to all other cards. -- THIS EXCEPTION IS NOT IN THE CODE YET!
	 * If there is a tie, war is declared! Both players ante three cards from the top of their deck face down and reveal a fourth one to battle again.
	 * When you win a battle, you take yours and the other player's card and put it in a side pile.
	 * When you run out of cards from your original pile, shuffle the side pile and use that.
	 * The player who runs out of cards first loses! 
	 * */
	
	public void playWar(Deck deck){
		//Get player names
		ArrayList<Player> players = this.getPlayers();
		Player player1 = players.get(0);
		Player player2 = players.get(1);
		
		ArrayList<Deck> playDecks = deck.split(2);
		ArrayList<Deck> sideDecks = new ArrayList<Deck>();
		ArrayList<Deck> anteDecks = new ArrayList<Deck>();
			
		Deck playDeck1 = playDecks.get(0);
		Deck playDeck2 = playDecks.get(1);
		Deck sideDeck1 = new Deck();
		Deck sideDeck2 = new Deck();
		sideDecks.add(sideDeck1);
		sideDecks.add(sideDeck2);
		Deck anteDeck1 = new Deck();
		Deck anteDeck2 = new Deck();
		anteDecks.add(anteDeck1);
		anteDecks.add(anteDeck2);
			
		//Let's play!
		boolean gameOver = false;
		int battleNum = 1;
		//int counter = 0;
		gameOverLoop:
		do{
			//Reveal top cards from play deck
			Card battleCard1 = playDeck1.drawCard();
			Card battleCard2 = playDeck2.drawCard();
			
			//Battle!
			boolean battleWinner = false;
			boolean ante = false;
			do{
				//Show the battle number
				System.out.println("Battle #" + battleNum);
				//Show the cards
				System.out.println(player1 + " shows: " + battleCard1);
				System.out.println(player2 + " shows: " + battleCard2);
				//Player 1 card is greater than Player 2 card
				if (battleCard1.compareTo(battleCard2, decoder) > 0){
					System.out.println("***" + player1 + " wins the battle.***");
					sideDeck1.addCard(battleCard1); //Player 1 takes card back
					sideDeck1.addCard(battleCard2); //Player 2 loses card to Player 1
					//Player 1 takes all the ante cards if there are any
					if (ante){
						System.out.println(player1 + " won the following ante cards: ");
						anteDeck1.printDeck();
						anteDeck2.printDeck();
						sideDeck1.mergeDeck(anteDeck1);
						sideDeck1.mergeDeck(anteDeck2);
						ante = false;
					}
					battleWinner = true;
				}
				//Player 2 card is greater than Player 1 card
				else if (battleCard1.compareTo(battleCard2 , decoder) < 0){
					System.out.println("***" + player2 + " wins the battle.***");
					sideDeck2.addCard(battleCard2); //Player 2 takes card back
					sideDeck2.addCard(battleCard1); //Player 1 loses card to Player 2
					//Player 2 takes all the ante cards if there are any
					if (ante){
						System.out.println(player2 + " won the following ante cards: ");
						anteDeck1.printDeck();
						anteDeck2.printDeck();
						sideDeck2.mergeDeck(anteDeck2);
						sideDeck2.mergeDeck(anteDeck1);
						ante = false;
					}
					battleWinner = true;
				}
				//Player 1 card is equal to Player 2 card - WAR!!!
				else if (battleCard1.compareTo(battleCard2, decoder) == 0){
					System.out.println("***There is a tie. War is declared!***");
					
					//Try to ante 3 cards, if either play deck has less than 3 cards we must handle them accordingly
					int numOfCardsToAnte;
					//If both decks have 4 or more cards, ante 3
					if (playDeck1.getNumberOfCards() >= 4 && playDeck2.getNumberOfCards() >= 4){
						numOfCardsToAnte = 3;
						}
					//If either deck has 3 cards, ante only 2
					else if (playDeck1.getNumberOfCards() >= 3 && playDeck2.getNumberOfCards() >= 3){
						numOfCardsToAnte = 2;
					}
					//If either deck has 2 cards, ante only 1
					else if (playDeck1.getNumberOfCards() >= 2 && playDeck2.getNumberOfCards() >= 2){
						numOfCardsToAnte = 1;
					}
					//If either deck has 1 card, ante 0
					else if (playDeck1.getNumberOfCards() >= 1 && playDeck2.getNumberOfCards() >= 1){
						numOfCardsToAnte = 0;
					}
					//If either deck has 0 cards, ante 0.
					//Try to shuffle sideDeck into playDeck. If there is no sideDeck, player loses.
					else{
						numOfCardsToAnte = 0;
						if (playDeck1.getNumberOfCards() == 0 && !(sideDeck1.getNumberOfCards() == 0)){
							playDeck1.mergeDeck(sideDeck1);
						}
						else if (playDeck1.getNumberOfCards() == 0 && sideDeck1.getNumberOfCards() == 0){
							System.out.println(player2 + " wins!");
							gameOver = true;
							break gameOverLoop;
						}
						
						if (playDeck2.getNumberOfCards() == 0 && !(sideDeck2.getNumberOfCards() == 0)){
							playDeck2.mergeDeck(sideDeck2);
						}
						else if (playDeck2.getNumberOfCards() == 0 && sideDeck2.getNumberOfCards() == 0){
							System.out.println(player1 + " wins!");
							gameOver = true;
							break gameOverLoop;
						}
					}
					
					//System.out.println("Number of cards in Player 1's deck: " + playDeck1.getNumberOfCards());
					//System.out.println("Number of cards in Player 2's deck: " + playDeck2.getNumberOfCards());
					
					System.out.println(player1 + " places " + numOfCardsToAnte + " cards face down.");
					System.out.println(player2 + " places " + numOfCardsToAnte + " cards face down.");
					//Ante three cards in the "ante decks"
					for (int i = 0; i < numOfCardsToAnte; i++){
						Card anteCard1 = playDeck1.drawCard();
						anteDeck1.addCard(anteCard1);
						
						Card anteCard2 = playDeck2.drawCard();
						anteDeck2.addCard(anteCard2);
					}
					
					anteDeck1.addCard(battleCard1);
					anteDeck2.addCard(battleCard2);
					
					battleCard1 = playDeck1.drawCard();
					battleCard2 = playDeck2.drawCard();
					ante = true;
				}
				battleNum++;
			} while (!battleWinner);
			
			System.out.println("Number of cards in " + player1 + "'s play deck: " + playDeck1.getNumberOfCards());
			System.out.println("Number of cards in " + player1 + "'s side deck: " + sideDeck1.getNumberOfCards());
			System.out.println("Number of cards in " + player1 + "'s ante deck: " + anteDeck1.getNumberOfCards());
			System.out.println("Number of cards in " + player2 + "'s play deck: " + playDeck2.getNumberOfCards());
			System.out.println("Number of cards in " + player2 + "'s side deck: " + sideDeck2.getNumberOfCards());
			System.out.println("Number of cards in " + player2 + "'s ante deck: " + anteDeck2.getNumberOfCards());
			
			//If playDeck for either player is out of cards, make the sideDeck the playDeck and shuffle
			//If there is no cards in the sideDeck at this time, the player loses!
			if (playDeck1.getNumberOfCards() == 0 && !(sideDeck1.getNumberOfCards() == 0)){
				System.out.println("~~~~~~~~~~~~~~~~~~~" + player1 + " shuffles his/her deck.");
				playDeck1.mergeDeck(sideDeck1);
				playDeck1.shuffle();
			}
			else if(playDeck1.getNumberOfCards() == 0 && sideDeck1.getNumberOfCards() == 0){
				System.out.println(player2 + " wins!");
				gameOver = true;
			}
			
			if (playDeck2.getNumberOfCards() == 0 && !(sideDeck2.getNumberOfCards() == 0)){
				System.out.println("~~~~~~~~~~~~~~~~~~~" + player2 + " shuffles his/her deck.");
				playDeck2.mergeDeck(sideDeck2);
				playDeck2.shuffle();
			}
			else if(playDeck2.getNumberOfCards() == 0 && sideDeck2.getNumberOfCards() == 0){
				System.out.println(player1 + " wins!");
				gameOver = true;
			}
		} while (!gameOver);
	}
}
