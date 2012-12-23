package Tests;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import BL.*;

import junit.framework.TestCase;

public class TeacherTest extends TestCase 
{
	Teacher t;
	

	@Before
	protected void setUp() throws Exception 
	{
		super.setUp();
		t = PersistFactory.getInstance().createTeacher();
		t.load("Laurent", "anne");
	}

	@Test
	public void testGetValidBooking() 
	{
		int week = 10;
		ArrayList<String> als = new ArrayList<String>();
		als.add("Cours Méthodologie de gestion de projets IG4 G1");
		als.add("TP Langue 1: anglais IG4 G2");
		
		try 
		{
			ArrayList<Booking> alb = this.t.getValidBooking(week);
			assertEquals(als.size(), alb.size());
			for(int i=0; i<als.size(); i++)
			{
				assertEquals(als.get(i), alb.get(i).getTeaching().toString());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTeachings() 
	{
		ArrayList<String> als = new ArrayList<String>();
		als.add("TP Langue 1: anglais IG4 G2");
		als.add("TD Système d exploitation IG4 anglais G3");
		als.add("Cours Méthodologie de gestion de projets IG4 G1");
		
		ArrayList<Teaching> alt = this.t.getTeachings();
		
		assertEquals(als.size(), alt.size());
		for(int i=0; i<als.size(); i++)
		{
			assertEquals(als.get(i), alt.get(i).toString());
		}
	}

	@Test
	public void testIsSuperUser() 
	{
		boolean superuser = this.t.isSuperUser();
		assertEquals(false, superuser);
	}

}
