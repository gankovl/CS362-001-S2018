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
	Appt my_appt_1 = new Appt(12, 11, 13, 4, 2018, "title", "desc", "email");
	
	//appointment without start time, no recurrence
	Appt my_appt_2 = new Appt(13, 4, 2018, "title", "desc", "email");
	
	//appointment with corner case
	Appt corner = new Appt(1, 0, 1, 1, 1, "title", "desc", "email");
	
	//leap year test
	Appt my_appt_3 = new Appt(1, 0, 29, 2, 2016, "title", "desc", "email");
	
	
	
//checks TimeSet	
	 @Test
	  public void TimeSet_1()  throws Throwable  {
		 boolean test_var = my_appt_1.hasTimeSet();
		 assertTrue(test_var);
	 }
	 
	 
	 @Test
	 public void TimeSet_2()  throws Throwable  {
		 boolean test_var = my_appt_2.hasTimeSet();
		 assertFalse(test_var);
	 }
		

//checks isOn
	 @Test
	 public void IsOn_1()	throws Throwable	{
     //testing isOn
		 assertFalse( my_appt_1.isOn(1, 1, 1) );
	 }
	 
	 @Test
	 public void IsOn_1_5()	throws Throwable	{
		 assertTrue(  my_appt_1.isOn(13, 4, 2018) );
	 }
	 
	 //appt 1's set date is 13, 4, 2018
	 @Test
	 public void isOn_2()  throws Throwable  {			/* FAILS! (prior to bug fix)*/
		 assertFalse( my_appt_1.isOn(13, 1, 1) );
	 }
	 
	 @Test
	 public void isOn_3()  throws Throwable  {			/* FAILS! (prior to bug fix)*/
		 assertFalse( my_appt_1.isOn(1, 4, 1) );	
	 }
	 
	 @Test
	 public void isOn_4()  throws Throwable  {			/* FAILS! (prior to bug fix)*/ 
		 assertFalse( my_appt_1.isOn(1, 1, 2018) );
	 }
	 //therefore, there is a bug here - IsOn becomes "true" whenever any one 
	 //    of the date, month, or year values is correct (needes to be all of 
	 //    them). A bug has been found!
	 
//checks setValid	 
	 @Test
	 public void SetValid_1()  throws Throwable  {
		 //expected valid
		 my_appt_1.setValid();
		 assertTrue( my_appt_1.getValid() );
	 }
	 
	 @Test
	 public void SetValid_2()  throws Throwable  {
		 //expected valid
		 my_appt_2.setValid();
		 assertTrue( my_appt_2.getValid() );
	 }
	 	 
		 
	// Cannot be run due to array indexing error in the CalendarUtil class.	 
		 //month out of range
		 /*
		 my_appt_3.setStartMonth(0);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 my_appt_3.setStartMonth(2);
		 */
	
	 
	 //appt_3 initial values: 1(hr), 0(min), 29(day), 2(mo), 2016(yr)
	 @Test
	 public void Range_1()  throws Throwable  {	 
		 //Day out of range
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
		 
	 @Test
	 public void Range_2()  throws Throwable  {
		 //Year out of range
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }	
		 
	 
	 @Test
	 public void Range_3()  throws Throwable  {			/* FAILS! */
		 //minutes out of range
		 my_appt_3.setStartMinute(72);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 
	 @Test
	 public void Range_4()  throws Throwable  {			/* FAILS! */
		 //hour out of range
		 my_appt_3.setStartHour(-7);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 // for tests 3 and 4, it's still set to valid even after hour and minute
	 // have been changed to an invalid value.

	 
//checks recurrence functions
	 @Test
	 public void recur_1() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 
		 assertTrue( my_appt_1.isRecurring() );
	 }
	 
	 @Test
	 public void recur_2() 	throws Throwable	{
		 int[] recur_2 = {0, 1, 2, 3, 4, 5, 6};
		 my_appt_2.setRecurrence( recur_2, 2, 10, 10);	//every month
		 assertTrue( my_appt_2.isRecurring() );
	 }
	 
	 @Test
	 public void recur_3() 	throws Throwable	{
		 int[] recur_3 = {6};
		 corner.setRecurrence( recur_3, 3, 10, -1);		//every year, infinite
		 assertTrue( corner.isRecurring() );
	 }
		 
	 //even though no days on which to recur are given, it still "recurs"
	 //    because it is provided with a recurNumber > 0.
	 @Test
	 public void recur_4() 	throws Throwable	{
		 int[] recur_4 = null;
		 my_appt_3.setRecurrence( recur_4, 1, 10, 10);
		 assertTrue( my_appt_3.isRecurring() );
	 }
	
	 
//string functions
	 //appt 1 initial values: 12(hr), 0(min), 13(day), 4(mo), 2018(yr)
	 //appt 2 initial values: 13(day), 4(mo), 2018(yr)
	 @Test
	 public void string_2_5()	throws Throwable	{ 
		 my_appt_1.setStartDay(74);
		 my_appt_1.setValid();
		 String output = "\t2018/4/74 at 12:11pm, desc, title\n";
		 assertEquals( output,  my_appt_1.toString() );
	 } 
	 
	 @Test
	 public void string_4()		throws Throwable	{ 
		 my_appt_1.setTitle( null );
		 assertEquals( "", my_appt_1.getTitle() );
	 }

	 @Test
	 public void string_5()		throws Throwable	{
		 my_appt_1.setDescription( null );
		 assertEquals( "", my_appt_1.getDescription() );
		 
	 }
	 
	 @Test
	 public void string_6()		throws Throwable	{
		 Appt test_appt = new Appt(1, 0, 1, 1, 1, "title", "desc", null);
		 assertEquals( "", test_appt.getEmailAddress() );
	 }
}
