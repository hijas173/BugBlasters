import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUi; //'UI' changed to 'borrowBookUi'
	
	private Library library; //'library' changed to 'Library','LIBRARY' changed to 'library' 
	private Member member; //'member' changed to 'Member','M' changed to 'member'
	private enum ControlState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};//'CONTROL_STATE' changed to 'ControlState', delete the white spance	
	private ControlState controlState;//'CONTROL_STATE' changed to 'ControlState','State' changed to 'controlState'
	
	private List<Book> pendingBooks; //'PENDING' changed to 'pendingBooks','book' changed to 'Book'
	private List<Loan> completedBooks;//'COMPLETED' changed to 'completedBooks','loan' changed to 'Loan'
	private Book book; //'book' changed to 'Book','BOOK' changed to 'book'
	
	
	public BorrowBookControl() {
		this.library = Library.getInstance();//'LIBRARY' changed to 'library','LIBRARY' changed to 'Library','INSTANCE()' changed to 'getInstance()'
		controlState = ControlState.INITIALISED;//'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!controlState.equals(ControlState.INITIALISED)) //'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUi = ui;//'UI' changed to 'borrowBookUi'
		borrowBookUi.Set_State(BorrowBookUI.UiState.READY);//'UI' changed to 'borrowBookUi','UI_STATE' changed to 'UiState'
		controlState = ControlState.READY;	//'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'	
	}

		
	public void cardSwiped(int memberId) {//'Swiped' changed to 'cardSwiped','MEMMER_ID' changed to 'memberId'
		if (!controlState.equals(ControlState.READY)) //'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = Library.getMember(memberId);//'M' changed to 'member','LIBRARY' changed to 'Library','MEMBER' changed to 'getMember','MEMMER_ID' changed to 'memberId'
		if (member == null) {//'M' changed to 'member'
			borrowBookUi.Display("Invalid memberId");//'UI' changed to 'borrowBookUi'
			return;
		}
		if (LIBRARY.memberCanBorrow(member)) {//'LIBRARY' changed to 'Library','MEMBER_CAN_BORROW' changed to 'memberCanBorrow','M' changed to 'member'
			pendingBooks = new ArrayList<>();//'PENDING' changed to 'pendingBooks'
			borrowBookUi.setState(BorrowBookUI.UiState.SCANNING);//'UI' changed to 'borrowBookUi','Set_State' changed to 'setState','UI_STATE' changed to 'UiState'
			controlState = ControlState.SCANNING; }//'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
		else 
		{
			borrowBookUi.Display("Member cannot borrow at this time");//'UI' changed to 'borrowBookUi'
			borrowBookUi.setState(BorrowBookUI.UiState.RESTRICTED); //'UI' changed to 'borrowBookUi','Set_State' changed to 'setState','UI_STATE' changed to 'UiState'
		}
	}//bracketing style keep consistent
	
	
	public void bookScanned(int bookId) {//'Scanned' changed to 'bookScanned'
		book = null;//'BOOK' changed to 'book'
		if (!controlState.equals(ControlState.SCANNING)) {//'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = Library.getBook(bookId);//'BOOK' changed to 'book','LIBRARY' changed to 'Library','Book' changed to 'getBook'
		if (book == null) {//'BOOK' changed to 'book'
			borrowBookUi.Display("Invalid bookId");//'UI' changed to 'borrowBookUi'
			return;
		}
		if (!book.idAvailable()) {//'BOOK' changed to 'book','AVAILABLE' changed to 'idAvailable'
			borrowBookUi.Display("Book cannot be borrowed");//'UI' changed to 'borrowBookUi'
			return;
		}
		pendingBooks.add(book);//'PENDING' changed to 'pendingBooks','BOOK' changed to 'book'
		for (book item : pendingBooks) {//'PENDING' changed to 'pendingBooks','B' changed to 'item'
			borrowBookUi.Display(item.toString());//'UI' changed to 'borrowBookUi','B' changed to 'item'
		}
		if (Library.loansRemainingForMember(member) - pendingBooks.size() == 0) {//'LIBRARY' changed to 'Library','Loans_Remaining_For_Member' changed to 'loansRemainingForMember','M' changed to 'member','PENDING' changed to 'pendingBooks'
			borrowBookUi.Display("Loan limit reached");//'UI' changed to 'borrowBookUi'
			borrowComplete();//'Complete' changed to 'borrowComplete'
		}
	}
	
	
	public void borrowComplete() {//'Complete' changed to 'borrowComplete'
		if (pendingBooks.size() == 0) {//'PENDING' changed to 'pendingBooks'
			borrowCancel();//'cancel' changed to 'borrowCancel'
		}
		else {
			borrowBookUi.Display("\nFinal Borrowing List");//'UI' changed to 'borrowBookUi'
			for (book item : pendingBooks) {//'PENDING' changed to 'pendingBooks','B' changed to 'item'
				borrowBookUi.Display(item.toString());//'UI' changed to 'borrowBookUi','B' changed to 'item'
			}
			completedBooks = new ArrayList<loan>();//'COMPLETED' changed to 'completedBooks'
			borrowBookUi.setState(BorrowBookUI.UiState.FINALISING);//'UI' changed to 'borrowBookUi','Set_State' changed to 'setState','UI_STATE' changed to 'UiState'
			controlState = ControlState.FINALISING;//'State' changed to 'controlState','CONTROL_STATE' changed to 'ControlState'
		}
	}


	public void Commit_LOans() {
		if (!controlState.equals(CONTROL_STATE.FINALISING)) {//'State' changed to 'controlState'
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = LIBRARY.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		UI.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		UI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		controlState = CONTROL_STATE.COMPLETED;//'State' changed to 'controlState'
	}

	
	public void borrowCancel() {//'cancel' changed to 'borrowCancel'
		UI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		controlState = CONTROL_STATE.CANCELLED;//'State' changed to 'controlState'
	}
	
	
}
