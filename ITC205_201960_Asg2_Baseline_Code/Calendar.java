import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;//'SeLf' changed to 'self'
	private static java.util.Calendar calendar;//'CaLeNdAr' changed to 'calendar'
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();//'CaLeNdAr' changed to 'calendar'
	}
	
	public static Calendar getInstance() {//'INSTANCE' changed to 'getInstance'
		if (self == null) {//'SeLf' changed to 'self'
			self = new Calendar();//'SeLf' changed to 'self'
		}
		return self;//'SeLf' changed to 'self'
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);//'CaLeNdAr' changed to 'calendar'		
	}
	
	public synchronized void setDate(Date date) {//'Set_dATE' changed to 'setDate'	
		try {
			calendar.setTime(date);//'CaLeNdAr' changed to 'calendar'	
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //modify the indentation,'CaLeNdAr' changed to 'calendar'
			calendar.set(java.util.Calendar.MINUTE, 0);  //modify the indentation,'CaLeNdAr' changed to 'calendar'
			calendar.set(java.util.Calendar.SECOND, 0);  //modify the indentation,'CaLeNdAr' changed to 'calendar'
			calendar.set(java.util.Calendar.MILLISECOND, 0);//modify the indentation,'CaLeNdAr' changed to 'calendar'
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date getDate() {//'Date' changed to 'getDate'
		try {
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);//modify the indentation,'CaLeNdAr' changed to 'calendar' 
			calendar.set(java.util.Calendar.MINUTE, 0);  //modify the indentation,'CaLeNdAr' changed to 'calendar'
			calendar.set(java.util.Calendar.SECOND, 0);  //modify the indentation,'CaLeNdAr' changed to 'calendar'
			calendar.set(java.util.Calendar.MILLISECOND, 0);//modify the indentation,'CaLeNdAr' changed to 'calendar'
			return calendar.getTime();//'CaLeNdAr' changed to 'calendar'
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		CaLeNdAr.add(java.util.Calendar.DATE, loanPeriod);
		Date DuEdAtE = CaLeNdAr.getTime();
		CaLeNdAr.setTime(NoW);
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
