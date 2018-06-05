package calendar;

import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Random Test Generator for DataHandler class.
 */

public class DataHandlerRandomTest {

	private static final long TestTimeout =  30 * 500 * 1; /* Timeout at 30 seconds */
	private static final int APPTS_TO_ADD = 15;

	public Appt generate_valid_appt() {
		long randomseed = System.currentTimeMillis(); // 10
		Random random = new Random(randomseed);

		int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 59);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 28);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
		int startYear = ValuesGenerator.getRandomIntBetween(random, 1, 5);
		String title = "Title";
		String description = "Description";
		String emailAddress = "Email";

		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description, emailAddress);
		
		//set recurrence
		if( ValuesGenerator.getRandomIntBetween(random, 0, 1) == 1) {
			
			int n =  ValuesGenerator.getRandomIntBetween(random, 0, 2);
			int[] recurDays = new int[n];
			for(int i=0; i<n; i++)
				recurDays[i] = ValuesGenerator.getRandomIntBetween(random, 1, 7);
			
			int recurBy 		= ValuesGenerator.getRandomIntBetween(random, 1, 3);
			int recurIncrement 	= ValuesGenerator.getRandomIntBetween(random, 1, 9);
			int recurNumber		= ValuesGenerator.getRandomIntBetween(random, -1, 0);
			
			appt.setRecurrence( recurDays, recurBy, recurIncrement, recurNumber);
		}
		
		return appt;
	}
	public Appt generate_appt() {
		long randomseed = System.currentTimeMillis(); // 10
		Random random = new Random(randomseed);

		int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 50);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 100);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 50);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
		int startYear = ValuesGenerator.getRandomIntBetween(random, -5, 5);
		String title = "Title";
		String description = "Description";
		String emailAddress = "Email";

		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description, emailAddress);
		
		return appt;
	}

	// Generate Random Tests that tests DataHandler Class.
	@Test
	public void randomtest_deleteAppt() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

				DataHandler datahandler = null;

				// autoSave true / false toggle
				if (ValuesGenerator.getRandomIntBetween(random, 0, 1) == 1)
					datahandler = new DataHandler();
				else
					datahandler = new DataHandler("filename", false);

				// appt generation
				Appt appt = generate_appt();
				appt.setValid();
				
				datahandler.saveAppt(appt);
				
				// toggle xml valid
				if (ValuesGenerator.getRandomIntBetween(random, 0, 1) == 1)
					appt.setXmlElement(null);
				
				datahandler.deleteAppt(appt);
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			}
		} catch (NullPointerException e) {}
	}
	
	
	@Test
	public void randomtest_getApptRange() throws Throwable {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		try {
			DataHandler datahandler = null;
			GregorianCalendar day = new GregorianCalendar(1, 1, 1);
			
			LinkedList<CalDay> calDays = new LinkedList<CalDay>();
			calDays = (LinkedList<CalDay>) datahandler.getApptRange(day, day);
			
		} catch (NullPointerException e) {}
		
		for (int iteration = 0; iteration < 100; iteration++) {
			try {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

				DataHandler datahandler = new DataHandler();

				// add some appointments
				for(int i=0; i<APPTS_TO_ADD; i++) {
					Appt appt = generate_valid_appt();
					datahandler.saveAppt(appt);
				}
				
				// generate day pair
				int day_1 	= ValuesGenerator.getRandomIntBetween(random, 1, 28);
				int day_2 	= ValuesGenerator.getRandomIntBetween(random, 1, 28);
				int month_1 = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				int month_2 = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				int year_1 	= ValuesGenerator.getRandomIntBetween(random, 0, 7);
				int year_2 	= ValuesGenerator.getRandomIntBetween(random, 3, 10);

				GregorianCalendar day_one = new GregorianCalendar(day_1, month_1, year_1);
				GregorianCalendar day_two = new GregorianCalendar(day_2, month_2, year_2);
				
				LinkedList<CalDay> calDays = new LinkedList<CalDay>();
				calDays = (LinkedList<CalDay>) datahandler.getApptRange(day_one, day_two);
			} 
			catch (DateOutOfRangeException e) {}
			catch (NullPointerException e) {}
		}
	}

}
