package Tests;

import java.util.ArrayList;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import BL.TeacherFacade;

public class TeacherFacadeTest extends TestCase
{
	TeacherFacade tf;
	
	@Before
    public void setUp() throws Exception 
    {
		if(this.tf == null)
		{
			this.tf = new TeacherFacade("Laurent", "anne");   
		}
    }

	@Test
	public void testGetSchedules() 
	{
		ArrayList<String> alt = new ArrayList<String>();
		alt.add("8h00 - 9h30");
		alt.add("9h45 - 11h15");
		alt.add("11h30 - 13h00");
		alt.add("13h15 - 14h45");
		alt.add("15h00 - 16h30");
		alt.add("16h45 - 18h15");
		alt.add("18h30 - 20h00");
		
		ArrayList<String> als = new ArrayList<String>();
		
		als = this.tf.getSchedules();
		
		assertEquals(alt.size(), als.size());
		for(int i=0; i<alt.size();i++)
		{
			assertEquals(alt.get(i), als.get(i));
		}
	}

	@Test
	public void testGetFeatures() 
	{
		ArrayList<String> alt = new ArrayList<String>();
		alt.add("videoprojecteur");
		alt.add("grande");
		alt.add("moyenne");
		alt.add("petite");
		alt.add("TP");
		
		ArrayList<String> als = new ArrayList<String>();
		
		als = this.tf.getFeatures();
		
		assertEquals(alt.size(), als.size());
		for(int i=0; i<alt.size();i++)
		{
			assertEquals(alt.get(i), als.get(i));
		}
	}

	@Test
	public void testGetTeachings() 
	{
		ArrayList<String> alt = new ArrayList<String>();
		alt.add("TP Langue 1: anglais IG4 G2");
		alt.add("TD Système d exploitation IG4 anglais G3");
		alt.add("Cours Méthodologie de gestion de projets IG4 G1");

		ArrayList<String> als = new ArrayList<String>();
		
		als = this.tf.getTeachings();
		
		assertEquals(alt.size(), als.size());
		for(int i=0; i<alt.size();i++)
		{
			assertEquals(alt.get(i), als.get(i));
		}
	}

	@Test
	public void testGetValidBooking() 
	{
		int week = 11;

		ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
		ArrayList<String> ls = new ArrayList<String>();
		ls.add("2012-03-12");
		ls.add("11h30 - 13h00");
		ls.add("SC001");
		ls.add("Système d exploitation");
		als.add(ls);
		
		ArrayList<ArrayList<String>> alb = this.tf.getValidBooking(week);
		assertEquals(alb.size(), als.size());
		for(int i=0; i<alb.size(); i++)
		{
			for (int j=0; j<alb.get(i).size(); j++)
			{
				assertEquals(alb.get(i).size(), als.get(i).size());
				assertEquals(alb.get(i).get(j), als.get(i).get(j));
			}
		}
	}

	@Test
	public void testIsSuperUser() 
	{
		boolean superuser = this.tf.isSuperUser();
		assertEquals(false, superuser);
	}

	@Test
	public void testConfirmBooking() 
	{
		ArrayList<String> fts = new ArrayList<String>();
		fts.add("videoprojecteur");
		try 
		{
			//this.tf.confirmBooking("TP Langue 1: anglais IG4 G2", new Date(), "9h45 - 11h15", fts, 45, "URGENT SVP");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckFreeRooms() 
	{
		int nbr;
		ArrayList<String> fts = new ArrayList<String>();
		fts.add("videoprojecteur");
		try 
		{
			nbr = this.tf.checkFreeRooms(new Date(), "9h45 - 11h15", fts, 45);
			assertEquals(12, nbr);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
