import java.util.ArrayList;


public class GoFish extends Game{

	GoFish(){
		super("Go Fish");
	}
	
	GoFish(ArrayList<Player> players){
		super("Go Fish", players);
	}
	
	//Players play go fish with each other
	public void play(Deck deck){
		
	}
}
