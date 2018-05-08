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
	
	//leap year test
	Appt my_appt_3 = new Appt(1, 0, 29, 2, 2016, "title", "desc", "email");
	
	//null title/desc/email case
	Appt my_appt_4 = new Appt(13, 30, 30, 7, 2222, null, null, null);
	
	
	
	Appt bound_1 = new Appt(0, 0, 1, 1, 1, "title", "desc", "email");
	Appt bound_2 = new Appt(23, 59, 31, 1, 1, null, null, null);
			
	Appt invalid_1_hour 	= new Appt(0,  0,  1, 1,  1, null, null, null);
	Appt invalid_1_minute 	= new Appt(1, -1,  1, 1,  1, null, null, null);
	Appt invalid_1_day 		= new Appt(1,  1, 32, 1,  1, null, null, null);
	Appt invalid_1_year 	= new Appt(1,  1,  1, 1, -1, null, null, null);
    
	Appt invalid_2_hour 	= new Appt(24, 1, 1, 1, 1, null, null, null);
	Appt invalid_2_minute 	= new Appt(1, 60, 1, 1, 1, null, null, null);
	Appt invalid_2_day 		= new Appt(1,  1, 0, 1, 1, null, null, null);
 
	
//performing isValid tests on bounds and invalid:
    @Test
    public void valid_1()	throws Throwable	{
    	bound_1.setValid();
    	assertTrue( bound_1.getValid() );
    }
    @Test
    public void valid_2()	throws Throwable	{
    	bound_2.setValid();
    	assertTrue( bound_2.getValid() );
    }
    /*
    @Test
    public void valid_3()	throws Throwable	{
    	invalid_1_hour.setValid();
    	assertFalse( invalid_1_hour.getValid() );
    }
    */
    @Test
    public void valid_4()	throws Throwable	{
    	invalid_1_minute.setValid();
    	assertFalse( invalid_1_minute.getValid() );
    }
    @Test
    public void valid_5()	throws Throwable	{
    	invalid_1_day.setValid();
    	assertFalse( invalid_1_day.getValid() );
    }
    @Test
    public void valid_6()	throws Throwable	{
    	invalid_1_year.setValid();
    	assertFalse( invalid_1_year.getValid() );
    }
    @Test
    public void valid_7()	throws Throwable	{
    	invalid_2_hour.setValid();
    	assertFalse( invalid_2_hour.getValid() );
    }
    @Test
    public void valid_8()	throws Throwable	{	
    	invalid_2_minute.setValid();
    	assertFalse( invalid_2_minute.getValid() );
    }
    @Test
    public void valid_9()	throws Throwable	{
    	invalid_2_day.setValid();
    	assertFalse( invalid_2_day.getValid() );
    }

    
	
//verifying setters/ getters
	@Test
	public void get_test_1()	throws Throwable	{
		assertEquals( 1, my_appt_3.getStartHour() );
	}
	@Test
	public void get_test_2()	throws Throwable	{
		assertEquals( 0, my_appt_3.getStartMinute() );
	}
	@Test
	public void get_test_3()	throws Throwable	{
		assertEquals( 29, my_appt_3.getStartDay() );
	}@Test
	public void get_test_4()	throws Throwable	{
		assertEquals( 2, my_appt_3.getStartMonth() );
	}
	@Test
	public void get_test_5()	throws Throwable	{
		assertEquals( 2016, my_appt_3.getStartYear() );
	}

	
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
	 
	//appt 1 initial values: 12(hr), 0(min), 13(day), 4(mo), 2018(yr)
	//appt 2 initial values:                 13(day), 4(mo), 2018(yr)
	//appt_3 initial values: 1(hr),  0(min), 29(day), 2(mo), 2016(yr)
	
	 @Test //checking all wrong values
	 public void IsOn_1()	throws Throwable	{
		 assertFalse( my_appt_1.isOn(1, 1, 1) );
	 }
	 
	 @Test //checking all right values
	 public void IsOn_1_5()	throws Throwable	{
		 assertTrue(  my_appt_1.isOn(13, 4, 2018) );
	 }
	 
	 @Test //checking just day
	 public void isOn_2()  throws Throwable  {			/* FAILS! (prior to bug fix)*/
		 assertFalse( my_appt_1.isOn(13, 1, 1) );
	 }
	 
	 @Test //checking just month
	 public void isOn_3()  throws Throwable  {			/* FAILS! (prior to bug fix)*/
		 assertFalse( my_appt_1.isOn(1, 4, 1) );	
	 }
	 
	 @Test //checking just year
	 public void isOn_4()  throws Throwable  {			/* FAILS! (prior to bug fix)*/ 
		 assertFalse( my_appt_1.isOn(1, 1, 2018) );
	 }
	 //therefore, there is a bug here - IsOn becomes "true" whenever any one 
	 //    of the date, month, or year values is correct (needes to be all of 
	 //    them). A bug has been found!
	 
	 @Test	//checking year and month
	 public void isOn_5()  throws Throwable  {
		 assertFalse( my_appt_1.isOn(1, 4, 2018) );
	 }
	 @Test //checking day and year
	 public void isOn_6()  throws Throwable  { 
		 assertFalse( my_appt_1.isOn(13, 77, 2018) );
	 }
	 @Test //checking day and year
	 public void isOn_7()  throws Throwable  {
		 assertFalse( my_appt_1.isOn(13, 4, 1) );
	 }
	 
	 
	 
	 
//checks setValid	
	 
	 @Test		// all valid
	 public void SetValid_1()  throws Throwable  {
		 //expected valid
		 my_appt_1.setValid();
		 assertTrue( my_appt_1.getValid() );
	 }
	 
	 @Test		//all valid
	 public void Set_Valid_4() 	throws Throwable	{
		 my_appt_4.setValid();
		 assertTrue( my_appt_4.getValid() );
	 }
	 
/*	 @Test
	 public void SetValid_2()  throws Throwable  {		/ FAILS /
		 //expected valid
		 my_appt_2.setValid();
		 assertTrue( my_appt_2.getValid() );
	 }
	 // this test reveals that an appointment without a set start time is not valid!
	 // this seems to be an error in the source code provided (the secondary 
	 // constructor is not compatible with the setValid method.
	 
	 // will not fix this in the source code as this is not a bug that I introduced.
*/	 	 // UPDATE: I have implemented a fix into the appt.java source code
	 	 // 	for this because I need it for the CalDay tests.
		 
	// Cannot be run due to array indexing error in the CalendarUtil class.	 
	// month out of range
/*
		 my_appt_3.setStartMonth(0);
		 my_appt_3.setValid();
		 test_var = my_appt_3.getValid();
		 assertFalse(test_var);
		 
		 my_appt_3.setStartMonth(2);
*/
	 @Test
	 public void xml_Test()	throws Throwable {
		 assertEquals( null, my_appt_1.getXmlElement() );
	 }
	
	 //appt 1 initial values: 12(hr), 0(min), 13(day), 4(mo), 2018(yr)
	 //appt 2 initial values:                 13(day), 4(mo), 2018(yr)
	 //appt_3 initial values: 1(hr),  0(min), 29(day), 2(mo), 2016(yr)
	 
	 @Test	//Day out of range
	 public void Range_1()  throws Throwable  {	 
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
		 
	 @Test	 //Year out of range
	 public void Range_2()  throws Throwable  {
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }	
		 
	 @Test	//minutes out of range
	 public void Range_3()  throws Throwable  {			/* FAILS! (prior to fix)*/
		 my_appt_3.setStartMinute(72);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 
	 @Test	//hour out of range
	 public void Range_4()  throws Throwable  {			/* FAILS! (prior to fix)*/
		 my_appt_3.setStartHour(-7);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//day and year out of range
	 public void Range_5()  throws Throwable  {
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test 	//day and minutes out of range
	 public void Range_6()  throws Throwable  {
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test 	//day and hour out of range
	 public void Range_7()  throws Throwable  {
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setStartDay(32);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//year and minutes out of range
	 public void Range_8()  throws Throwable  {
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//year and hour out of range
	 public void Range_9()  throws Throwable  {
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//minutes and hour out of range
	 public void Range_10()  throws Throwable  {
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//day and year and minutes out of range
	 public void Range_11()  throws Throwable  {
		 my_appt_3.setStartDay(32);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//day and year and hours out of range
	 public void Range_12()  throws Throwable  {
		 my_appt_3.setStartDay(32);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//day and minutes and hours out of range
	 public void Range_13()  throws Throwable  {
		 my_appt_3.setStartDay(32);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test 	//year and minutes and hours out of range
	 public void Range_14()  throws Throwable  {
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 @Test	//day and year and minutes and hours out of range
	 public void Range_15()  throws Throwable  {
		 my_appt_3.setStartDay(32);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartMinute(-1);
		 my_appt_3.setStartHour(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 
	 
	 
	 
	 // for tests 3 and 4, it's still set to valid even after hour and minute
	 // have been changed to an invalid value.
	 
	 // startYear and startDay have been proven to work, so something's up with
	 // startHour and startMinute.

	 /* COMMENTED OUT BECAUSE THIS WAS USED TO FIND A BUG	 
	 //performing further testing:
	 
	 //testing invalid year + invalid hour
	 @Test
	 public void Range_5()	throws Throwable	{
		 my_appt_3.setStartHour(-7);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 // the above test passed. This means that the year overrides the hour in 
	 // terms of validity (perhaps)
	 
	 
	 // a thought - the base initialization of my_appt_3 is on Feb 29th, 2016 -
	 // this is a leap year. When the year is changed, the day also becomes invalid, 
	 // even though I don't change the date. Let's try changing both the year 
	 // and the day.
	 @Test
	 public void Range_6()	throws Throwable	{
		 my_appt_3.setStartHour(-7);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartDay(7);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 } 
	 // the above test failed! This suggests that the date (day of the month) is 
	 // might be overwriting the others to set the validity of the appt.
	 
	 
	 // let's do one last test - setting everything to invalid except the date
	 // (and month, due to the CalendarUtil dependency).
	 @Test
	 public void Range_7()	throws Throwable	{
		 my_appt_3.setStartMinute(700);
		 my_appt_3.setStartHour(-7);
		 my_appt_3.setStartYear(-1);
		 my_appt_3.setStartDay(4);
		 my_appt_3.setValid();
		 assertFalse( my_appt_3.getValid() );
	 }
	 // okay, this would seem to be the case!
	 // the validity of the day overrides the validity of the other variables.
	 // A bug has been found!
*/
	 
	 
	 
	 
//checks recurrence functions
	 
	 @Test
	 public void recur_1() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertTrue( my_appt_1.isRecurring() );
	 }
	 
	 @Test	//explicitely testing getRecurIncrement
	 public void recur_1_x() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertEquals( 10, my_appt_1.getRecurIncrement() );
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
		 bound_1.setRecurrence( recur_3, 3, 10, -1);		//every year, infinite
		 assertTrue( bound_1.isRecurring() );
	 }
		 
	 //even though no days on which to recur are given, it still "recurs"
	 //    because it is provided with a recurNumber > 0.
	 @Test
	 public void recur_4() 	throws Throwable	{
		 int[] recur_4 = null;
		 my_appt_3.setRecurrence( recur_4, 1, 10, 10);
		 assertTrue( my_appt_3.isRecurring() );
	 }
	 /*
	 @Test
	 public void recur_4_1() 	throws Throwable	{
		 int[] recur_4 = null;
		 int[] test = new int[0];
		 my_appt_3.setRecurrence( recur_4, 1, 10, 10);
		 assertEquals( null, (my_appt_3.getRecurDays())[0] );
		 assertEquals( test, my_appt_3.getRecurDays() );
	 }
	*/
	 @Test
	 public void recur_5() 	throws Throwable	{
		 int[] recur_4 = {6};
		 my_appt_3.setRecurrence( recur_4, 1, 10, 0);
		 assertFalse( my_appt_3.isRecurring() );
	 }
	 
//verifying recursion initialization
	 @Test
	 public void recur_1_1() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertEquals( recur_1, my_appt_1.getRecurDays() );
	 }
	 
	 @Test
	 public void recur_1_2() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertEquals( 1, my_appt_1.getRecurBy() );
	 }
	 
	 @Test
	 public void recur_1_3() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertEquals( 10, my_appt_1.getRecurIncrement() );
	 }
	 
	 @Test
	 public void recur_1_4() 	throws Throwable	{
		 int[] recur_1 = {2, 3};
		 my_appt_1.setRecurrence( recur_1, 1, 10, 10);
		 // repeats on 3rd and 4th day of the week, every week, ?, 10 times.
		 assertEquals( 10, my_appt_1.getRecurNumber() );
	 }
	 
	 
	 
	 
	 
//string functions
	 //appt 1 initial values: 12(hr), 0(min), 13(day), 4(mo), 2018(yr)
	 //appt 2 initial values: 13(day), 4(mo), 2018(yr)
	 @Test	//testing toString output
	 public void string_2_1()	throws Throwable	{ 
		 my_appt_1.setStartDay(74);
		 my_appt_1.setValid();
		 String output = "\t2018/4/74 at 12:11pm, desc, title\n";
		 assertEquals( output,  my_appt_1.toString() );
	 } 
	 
	 @Test	//testing toString output (am conditional)
	 public void string_2_2()	throws Throwable	{ 
		 my_appt_1.setStartHour(3);
		 my_appt_1.setValid();
		 String output = "\t2018/4/13 at 3:11am, desc, title\n";
		 assertEquals( output,  my_appt_1.toString() );
	 } 

	@Test 	//testing toString output (pm conditional)
	public void string_2_3()	throws Throwable	{ 
		 my_appt_1.setStartHour(17);
		 my_appt_1.setValid();
		 String output = "\t2018/4/13 at 5:11pm, desc, title\n";
		 assertEquals( output,  my_appt_1.toString() );
	 } 
	 
	@Test	//testing 12pm corner case
	public void string_2_4()	throws Throwable	{
		my_appt_1.setStartHour(12);
		my_appt_1.setValid();
		String output = "\t2018/4/13 at 12:11pm, desc, title\n";
		assertEquals( output,  my_appt_1.toString() );
	}
	
	
// testing email, desc, title set.	
	 @Test
	 public void string_4()		throws Throwable	{ 
		 my_appt_1.setTitle( null );
		 assertEquals( "", my_appt_1.getTitle() );
	 }
	 @Test
	 public void string_4_1()		throws Throwable	{ 
		 assertEquals( "title", my_appt_1.getTitle() );
	 }

	 @Test
	 public void string_5()		throws Throwable	{
		 my_appt_1.setDescription( null );
		 assertEquals( "", my_appt_1.getDescription() );
		 
	 }
	 @Test
	 public void string_5_1()		throws Throwable	{
		 assertEquals( "desc", my_appt_1.getDescription() );
		 
	 }
	 
	 @Test
	 public void string_6()		throws Throwable	{
		 Appt test_appt = new Appt(1, 0, 1, 1, 1, "title", "desc", null);
		 assertEquals( "", test_appt.getEmailAddress() );
	 }
	 @Test
	 public void string_6_1()		throws Throwable	{
		 assertEquals( "email", my_appt_1.getEmailAddress() );
		 
	 }
}
