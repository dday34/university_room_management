package PersistSerialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import BL.Booking;
import BL.Teacher;
import BL.Teaching;

public class TeacherSerialization extends Teacher implements Serializable
{

	private static final long serialVersionUID = -3433592847741500802L;

	@Override
	public void load(String nom, String pwd) throws Exception 
	{
		FileInputStream fichier = new FileInputStream("data/teacher/"+nom+".ser");
		ObjectInputStream ois = new ObjectInputStream(fichier);
		TeacherSerialization t = (TeacherSerialization) ois.readObject();
		this.id = t.id;
		this.firstName = t.firstName;
		this.lastName = t.lastName;
		this.myTeachings = t.myTeachings;
		this.password = t.password;
		this.superUser = t.superUser;
		this.mail = t.mail;
		this.phone = t.phone;
		fichier.close();
		ois.close();
		
	}

	@Override
	public ArrayList<Booking> getValidBooking(int week) throws Exception 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void create(String id, String firstName, String lastName, String password, boolean superUser, ArrayList<Teaching> myTeachings)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.superUser = superUser;
		this.myTeachings = myTeachings;
	}

}
