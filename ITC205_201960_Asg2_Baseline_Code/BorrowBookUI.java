import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UIState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};//'UI_STATE' changed to'UIState'

	private BorrowBookControl borrowBookControl;//'CONTROL' changed to'borrowBookControl'
	private Scanner input;
	private UiState uiState;//'UI_STATE' changed to'UiState','StaTe' changed to'uiState',

	
	public BorrowBookUI(BorrowBookControl control) {
		this.borrowBookControl = control;//'CONTROL' changed to'borrowBookControl'
		input = new Scanner(System.in);
		uiState = UiState.INITIALISED;//'StaTe' changed to'uiState','UI_STATE' changed to'UiState'
		control.setUI(this);
	}

	
	private String getInput(String prompt) {//'input' changed to'getInput'
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void outputInfo(Object object) {//'output' changed to'outputInfo'
		System.out.println(object);
	}
	
			
	public void setState(UiState state) {//'Set_State' changed to'setState','UI_STATE' changed to'UiState','STATE' changed to'state'
		this.StaTe = state;//'StaTe' changed to'state','STATE' changed to'state'
	}

	
	public void run() {
		outputInfo("Borrow Book Use Case UI\n");//'output' changed to'outputInfo'
		
		while (true) {
			
			switch (state) {//'StaTe' changed to'state'			
			
			case CANCELLED:
				outputInfo("Borrowing Cancelled");//'output' changed to'outputInfo'
				return;

				
			case READY:
				String strMember = input("Swipe member card (press <enter> to cancel): ");//'MEM_STR' changed to'strMember'
				if (strMember.length() == 0) {//'MEM_STR' changed to'strMember'
					borrowBookControl.borrowCancel();//'CONTROL' changed to'borrowBookControl','cancel' changed to'borrowCancel'
					break;
				}
				try {
					int memberId = Integer.valueOf(strMember).intValue();//'Member_ID' changed to'memberId','MEM_STR' changed to'strMember'
					borrowBookControl.cardSwiped(memberId);//'CONTROL' changed to'borrowBookControl','Swiped' changed to 'cardSwiped','Member_ID' changed to'memberId'
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					CONTROL.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}
