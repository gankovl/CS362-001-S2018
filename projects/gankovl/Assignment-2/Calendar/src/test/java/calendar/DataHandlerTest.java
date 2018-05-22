package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  DataHandler class.
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
		DataHandler dataHandler2 = new DataHandler("calendar2.xml", false);
		
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
		boolean test_var = appt_1.getValid();
		assertTrue(test_var);
		
		appt_2.setValid();
		test_var = appt_2.getValid();
		assertTrue(test_var);
		
		appt_3.setValid();
		test_var = appt_3.getValid();
		assertTrue(test_var);
		
		appt_4.setValid();
		test_var = appt_4.getValid();
		assertTrue(test_var);
		
		
		//save appointments to datahandler
		test_var = dataHandler.saveAppt(appt_1);
		assertTrue(test_var);
		
		test_var = dataHandler.saveAppt(appt_2);
		assertTrue(test_var);		
		
		test_var = dataHandler.saveAppt(appt_3);
		assertTrue(test_var);
		
		test_var = dataHandler2.saveAppt(appt_4);
		//assertFalse(test_var);
		
		
		//set up time window for initializing the iterator
		GregorianCalendar today = new GregorianCalendar(1, 1, 1);
		GregorianCalendar tomorrow = new GregorianCalendar (1, 1, 2);
		
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		LinkedList<CalDay> calDays_2 = new LinkedList<CalDay>();
		
		try{
			calDays_2 = (LinkedList<CalDay>) dataHandler.getApptRange(tomorrow, today);	
		} catch( Exception e ) {
			calDays = (LinkedList<CalDay>) dataHandler.getApptRange(today,tomorrow);	
		}
			
		for (int i = 0; i < calDays.size(); i++){
			CalDay calday= calDays.get(i);	
			String str= calday.getFullInfomrationApp(calday);

			LinkedList<Appt>  appts =calDays.get(i).getAppts();
		}
		
		//delete an appointment
		test_var = dataHandler.deleteAppt(appt_2);
		assertTrue(test_var);
		/*
		appt_3.setStartDay(32);
		appt_3.setValid();
		test_var = dataHandler2.deleteAppt(appt_3);
		assertFalse(test_var);
		*/
		
	}
}