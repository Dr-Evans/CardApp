
public class OutOfCardsException extends Exception {
	static final long serialVersionUID = 100001;
	
	public OutOfCardsException(){
		super();
	}
	
	public OutOfCardsException(String message){
		super(message);
	}
}
