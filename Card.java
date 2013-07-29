
public class Card {
	protected String color;
	protected String rank;
	protected String suit;
	
	public Card()
	{
		this.color = "";
		this.suit = "";
		this.rank = "";
	}
	
	public Card(String rank, String suit){
		this.rank = rank;
		this.suit = suit;
		
		//Set color depending on suit
		if (suit.equalsIgnoreCase("Spades") || suit.equalsIgnoreCase("Clubs")){
			this.color = "Black";
		}
		else if (suit.equalsIgnoreCase("Diamonds") || suit.equalsIgnoreCase("Hearts")){
			this.color = "Red";
		}
		else {
			this.color = null;
		}
		
	}
	
	//Set-get methods
	public String getColor(){
		return color;
	}
	
	//Dont need a setColor()... if we know its suit we automatically know its color (ie: Spades are black)
	
	public String getSuit(){
		return suit;
	}
	
	public void setSuit(String suit){
		this.suit = suit;
	}
	
	public String getRank(){
		return rank;
	}
	
	public void setRank(String rank){
		this.rank = rank;
	}
	
	//Override toString()
	
	public String toString(){
		return rank + " of " + suit;
	}
	
	public int compareTo(Card c, RankDecoder decoder){
	
		int diff = decoder.decodeRank(this) - decoder.decodeRank(c);
		
	    return diff;
	}
}
