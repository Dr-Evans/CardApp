import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class Main extends JFrame{
	static int numOfPlayers;
	ArrayList<Player> players;
	ArrayList<JTextField> jtfPlayerNames = new ArrayList<JTextField>();
	JButton jbNewDeck;
	JButton jbShowDeck;
	JButton jbShuffleDeck;
	JButton jbDrawCard;
	JButton jbBurnCard;
	JButton jbRandomCard;
	JButton jbAddCard;
	JButton jbSplitDeck;
	JButton jbMergeDeck;
	JButton jbPlayWar;
	JButton jbPlayBlackjack;
	Deck deck;
	
	public Main(String s){
		super(s);
		setLayout(new BorderLayout());
		deck = new Deck(52);
		System.out.println("The deck has been created.");
		
		//Prompt user to select how many players there are
		askHowManyPlayers();
		
		//deck.printDeck();
		
		/**pTop holds all the JTextFields and etc for player names*/
		JPanel pTop = new JPanel();
		
		for (int i = 0; i < numOfPlayers; i++){
			JPanel pPlayer = new JPanel();
			JLabel jlPlayerName = new JLabel("Player " + (i + 1));
			JTextField jtfPlayerName = new JTextField(10);
			
			pPlayer.add(jlPlayerName);
			pPlayer.add(jtfPlayerName);
			
			jtfPlayerNames.add(jtfPlayerName);
			
			pTop.add(pPlayer);
		}
		/*
		JPanel pPlayer1 = new JPanel();
		JPanel pPlayer2 = new JPanel();
		
		jlPlayerName1 = new JLabel("Player 1:");
		jtfPlayerName1 = new JTextField(15);
		jlPlayerName2 = new JLabel("Player 2:");
		jtfPlayerName2 = new JTextField(15);
		
		pPlayer1.add(jlPlayerName1);
		pPlayer1.add(jtfPlayerName1);
		pPlayer2.add(jlPlayerName2);
		pPlayer2.add(jtfPlayerName2);
		
		pTop.add(pPlayer1);
		pTop.add(pPlayer2);
		*/
		
		add(pTop, BorderLayout.NORTH);
		
		/**pCenter holds all the JButtons for actions*/
		JPanel pCenter = new JPanel();
		pCenter.setBorder(new TitledBorder("Deck Actions"));
		
		jbNewDeck = new JButton("New Deck"); 
		jbShowDeck = new JButton("Show Deck");
		jbShuffleDeck = new JButton("Shuffle");
		jbDrawCard = new JButton("Draw Card");
		jbBurnCard = new JButton("Burn Card");
		jbRandomCard = new JButton("Random Card");
		jbAddCard = new JButton("Add Card");
		jbSplitDeck = new JButton("Split Deck");
		jbMergeDeck = new JButton("Merge Deck");
		
		
		jbNewDeck.addActionListener(new NewDeckListener());
		jbShowDeck.addActionListener(new ShowDeckListener());
		jbShuffleDeck.addActionListener(new ShuffleListener());
		jbDrawCard.addActionListener(new DrawCardListener());
		jbBurnCard.addActionListener(new BurnCardListener());
		jbRandomCard.addActionListener(new RandomCardListener());
		jbAddCard.addActionListener(new AddCardListener());
		jbSplitDeck.addActionListener(new SplitDeckListener());
		jbMergeDeck.addActionListener(new MergeDeckListener());
		
		pCenter.add(jbNewDeck);
		pCenter.add(jbShowDeck);
		pCenter.add(jbShuffleDeck);
		pCenter.add(jbDrawCard);
		pCenter.add(jbBurnCard);
		pCenter.add(jbRandomCard);
		pCenter.add(jbAddCard);
		pCenter.add(jbSplitDeck);
		pCenter.add(jbMergeDeck);
		
		add(pCenter, BorderLayout.CENTER);
		
		/**pBottom holds all the JButtons for the Game buttons*/
		JPanel pBottom = new JPanel();
		pBottom.setBorder(new TitledBorder("Games"));
		
		jbPlayWar = new JButton("Play War");
		jbPlayBlackjack = new JButton("Play Blackjack");
		
		jbPlayWar.addActionListener(new PlayWarListener());
		jbPlayBlackjack.addActionListener(new PlayBlackjackListener());
		
		pBottom.add(jbPlayWar);
		pBottom.add(jbPlayBlackjack);
		
		add(pBottom, BorderLayout.SOUTH);
	}
	
	class NewDeckListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			deck = new Deck(52);
			System.out.println("A new deck has been created.");
		}
	}
	
	class ShowDeckListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("The deck is as follows:");
			deck.printDeck();
		}
	}
	
	class ShuffleListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			deck.shuffle();
			System.out.println("The deck has been shuffled.");
			//deck.printDeck();
		}
	}
	
	class DrawCardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Card topCard = deck.drawCard();
			System.out.println("TOP CARD: " + topCard);
		}
	}
	
	class BurnCardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			deck.burnCard();
			System.out.println("A card has been burned.");
		}
	}
	
	class RandomCardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Card randomCard = deck.selectRandomCard();
			System.out.println("RANDOM CARD: " + randomCard);
		}
	}
	
	class AddCardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			deck.addCard(new Card("4", "Diamonds"));
			System.out.println("4 of Diamonds has been added to the deck.");
		}
	}
	
	class SplitDeckListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Prompt user to ask how many smaller decks they would like to split this deck into
			int n = Integer.parseInt((String)JOptionPane.showInputDialog(null, "How many smaller decks would you like to split this deck into?", "Split Deck", JOptionPane.INFORMATION_MESSAGE));
			
			//Split deck into n smaller decks
			ArrayList<Deck> splitDecks = deck.split(n);
			
			//Display the n smaller decks
			for (int i = 0; i < splitDecks.size() - 1; i++){
				Deck selectedDeck = splitDecks.get(i);
				System.out.println("The following " + selectedDeck.getNumberOfCards() + " cards are in deck #" + (i + 1) + ":");
				selectedDeck.printDeck();
			}
			
			Deck remainderDeck = splitDecks.get(splitDecks.size() - 1);
			System.out.println("The remainder deck has the following " + remainderDeck.getNumberOfCards() + " cards:");
			remainderDeck.printDeck();
		}
	}
	
	class MergeDeckListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Deck newDeck = new Deck(52);
			System.out.println("The current deck has been merged with a new 52 card deck.");
			deck.mergeDeck(newDeck);
		}
	}
	
	class PlayWarListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			players = getPlayerNames();
			War w = new War(players);
			w.playWar(deck);
		}
	}
	
	class PlayBlackjackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			players = getPlayerNames();
			Blackjack bj = new Blackjack(players);
			bj.playBlackjack(deck);
		}
	}
	public static void askHowManyPlayers(){
		numOfPlayers = Integer.parseInt((String)JOptionPane.showInputDialog(null, "How many players?", "Startup", JOptionPane.INFORMATION_MESSAGE));
	}
	
	public ArrayList<Player> getPlayerNames(){
		ArrayList<Player> players = new ArrayList<Player>();
		
		for (int i = 0; i < numOfPlayers; i++){
			Player player = new Player((jtfPlayerNames.get(i)).getText(), (i + 1));
			players.add(player);
		}
		
		return players;
	}
	
	public static void main(String[] args){
		Main app = new Main("Playing with Decks");
		app.pack();
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
	}
}
