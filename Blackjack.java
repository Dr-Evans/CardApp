import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack extends Game {
	private RankDecoder decoder = new RankDecoder(this);
	ArrayList<ArrayList<Card>> allPlayerCards; //Stores all player cards
	int[] playerValues; //Stores the values of player hands
	Deck deck;
	
	Blackjack(){
		super("Blackjack");
	}
	
	Blackjack(ArrayList<Player> players){
		super("Blackjack", players);	
	}
	
	public void playBlackjack(Deck deck){
		this.deck = deck;
		
		Player dealer = new Player("Dealer", 0);
		
		/**Deal out the cards*/
		//Allot cards to dealer
		ArrayList<Card> dealerCards = new ArrayList<Card>();
		
		Card dealerCard1 = deck.drawCard(); //1st card
		Card dealerCard2 = deck.drawCard(); //2nd card
		
		//Store cards in arraylist
		dealerCards.add(dealerCard1); 
		dealerCards.add(dealerCard2);
		
		//Show cards to users
		System.out.println(dealer + "'s cards:");
		System.out.println("NOT SHOWN");
		System.out.println(dealerCard2);
		System.out.println();
		
		//Calculate value of hand
		int dealerValue = decoder.decodeRank(dealerCard1) + decoder.decodeRank(dealerCard2);
		
		//Allot cards to each player
		allPlayerCards = new ArrayList<ArrayList<Card>>();
		playerValues = new int[this.getNumOfPlayers()]; //Stores the values of player hands
		
		for (int i = 0; i < this.getNumOfPlayers(); i++){
			ArrayList<Player> players = this.getPlayers();
			ArrayList<Card> playerCards = new ArrayList<Card>();
			
			Card playerCard1 = deck.drawCard(); //1st Card
			Card playerCard2 = deck.drawCard(); //2nd Card
			
			//Store cards in arraylist
			playerCards.add(playerCard1);
			playerCards.add(playerCard2);
			
			allPlayerCards.add(playerCards);
			
			//Show cards to users
			System.out.println(players.get(i) + "'s cards:");
			System.out.println(playerCard1);
			System.out.println(playerCard2);
			
			//Calculate value of hand and store in array
			playerValues[i] = decoder.decodeRank(playerCard1.getRank()) + decoder.decodeRank(playerCard2.getRank());
			
			if (playerValues[i] == 21){
				System.out.println("Blackjack!");
			}
		}
		
		System.out.println();
		
		/**Ask player 1, 2, 3.. etc to hit or stay and respond*/
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < this.getNumOfPlayers(); i++){
			boolean goNext = false;
			do {
				System.out.println("Player #" + players.get(i).getPlayerNum() + " - " + players.get(i));
				
				//Output cards to player
				String cardString = "";
				for (int j = 0; j < allPlayerCards.get(i).size(); j++){
					cardString += allPlayerCards.get(i).get(j);
					if (j != allPlayerCards.get(i).size() - 1){
						cardString += "\n";
					}
				}
				
				System.out.println(cardString);
				System.out.println("Hand value: " + playerValues[i]);
				System.out.println("Would you like to hit or stay? (H or S)");
				
				String answer = input.next();
				//Hit
				if(answer.equalsIgnoreCase("H")){
					hit(players.get(i));
					System.out.println("Hand value: " + playerValues[i]);
					
					//Check new hand value to see if the player busted.
					if (playerValues[i] > 21){
						System.out.println("BUST!");
						goNext = true;
					}
					else if (playerValues[i] == 21){
						System.out.println("You hit 21!");
						goNext = true;
					}
				}
				//Stay
				else if (answer.equals("S")){
					goNext = true;
				}
			} while (!goNext);
		}
		input.close();
		
		System.out.println();
		
		/**Dealer deals cards to itself. It must hit if =< 16*/
		boolean goNext = false;
		System.out.println("Dealer");
		System.out.println(dealerCards.get(0));
		System.out.println(dealerCards.get(1));
		System.out.println("Hand value: " + dealerValue);
		do{
			//Hit
			if (dealerValue <= 16){
				Card c = deck.drawCard();
				dealerCards.add(c);
				dealerValue += decoder.decodeRank(c);
				System.out.println("Dealer hits: " + c);
				System.out.println("Hand value: " + dealerValue);
			}
			//Stay
			else if (dealerValue >= 17 && dealerValue <= 21){
				System.out.println("Dealer stays.");
				goNext = true;
			}
			//Bust
			else{
				System.out.println("Dealer BUSTS!");
				goNext = true;
			}
		} while (!goNext);
		
		System.out.println();
		
		/** Compare player hand values with the dealer */
		System.out.println("Results:");
		for (int i = 0; i < players.size(); i++){
			//Player wins
			if (playerValues[i] > dealerValue && playerValues[i] <= 21){
				System.out.println(players.get(i) + " wins!");
			}
			//Player ties (push)
			else if (playerValues[i] == dealerValue && playerValues[i] <= 21){
				System.out.println(players.get(i) + " pushes.");
			}
			else if (playerValues[i] < dealerValue || playerValues[i] > 21){
				System.out.println(players.get(i) + " loses.");
			}
		}
	}
	
	//Only works for player and does not work for dealer
	public void hit(Player p){
		int num = p.getPlayerNum() - 1;
		Card c = deck.drawCard();
		
		System.out.println("Dealer deals: " + c);
		allPlayerCards.get(num).add(c);
		
		playerValues[num] += decoder.decodeRank(c);
	}
}
