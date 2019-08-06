import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { //'book' changed to "Book", because class name is to start with uppercase letter.
	
	private String title; //'TITLE' changed to 'title',variable name should start with lowercase
	private String author; //'AUTHOR' changed to 'author',variable name should start with lowercase
	private String callNo; //'CALLNO' changed to 'callNo',variable name should start with lowercase letter and to be in camelCase.
	private int bookId;  //'ID' changed to 'bookId', because variable name should be meaningful
	
	private enum State { 
		AVAILABLE, ON_LOAN, DAMAGED, RESERVED
	} //1. 'STATE' changed to 'State' 2.delete ';' 3. modify the block format
	private State state;//'STATE' changed to 'State', 'State' changed to 'state', State instance name should start with lowercase letter
	
	public Book()(); //add a default(no arg) constructor for Book class
	public Book(String author, String title, String callNo, int id) {//'book' changed to Book, constructor name should be the same with class name
		this.author = author;//'AUTHOR' changed to 'author'
		this.title = title;//'AUTHOR' changed to 'author'
		this.callNo = callNo;//'CALLNO' changed to 'callNo'
		this.bookId = id;//'ID' changed to 'bookId'
		this.state = State.AVAILABLE;//'State' changed to 'state'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(TITLE).append("\n")
		  .append("  Author: ").append(AUTHOR).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public Integer ID() {
		return ID;
	}

	public String TITLE() {
		return TITLE;
	}


	
	public boolean AVAILABLE() {
		return State == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return State == STATE.ON_LOAN;
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
