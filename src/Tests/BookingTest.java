package Tests;


import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import BL.Booking;
import BL.Feature;
import BL.PersistFactory;
import BL.Schedule;
import BL.Teaching;

import junit.framework.TestCase;

public class BookingTest extends TestCase 
{
	Booking b;
	
	@Before
	protected void setUp() throws Exception 
	{
		super.setUp();
		b = PersistFactory.getInstance().createBooking();
		b.load("13");
		assertEquals("6", b.getSchedule().getId());
		assertEquals("6", b.getTeaching().getId());
		assertEquals("SC202", b.getRoom());
	}

	@Test
	public void testCheckFreeRooms() 
	{
		try 
		{
			assertEquals(1, this.b.checkFreeRooms());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSetDate() 
	{
		Date d = new Date();
		this.b.setDate(d);
		assertEquals(d, this.b.getDate());
	}

	@Test
	public void testSetSchedule() 
	{
		try 
		{
			Schedule s = PersistFactory.getInstance().createSchedule();
			s.create("1","8h00","9h30");
			this.b.setSchedule(s);
			assertEquals(s, this.b.getSchedule());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSetFeatures() 
	{
		ArrayList<Feature> alf = new ArrayList<Feature>();
		
		try 
		{
			Feature f = PersistFactory.getInstance().createFeature();
			f.create("1","videoprojecteur");
			alf.add(f);
			this.b.setFeatures(alf);
			assertEquals(alf, this.b.getFeatures());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSetTeaching() 
	{
		try 
		{
			Teaching t = PersistFactory.getInstance().createTeaching();
			this.b.setTeaching(t);
			assertEquals(t, this.b.getTeaching());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
