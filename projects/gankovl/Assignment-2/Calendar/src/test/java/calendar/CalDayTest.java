package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import org.junit.Test;

import static org.junit.Assert.*;import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalDayTest {
    /**
     * Test that the gets methods work as expected.
     */
		
	//valid = false
	CalDay calday_1 = new CalDay();
	
	GregorianCalendar calendar = new GregorianCalendar(1, 1, 1);
	CalDay calday = new CalDay( calendar );
	
	
	@Test	//verifies validity of the created CalDay objects
	public void valid_test()	throws Throwable	{
		assertFalse( calday_1.isValid() );
		assertTrue ( calday.isValid() );
	}
	
	
	
	// some appointments
	Appt appt_1 = new Appt(14, 0, 1, 1, 1, "title_1", "desc_1", "email_1");
	Appt appt_2 = new Appt(3, 3, 1, "title_2", "desc_2", "email_2");
	Appt appt_3 = new Appt(5, 30, 1, 1, 1, "title_3", "desc_3", "email_3");
	Appt appt_4 = new Appt(3, 0, 1, 1, 1, "title_4", "desc_4", "email_4");
	
	@Test	//adding appts to iterator
	public void appt_test()		throws Throwable	{
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		appt_4.setValid();
		
		//adding appointments into the valid CalDay instance
		calday.addAppt( appt_1 );
		calday.addAppt( appt_2 );
		calday.addAppt( appt_3 );
		calday.addAppt( appt_4 );
		calday.addAppt( appt_1 );
		
		//testing iterators
		Iterator<?> iter = calday.iterator();
		assertNotEquals( null, iter );
		Iterator<?> iter_1 = calday_1.iterator();
		assertEquals( null, iter_1 );
		
		
		//testing string functions
		String test_str = calday.toString();
		assertNotEquals("", test_str);
		
		test_str = calday.getFullInfomrationApp(calday);
		assertNotEquals("", test_str);
		
	}
	
	
}