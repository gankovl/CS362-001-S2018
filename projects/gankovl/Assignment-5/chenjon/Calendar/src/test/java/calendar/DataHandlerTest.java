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
	
	//make some appointments, initialize them
	Appt appt_1 = new Appt(14, 0, 1, 1, 1, "title_1", "desc_1", "email_1");
	Appt appt_2 = new Appt(5, 30, 1, 1, 1, "title_3", "desc_3", "email_3");
	Appt appt_3 = new Appt(3, 0, 1, 1, 1, "title_4", "desc_4", "email_4");
	Appt invalid = new Appt(3,  0, 45, 1, 1, "title",   "desc",   "email"  );
	        
//testing saving appointments	
    @Test
    public void saveAppt_1_1()	throws Throwable	{
    	appt_1.setValid(); 
    	DataHandler dataHandler = new DataHandler();
    	
    	boolean test_var = dataHandler.saveAppt(appt_1);
		assertTrue(test_var);
    }	
	@Test
	public void saveAppt_1_2()	throws Throwable	{
		appt_2.setValid();
		DataHandler dataHandler = new DataHandler("yes.xml", true);
		
		boolean test_var = dataHandler.saveAppt(appt_2);
		assertTrue(test_var);		
	}
	@Test
	public void saveAppt_1_3()	throws Throwable	{
		appt_3.setValid();
		
		//set a recurring appointment
		int[] recurDaysArr4={2,3,4};
	    appt_3.setRecurrence( recurDaysArr4, 1, 3, -1);
		DataHandler dataHandler = new DataHandler();
		
		boolean test_var = dataHandler.saveAppt(appt_3);
		assertTrue(test_var);   	
    }
	@Test
	public void saveAppt_1_4()	throws Throwable	{
		invalid.setValid();
		DataHandler dataHandler = new DataHandler();
		
		boolean test_var = dataHandler.saveAppt(invalid);
		assertFalse(test_var);   	
    }

	
//datahandler initialization
	@Test
	public void datahandler_test_1()	throws Throwable {
		DataHandler dataHandler = new DataHandler();
		
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		
		//set a recurring appointment
		int[] recurDaysArr4={2,3,4};
	    appt_3.setRecurrence( recurDaysArr4, 1, 3, -1);
		    
	    boolean test_var = dataHandler.saveAppt(appt_1);
	    test_var = dataHandler.saveAppt(appt_2);
	    test_var = dataHandler.saveAppt(appt_3);
		
		//set up time window for initializing the iterator
		GregorianCalendar today = new GregorianCalendar(1, 1, 1);
		GregorianCalendar tomorrow = new GregorianCalendar (1, 1, 2);
		
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		calDays = (LinkedList<CalDay>) dataHandler.getApptRange(today, tomorrow);
		
		for (int i = 0; i < calDays.size(); i++){
			CalDay calday= calDays.get(i);	
			String str= calday.getFullInfomrationApp(calday);
			
			LinkedList<Appt>  appts =calDays.get(i).getAppts();
		}		
	}
	@Test
	public void datahandler_test_2()	throws Throwable {
		DataHandler dataHandler2 = new DataHandler("calendar2.xml", false);
		

		GregorianCalendar today = new GregorianCalendar(1, 1, 1);
		GregorianCalendar tomorrow = new GregorianCalendar (1, 1, 2);
		
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		calDays = (LinkedList<CalDay>) dataHandler2.getApptRange(today, tomorrow);
		
		assertNotEquals(null, calDays);
	}
	@Test
	public void datahandler_test_3()	throws Throwable {
		DataHandler dataHandler3 = new DataHandler("calendar.xml");
		

		GregorianCalendar today = new GregorianCalendar(1, 1, 1);
		GregorianCalendar tomorrow = new GregorianCalendar (1, 1, 2);
		
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		calDays = (LinkedList<CalDay>) dataHandler3.getApptRange(today, tomorrow);
		
		assertNotEquals(null, calDays);
	}

	
//testing deletion	
	@Test
	public void deletion_test()	throws Throwable	{
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		
		//set a recurring appointment
		int[] recurDaysArr4={2,3,4};
	    appt_3.setRecurrence( recurDaysArr4, 1, 3, -1);
		    
		DataHandler dataHandler = new DataHandler();
		
		boolean test_var = dataHandler.saveAppt(appt_1);
	    test_var = dataHandler.saveAppt(appt_2);
	    test_var = dataHandler.saveAppt(appt_3);
		
		test_var = dataHandler.deleteAppt(appt_2);
		assertTrue(test_var);
	}
	@Test
	public void deletion_test_2()	throws Throwable	{
		appt_1.setValid();
		appt_2.setValid();
		invalid.setValid();
		    
		DataHandler dataHandler = new DataHandler();
		
		boolean test_var = dataHandler.saveAppt(appt_1);
	    test_var = dataHandler.saveAppt(appt_2);
	    test_var = dataHandler.saveAppt(invalid);
		
		test_var = dataHandler.deleteAppt(invalid);
		assertFalse(test_var);
	}
	@Test
	public void deletion_test_3()	throws Throwable	{
		appt_1.setValid();
		appt_2.setValid();
		invalid.setValid();
		    
		DataHandler dataHandler = new DataHandler("yes.xml", true);
		
		boolean test_var = dataHandler.saveAppt(appt_1);
	    test_var = dataHandler.saveAppt(appt_2);
	    test_var = dataHandler.saveAppt(invalid);
		
		test_var = dataHandler.deleteAppt(appt_1);
		assertTrue(test_var);
	}	
}