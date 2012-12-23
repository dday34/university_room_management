package Tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import BL.Feature;
import BL.Manager;
import BL.PersistFactory;
import BL.Schedule;

public class ManagerTest extends TestCase
{
	Manager mng;

	@Before
	public void setUp() throws Exception 
	{
		mng = PersistFactory.getInstance().createManager();
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

		ArrayList<Feature> als;
		try 
		{
			als = this.mng.getFeatures();
			assertEquals(alt.size(), als.size());
			for(int i=0; i<alt.size();i++)
			{
				assertEquals(alt.get(i), als.get(i).toString());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
		
		ArrayList<Schedule> als;
		try 
		{
			als = this.mng.getSchedules();
			assertEquals(alt.size(), als.size());
			for(int i=0; i<alt.size();i++)
			{
				assertEquals(alt.get(i), als.get(i).toString());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
