import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	protected int numberOfCards;
	protected ArrayList<Card> cards = new ArrayList<Card>();
    protected final String[] suit = { "Clubs", "Diamonds", "Hearts", "Spades" };
    //Note 11 = Jack, 12 = Queen, 13 = King, 14 = Ace; 
    protected final String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
    public Deck()
    {
    	numberOfCards = 0;
    }
    
	public Deck(int num)
	{
		numberOfCards = num;
		//Iterate through all suits
		for (int i = 0; i < suit.length; i++){
			//Iterate through all ranks
			for (int j = 0; j < rank.length; j++){
				cards.add(new Card(rank[j], suit[i]));
			}
		}
	}
	
	public int getNumberOfCards(){
		return numberOfCards;
	}
	
	public void setNumberOfCards(int num){
		this.numberOfCards = num;
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public void setCards(ArrayList<Card> c){
		this.cards = c;
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	//num is the number of people to split the deck to
	//sometimes you will have a remainder of cards left over
	public ArrayList<Deck> split(int num){
		int numberOfCardsInEachDeck = numberOfCards / num;
		int numberOfLeftoverCards = numberOfCards % num;
		
		ArrayList<Deck> splitDecks = new ArrayList<Deck>();
		
		//Splits the cards evenly into the required number of split decks
		for (int i = 0; i < num; i++)
		{
			Deck d = new Deck();
			
			for (int j = 0; j < numberOfCardsInEachDeck; j++){
				Card selectedCard = this.drawCard();
				d.addCard(selectedCard);
			}
			
			splitDecks.add(d);
		}
		
		//Handles any remainder cards and puts them in a deck at the end of the ArrayList
		Deck remainderDeck = new Deck();
		for (int i = 0; i < numberOfLeftoverCards; i++){
			Card remainderCard = this.drawCard();
			remainderDeck.addCard(remainderCard);
		}
		splitDecks.add(remainderDeck);
		
		return splitDecks;
	}
	
	public void addCard(Card c){
		cards.add(c);
		numberOfCards++;
	}
	
	public Card drawCard(){
		Card topCard = cards.get(0);
		cards.remove(0);
		numberOfCards--;
		
		return topCard;
	}
	
	public void burnCard(){
		cards.remove(0);
		numberOfCards--;
	}
	
	public void mergeDeck(Deck d){
		ArrayList<Card> newDeckCards = d.getCards();
		cards.addAll(newDeckCards);
		this.numberOfCards = this.numberOfCards + d.getNumberOfCards();
		d.setCards(new ArrayList<Card>());
		d.setNumberOfCards(0);
	}
	
	public Card selectRandomCard(){
		Random rand = new Random();
		int randomNumber = rand.nextInt(numberOfCards);
		
		return cards.get(randomNumber);
	}
	
	public void printDeck(){
		for (int i = 0; i < cards.size(); i++){
			System.out.println(cards.get(i));
		}
	}
}
