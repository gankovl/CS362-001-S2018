package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	
	//appointment with start time, no recurrence
	Appt my_appt_1 = new Appt(12, 0, 13, 4, 2018, "title", "desc", "email");
	
	//appointment without start time, no recurrence
	Appt my_appt_2 = new Appt(13, 4, 2018, "title", "desc", "email");
	
	//appointment with corner case
	Appt corner = new Appt(1, 0, 1, 1, 1, "title", "desc", "email");
	
	//leap year test
	Appt my_appt_3 = new Appt(1, 0, 29, 2, 2016, "title", "desc", "email");
	
	
	 @Test	//checks isOn and hasTimeSet
	  public void test01()  throws Throwable  {
		 
     //testing hasTimeSet
		 boolean test_var = my_appt_1.hasTimeSet();
		 assertTrue(test_var);
		 
		 test_var = my_appt_2.hasTimeSet();
		 assertFalse(test_var);
		 
     //testing isOn
		 test_var = my_appt_1.isOn(1, 1, 1);
		 assertFalse(test_var);
		 test_var = my_appt_1.isOn(13, 4, 2018);
		 assertTrue(test_var);
		 
		 test_var = my_appt_2.isOn(1, 1, 1);
		 assertFalse(test_var);
		 test_var = my_appt_2.isOn(13, 4, 2018);
		 assertTrue(test_var);
		 
		 test_var = corner.isOn(1, 1, 1);
		 assertTrue(test_var);
		 test_var = corner.isOn(13, 4, 2018);
		 assertFalse(test_var);
	 }

	 @Test	//checks setValid
	  public void test02()  throws Throwable  {
		
		 //expected valid
		 my_appt_1.setValid();
		 boolean test_var = my_appt_1.getValid();
		 assertTrue(test_var);
		 
		 //expected valid
		 my_appt_2.setValid();
		 test_var = my_appt_2.getValid();
		 assertTrue(test_var);
		 
	  //testing branches	 
		 
		 
		 /*month out of range
		 my_appt_3.setStartMonth(0);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 my_appt_3.setStartMonth(1);*/
		 
		 //Day out of range
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 
		 //Year out of range
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 
		 //minutes out of range
		 my_appt_3.setStartMinute(0);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 //hour out of range
		 my_appt_3.setStartHour(0);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
	 }
	 
	 
	 @Test	//checks recurrence functions
	 public void test03() 	throws Throwable	{
		 
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 
		 int[] recur_2 = {0, 1, 2, 3, 4, 5, 6};
		 my_appt_2.setRecurrence( recur_2, 2, 10, 10);	//every month
		 
		 int[] recur_3 = {6};
		 corner.setRecurrence( recur_3, 3, 10, -1);	//every year, infinite
		 
		 /*
		 int[] recur_4 = {};
		 my_appt_3.setRecurrence( recur_4, 1, 10, 10);
		 */
		 
		 assertTrue( my_appt_1.isRecurring() );
		 assertTrue( my_appt_2.isRecurring() );
		 assertTrue( corner.isRecurring() );
		 assertFalse( my_appt_3.isRecurring() );
		 
	 }
	 
	 @Test 	//string functions
	 public void test04()	throws Throwable	{
		 
		 my_appt_1.setStartHour(12);
		 String test = my_appt_1.toString();
		 assertNotEquals( "",  test );
		 
		 my_appt_1.setStartHour(0);
		 test = my_appt_1.toString();
		 assertNotEquals( "",  test );
		 
		 /*
		 my_appt_2.setStartDay(40);
		 my_appt_2.setValid();
		 test = my_appt_2.toString();
		 assertEquals( "", test );
		 */
		 
		 my_appt_1.setTitle( null );
		 my_appt_1.setDescription( null );
		 
		 Appt test_appt = new Appt(1, 0, 1, 1, 1, "title", "desc", null);
	 }
}
