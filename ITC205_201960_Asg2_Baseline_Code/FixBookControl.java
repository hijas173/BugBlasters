public class FixBookControl {
	
	private FixBookUI UI; //'UI' changed to 'fixBookUi'
	private enum ControlState { INITIALISED, READY, FIXING };//'CONTROL_STATE' changed to 'ControlState'
	private CONTROL_STATE StAtE;
	
	private library LIB;
	private book Cur_Book;


	public FixBookControl() {
		this.LIB = LIB.INSTANCE();
		StAtE = ControlState.INITIALISED;//'CONTROL_STATE' changed to 'ControlState'
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!StAtE.equals(ControlState.INITIALISED)) {//'CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = ui;//'UI' changed to 'fixBookUi'
		ui.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = ControlState.READY;	//'CONTROL_STATE' changed to 'ControlState'	
	}


	public void Book_scanned(int bookId) {
		if (!StAtE.equals(ControlState.READY)) {//'CONTROL_STATE' changed to 'ControlState'
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
		StAtE = ControlState.FIXING;	//'CONTROL_STATE' changed to 'ControlState'	
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!StAtE.equals(ControlState.FIXING)) {//'CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			LIB.Repair_BOOK(Cur_Book);
		}
		Cur_Book = null;
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY);//'UI' changed to 'fixBookUi'
		StAtE = ControlState.READY;	//'CONTROL_STATE' changed to 'ControlState'	
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(ControlState.READY)) {//'CONTROL_STATE' changed to 'ControlState'
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.Set_State(FixBookUI.UI_STATE.COMPLETED);//'UI' changed to 'fixBookUi'		
	}






}
