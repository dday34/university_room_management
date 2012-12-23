package PersistSerialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import BL.Booking;
import BL.Feature;
import BL.Manager;
import BL.PersistFactory;
import BL.Schedule;
import BL.Teacher;
import BL.Teaching;

public class PersistFactorySerialization extends PersistFactory 
{

	@Override
	public Teacher createTeacher() 
	{
		TeacherSerialization t = new TeacherSerialization();
		return t;
	}

	@Override
	public Teaching createTeaching() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking createBooking() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feature createFeature() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schedule createSchedule() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager createManager() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String argv[]) 
	{
		File dossier = new File("data");
		dossier.mkdir();
		File dossierTeacher = new File("data/teacher");
		dossierTeacher.mkdir();
		
		try 
		{
			TeacherSerialization t1 = new TeacherSerialization();
			t1.create("1", "Anne", "Laurent", "anne", false, null);
			FileOutputStream fichier = new FileOutputStream("data/teacher/Laurent.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(t1);
			oos.flush();
			oos.close();
			
			TeacherSerialization t2 = new TeacherSerialization();
			t2.create("2", "Tiberiu", "Stratulat", "tiberiu", true, null);
			FileOutputStream fichier1 = new FileOutputStream("data/teacher/Stratulat.ser");
			ObjectOutputStream oos1 = new ObjectOutputStream(fichier1);
			oos1.writeObject(t2);
			oos1.flush();
			oos1.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
