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

public class CalDayTest {
	/**
	 * Test that the gets methods work as expected.
	 */

	// valid = false
	CalDay calday_1 = new CalDay();

	// valid = true
	GregorianCalendar calendar = new GregorianCalendar(1, 1, 1);
	CalDay calday = new CalDay(calendar);

	// verifies validity of the created CalDay objects
	@Test
	public void valid_test_1() throws Throwable {
		assertFalse(calday_1.isValid());
	}

	@Test
	public void valid_test_2() throws Throwable {
		assertTrue(calday.isValid());
	}

	// verifies setters/ getters for CalDay
	@Test
	public void constructor_test_1() throws Throwable {
		assertEquals(1, calday.getDay());
	}

	@Test
	public void constructor_test_2() throws Throwable {
		assertEquals(1, calday.getMonth());
	}

	@Test
	public void constructor_test_3() throws Throwable {
		assertEquals(1, calday.getYear());
	}

	// making some appointments
	Appt appt_1 = new Appt(14, 0, 1, 1, 1, "title_1", "desc_1", "email_1");
	Appt appt_2 = new Appt(5, 30, 1, 1, 1, "title_2", "desc_2", "email_2");
	Appt appt_3 = new Appt(3, 0, 1, 1, 1, "title_3", "desc_3", "email_3");
	Appt invalid = new Appt(3, 0, 45, 1, 1, "title", "desc", "email");
	Appt no_time = new Appt(2, 2, 1, "title_4", "desc_4", "email_4");

	// testing addAppt
	@Test
	public void appt_1() throws Throwable {
		appt_1.setValid();
		calday.addAppt(appt_1);
		assertEquals(1, calday.getSizeAppts());
	}

	// testing iterator
	@Test
	public void iter_1() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		invalid.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		calday.addAppt(appt_2);
		calday.addAppt(appt_3);
		calday.addAppt(appt_1);
		calday.addAppt(invalid);

		// testing iterators - iterator for the invalid calday instance
		Iterator<?> iter = calday.iterator();
		assertNotEquals(null, iter);
	}

	@Test
	public void iter_2() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		invalid.setValid();

		Iterator<?> iter_1 = calday_1.iterator();

		// adding appointments into the valid CalDay instance
		/*
		 * calday_1.addAppt( appt_1 ); calday_1.addAppt( appt_2 ); calday_1.addAppt(
		 * appt_3 ); calday_1.addAppt( appt_1 ); calday_1.addAppt( invalid);
		 */

		// testing iterators
		// Iterator<?> iter_1 = calday_1.iterator();
		assertEquals(null, iter_1);
	}

	@Test
	public void iter_3() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		invalid.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		calday.addAppt(appt_2);
		calday.addAppt(appt_3);
		calday.addAppt(appt_1);
		calday.addAppt(invalid);

		// testing iterators
		Iterator<?> iter = calday.iterator();
		assertEquals(4, calday.getSizeAppts());
	}

	// testing string functions
	@Test
	public void string_0() throws Throwable {
		String output = "\t --- 2/1/1 --- \n --- -------- Appointments ------------ --- \n\n";
		String test_str = calday.toString();
		assertEquals(output, test_str);
	}

	// hr min day mo yr
	// appt 1 set times: 14, 0, 1, 1, 1
	// appt 2 set times: 5, 30, 1, 1, 1
	// appt 3 set times: 3, 0, 1, 1, 1

	@Test
	public void string_1() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		calday.addAppt(appt_2);
		// calday.addAppt( appt_3 );

		String output1 = "\t --- 2/1/1 --- \n --- -------- Appointments ------------ --- \n";
		String output2 = "\t1/1/1 at 2:0pm, desc_1, title_1\n ";
		String output3 = "\t1/1/1 at 5:30am, desc_2, title_2\n ";
		// \t1/1/1 at 5:30am, desc_2, title_2 \n\t1/1/1 at 2:0pm, desc_1, title_1 \n\n";
		String output = output1 + output3 + output2 + "\n";
		String test_str = calday.toString();
		assertEquals(output, test_str);
	}

	@Test
	public void string_2() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		appt_3.setValid();
		invalid.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		calday.addAppt(appt_2);
		calday.addAppt(appt_3);
		calday.addAppt(appt_1);
		calday.addAppt(invalid);

		String test_str = calday.getFullInfomrationApp(calday);
		assertNotEquals("", test_str);
	}

	@Test // testing invalid calday instance
	public void string_3() throws Throwable {
		String test_str = calday_1.toString();
		assertEquals("", test_str);
	}

	// hr min day mo yr
	// appt 1 set times: 14, 0, 1, 1, 1
	// appt 2 set times: 5, 30, 1, 1, 1
	// no_time : - -, 2, 2, 1
	@Test
	public void string_4() throws Throwable {
		appt_1.setValid();
		// appt_2.setValid();
		no_time.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		// calday.addAppt( appt_2 );
		calday.addAppt(no_time);

		// for appt_1 and appt_2
		// String output = "1-1-1 \n\t5:30AM title_2 desc_2 \n\t2:00PM title_1 desc_1 ";

		// for appt_1 and no_time
		String output = "1-1-1 \n\ttitle_4 desc_4 \n\t2:00PM title_1 desc_1 ";

		String test_str = calday.getFullInfomrationApp(calday);
		assertEquals(output, test_str);
	}

	@Test
	public void string_5() throws Throwable {
		appt_1.setValid();
		appt_2.setValid();
		// no_time.setValid();

		// adding appointments into the valid CalDay instance
		calday.addAppt(appt_1);
		calday.addAppt(appt_2);
		// calday.addAppt( no_time );

		// for appt_1 and appt_2
		String output = "1-1-1 \n\t5:30AM title_2 desc_2 \n\t2:00PM title_1 desc_1 ";

		// for appt_1 and no_time
		// String output = "1-1-1 \n\ttitle_4 desc_4 \n\t2:00PM title_1 desc_1 ";

		String test_str = calday.getFullInfomrationApp(calday);
		assertEquals(output, test_str);
	}

}
