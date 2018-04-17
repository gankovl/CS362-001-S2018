package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataHandlerTest {
	
	
	@Test
	public void datahandler_test()	throws Throwable {
		
		DataHandler dataHandler = new DataHandler();
		DataHandler dataHandler2 = new DataHandler("calendar2.xml",true);
		
		//make some appointments, initialize them
		Appt appt_1 = new Appt(14, 0, 1, 1, 1, "title_1", "desc_1", "email_1");
		Appt appt_2 = new Appt(3, 3, 1, "title_2", "desc_2", "email_2");
		Appt appt_3 = new Appt(5, 30, 1, 1, 1, "title_3", "desc_3", "email_3");
		Appt appt_4 = new Appt(3, 0, 1, 1, 1, "title_4", "desc_4", "email_4");
		
		//set a recurring appointment
		int[] recurDaysArr4={2,3,4};
        appt_4.setRecurrence( recurDaysArr4, 1, 3, -1);
        
        //set appointment validity
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		appt_4.setValid();
		
		//save appointments to datahandler
		dataHandler.saveAppt(appt_1);
		dataHandler.saveAppt(appt_2);
		dataHandler.saveAppt(appt_3);
		dataHandler.saveAppt(appt_4);
		
		
		
		GregorianCalendar today = new GregorianCalendar(1, 1, 1);
		GregorianCalendar tomorrow = new GregorianCalendar (1, 1, 2);
		
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		calDays = (LinkedList<CalDay>) dataHandler.getApptRange(today,tomorrow);	

		for (int i = 0; i < calDays.size(); i++){
			CalDay calday= calDays.get(i);	
			String str= calday.getFullInfomrationApp(calday);

			LinkedList<Appt>  appts =calDays.get(i).getAppts();
		}
		
		//delete an appointment
		dataHandler.deleteAppt(appt_2);
		
	}
}