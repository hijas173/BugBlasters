public class FixBookControl {
	
	private FixBookUI UI; //'UI' changed to 'fixBookUi'
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE StAtE;
	
	private library LIB;
	private book Cur_Book;


	public FixBookControl() {
		this.LIB = LIB.INSTANCE();
		StAtE = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!StAtE.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = ui;//'UI' changed to 'fixBookUi'
		ui.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = CONTROL_STATE.READY;		
	}


	public void Book_scanned(int bookId) {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		Cur_Book = LIB.Book(bookId);
		
		if (Cur_Book == null) {
			fixBookUi.display("Invalid bookId");//'UI' changed to 'fixBookUi'
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			fixBookUi.display("Book has not been damaged");//'UI' changed to 'fixBookUi'
			return;
		}
		fixBookUi.display(Cur_Book.toString());//'UI' changed to 'fixBookUi'
		fixBookUi.Set_State(FixBookUI.UI_STATE.FIXING);//'UI' changed to 'fixBookUi'
		StAtE = CONTROL_STATE.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!StAtE.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			LIB.Repair_BOOK(Cur_Book);
		}
		Cur_Book = null;
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY);//'UI' changed to 'fixBookUi'
		StAtE = CONTROL_STATE.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.Set_State(FixBookUI.UI_STATE.COMPLETED);//'UI' changed to 'fixBookUi'		
	}






}
