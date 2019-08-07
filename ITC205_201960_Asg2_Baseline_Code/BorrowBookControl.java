import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI UI; //'UI' changed to 'borrowBookUi'
	
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
		if (!controlState.equals(CONTROL_STATE.INITIALISED)) //'State' changed to 'controlState'
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.UI = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		controlState = CONTROL_STATE.READY;	//'State' changed to 'controlState'	
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!controlState.equals(CONTROL_STATE.READY)) //'State' changed to 'controlState'
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = LIBRARY.MEMBER(MEMMER_ID);
		if (M == null) {
			UI.Display("Invalid memberId");
			return;
		}
		if (LIBRARY.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			UI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			controlState = CONTROL_STATE.SCANNING; }//'State' changed to 'controlState'
		else 
		{
			UI.Display("Member cannot borrow at this time");
			UI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		BOOK = null;
		if (!controlState.equals(CONTROL_STATE.SCANNING)) {//'State' changed to 'controlState'
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = LIBRARY.Book(bookId);
		if (BOOK == null) {
			UI.Display("Invalid bookId");
			return;
		}
		if (!BOOK.AVAILABLE()) {
			UI.Display("Book cannot be borrowed");
			return;
		}
		PENDING.add(BOOK);
		for (book B : PENDING) {
			UI.Display(B.toString());
		}
		if (LIBRARY.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
			UI.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				UI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			UI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			controlState = CONTROL_STATE.FINALISING;//'State' changed to 'controlState'
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

	
	public void cancel() {
		UI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		controlState = CONTROL_STATE.CANCELLED;//'State' changed to 'controlState'
	}
	
	
}
