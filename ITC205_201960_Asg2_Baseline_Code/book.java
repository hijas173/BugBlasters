import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { //'book' changed to "Book", because class name is to start with uppercase letter.
	
	private String title; //'TITLE' changed to 'title',variable name should start with lowercase
	private String author; //'AUTHOR' changed to 'author',variable name should start with lowercase
	private String callNo; //'CALLNO' changed to 'callNo',variable name should start with lowercase letter and to be in camelCase.
	private int bookId;  //'ID' changed to 'bookId', because variable name should be meaningful
	
	private enum State { 
		AVAILABLE, ONLOAN, DAMAGED, RESERVED  
	} //1. 'STATE' changed to 'State' 2.delete ';' 3. modify the block format 4.'ON_LOAN' changed to 'ONLOAN' 
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

	public int getBookId() {//'Integer' changed to 'int', 'ID' changed to 'getBookId'
		return bookId;// 'ID' changed to 'bookId'
	}

	public String getTitle() {//'TITLE' changed to 'getTitle'
		return title;// 'TITLE' changed to 'title'
	}
	
	public boolean idAvailable() {//'AVAILABLE' changed to 'idAvailable'
		return state == State.AVAILABLE;// 'TITLE' changed to 'title','State' changed to 'state',,'STATE' changed to 'State'
	}

	
	public boolean isOnLoan() {//'On_loan' changed to 'isOnLoan'
		return state == State.ONLOAN;//'State' changed to 'state','STATE' changed to 'State','ON_LOAN' changed to 'ONLOAN' 
	}

	
	public boolean isDamaged() {//'IS_Damaged' changed to 'isDamaged'
		return state == State.DAMAGED;//'State' changed to 'state','STATE' changed to 'State'
	}

	
	public void borrowBooks() {//'Borrow' changed to 'borrowBooks' method name should be meaningful, and start with a lowercase letter and to be in camelBack.
		if (state.equals(State.AVAILABLE)) {//'State' changed to 'state','STATE' changed to 'State'
			state = State.ONLOAN;//'State' changed to 'state','ON_LOAN' changed to 'ONLOAN' ,'STATE' changed to 'State'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));//'State' changed to 'state'
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
