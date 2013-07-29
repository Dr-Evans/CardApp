
public class Player {
	protected String name;
	protected int playerNum;
	
	public Player(){
		
	}
	
	public Player(String name){
		this.name = name;
		playerNum = 0;
	}
	
	public Player(String name, int playerNum){
		this.name = name;
		this.playerNum = playerNum;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPlayerNum(int playerNum){
		this.playerNum = playerNum;
	}
	
	public int getPlayerNum(){
		return playerNum;
	}
	
	public String toString(){
		return name;
	}
}
