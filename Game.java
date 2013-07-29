import java.util.ArrayList;


public class Game {
	protected String name;
	protected int numOfPlayers;
	protected ArrayList<Player> players;
	
	Game(){
		
	}
	
	Game(String name){
		this.name = name;
		this.numOfPlayers = 1;
	}
	
	Game(String name, ArrayList<Player> playerNames){
		this.name = name;
		this.numOfPlayers = playerNames.size();
		this.players = playerNames;
	}
	
	//Get-Set methods
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumOfPlayers(){
		return players.size();
	}
	
	public void setPlayer(ArrayList<Player> players){
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}
