
public class RankDecoder {
	Game game; //This is the game associated with the RandDecoder
	
	public RankDecoder(){
		
	}
	
	public RankDecoder(Game game){
		this.game = game;
	}
	
	//Depending on the game, cards have different values
	//For war: Jack = 11, Queen = 12, etc
	//For blackjack: All face cards = 10, Ace = 11
	
	//Accepts the rank of a card and decodes value
	public int decodeRank(String rank){
		Game g = this.game;
		
		if (g instanceof War){
			if (rank.equalsIgnoreCase("Jack")){
				return 11;
			}
			else if (rank.equalsIgnoreCase("Queen")){
				return 12;
			}
			else if (rank.equalsIgnoreCase("King")){
				return 13;
			}
			else if (rank.equalsIgnoreCase("Ace")){
				return 14;
			}
			else{
				return Integer.parseInt(rank);
			}
		}
		else if (g instanceof Blackjack){
			if (rank.equalsIgnoreCase("Jack") || rank.equalsIgnoreCase("Queen") || rank.equalsIgnoreCase("King")){
				return 10;
			}
			else if (rank.equalsIgnoreCase("Ace")){
				return 11;
			}
			else{
				return Integer.parseInt(rank);
			}
		}
		
		return 0;
	}
	
	//Accepts the card and decodes value of its rank
	public int decodeRank(Card c){
		Game g = this.game;
		String rank = c.getRank();
		
		if (g instanceof War){
			if (rank.equalsIgnoreCase("Jack")){
				return 11;
			}
			else if (rank.equalsIgnoreCase("Queen")){
				return 12;
			}
			else if (rank.equalsIgnoreCase("King")){
				return 13;
			}
			else if (rank.equalsIgnoreCase("Ace")){
				return 14;
			}
			else{
				return Integer.parseInt(rank);
			}
		}
		else if (g instanceof Blackjack){
			if (rank.equalsIgnoreCase("Jack") || rank.equalsIgnoreCase("Queen") || rank.equalsIgnoreCase("King")){
				return 10;
			}
			else if (rank.equalsIgnoreCase("Ace")){
				return 11;
			}
			else{
				return Integer.parseInt(rank);
			}
		}
		
		return 0;
	}
}

