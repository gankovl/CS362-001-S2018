package calendar;

import org.junit.Test;
//import java.util.Calendar;
//import java.util.Random;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {

	private static final long TestTimeout = 20 * 500 * 1; /* Timeout at 10 seconds */
	private static final int NUM_TESTS = 100;

	/**
	 * Generate Random Tests that tests CalDay Class.
	 */

	public Appt generate_appt() {
		long randomseed = System.currentTimeMillis(); // 10
		Random random = new Random(randomseed);

		int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 59);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 28);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
		int startYear = ValuesGenerator.getRandomIntBetween(random, 2000, 2018);
		String title = "Title";
		String description = "Description";
		String emailAddress = "Email";

		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description, emailAddress);
		return appt;
	}

	@Test
	public void randomTest() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		//making CalDay instance
		GregorianCalendar calendar = new GregorianCalendar(1, 1, 1);
		CalDay calday = new CalDay( calendar );
		
		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

				Appt add_appt = null;
				
				for (int i = 0; i < NUM_TESTS; i++) {
					
					if( ValuesGenerator.getRandomIntBetween(random, 0, 9) != 0)
						add_appt = generate_appt();
					else
						add_appt = new Appt(-1, -1, -1, 1, -1, "title", "desc", "email");
					
					add_appt.setValid();
					calday.addAppt( add_appt );
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {}

	}
}
