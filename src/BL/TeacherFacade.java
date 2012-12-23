package BL;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Façade qui permet la communication avec la BL à travers un enseignant.
 * @author URM Team
 */
public class TeacherFacade 
{
	/**
	 * Enseignant qui est connecté.
	 */
	private Teacher user;
	
	/**
	 * Gestionnaire qui rend des services globaux à tous les enseignants.
	 */
	private Manager manager;
	
	/**
	 * Réservation créée par l'utilisateur.
	 */
	private Booking myBooking;

	/**
	 * Constructeur.
	 * @param nom
	 * 			Nom de l'utilisateur, qui est aussi son identifiant.
	 * @param pwd
	 * 			Mot de passe de l'utilisateur.
	 * @throws Exception 
	 * 			Problème lors de la connection de l'utilisateur.
	 */
	public TeacherFacade(String nom, String pwd) throws Exception 
	{
		this.manager = PersistFactory.getInstance().createManager();
		
		this.user = PersistFactory.getInstance().createTeacher(); //crée un teacher
			
		this.user.load(nom, pwd);
	}

	/**
	 * Retourne la liste des créneaux disponibles sous la forme d'une liste de String.
	 */
	public ArrayList<String> getSchedules() 
	{
		ArrayList<Schedule> scs;
		ArrayList<String> als = new ArrayList<String>();
		try {
			scs = this.manager.getSchedules();
			
			for (int i=0; i<scs.size(); i++)
			{
				als.add(scs.get(i).toString());
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return als;
	}

	/**
	 * retourne la liste des caractéristiques disponibles dans une liste de String.
	 */
	public ArrayList<String> getFeatures() 
	{
		ArrayList<Feature> alft;
		ArrayList<String> als = new ArrayList<String>();
		try {
			alft = this.manager.getFeatures();
			
			for (int i=0; i<alft.size(); i++)
			{
				als.add(alft.get(i).toString());
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return als;
	}

	/**
	 * retourne la liste des enseignement de l'enseignant dans une liste de String.
	 */
	public ArrayList<String> getTeachings() 
	{
		ArrayList<Teaching> scs;
		ArrayList<String> alt = new ArrayList<String>();
		try 
		{
			scs = this.user.getTeachings();
			
			for (int i=0; i<scs.size(); i++)
			{
				alt.add(scs.get(i).toString());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return alt;
	}

	/**
	 * Renvoie la liste des Réservations faites par l'enseignant qui sont validées.
	 * @param week
	 * 			Numéro de la semaine pour laquelle on veut les réservations validées.
	 */
	public ArrayList<ArrayList<String>> getValidBooking(int week) 
	{
		int i=0;
		/* liste de liste de string */
		ArrayList<ArrayList<String>> resaValidesString = new ArrayList<ArrayList<String>>();
		ArrayList<String> resaString;
		ArrayList<Booking> resaValides = new ArrayList<Booking>();
		Booking booking;
		
		try 
		{
			resaValides = user.getValidBooking(week);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		for (i=0; i<resaValides.size(); i++){
			
			booking = resaValides.get(i);
			
			resaString = new ArrayList<String>();
			resaString.add(booking.getDate().toString());
			resaString.add(booking.getSchedule().toString());
			resaString.add(booking.getRoom());
			resaString.add(booking.getField());
			
			resaValidesString.add(resaString);
		}
		return resaValidesString;
	}

	/**
	 * Retourne vrai si l'utilisateur est le gestionnaire de l'emploi du temps, faux sinon.
	 */
	public boolean isSuperUser()
	{
		if(this.user != null)
		{
			return this.user.isSuperUser();
		}
		else
		{
			return false;
		}
	}

	/**
	 * Valide définitivement la reservation, c'est à dire sauvegarde les données de celle-ci.
	 * @param comments 
	 * 			commentaires entrés par l'utilisateur.
	 * @param capacity 
	 * 			capacitée demandée par l'utilisateur.
	 * @param featuresSelected 
	 * 			caractéristiques choisies par l'utilisateur.
	 * @param scheduleSelected 
	 * 			créneau choisi par l'utilisateur.
	 * @param dateSelected 
	 * 			date chosie par l'utilisateur.
	 * @param teachingSelected 
	 * 			enseignement choisi par l'utilisateur.
	 * @throws Exception 
	 * 			Déclenchée si les informations de la réservation n'ont pas pu être récupérées.
	 */
	public void confirmBooking(String teachingSelected, Date dateSelected, String scheduleSelected, ArrayList<String> featuresSelected, int capacity, String comments) throws Exception 
	{
		if(this.myBooking==null)
		{
			this.checkFreeRooms(dateSelected, scheduleSelected, featuresSelected, capacity);
		}
		
		int i = 0;
		
		if(teachingSelected.equals("reunion"))
		{
			
		}
		else
		{
			while(!this.user.getTeachings().get(i).toString().equals(teachingSelected))
			{
				i++;
			}
			this.myBooking.setTeaching(this.user.getTeachings().get(i));
		}
		
		this.myBooking.save();
	}

	/**
	 * Retourne le nombre de salles disponibles avec les caractéristiques, la date, le créneau et la capacité choisie.
	 * @param capacity 
	 * 			capacitée demandée par l'utilisateur.
	 * @param featuresSelected 
	 * 			caractéristiques choisies par l'utilisateur.
	 * @param scheduleSelected 
	 * 			créneau choisi par l'utilisateur.
	 * @param dateSelected 
	 * 			date chosie par l'utilisateur.
	 * @throws Exception 
	 * 			Déclenchée si les informations de la réservation n'ont pas pu être récupérées.
	 */
	public int checkFreeRooms(Date dateSelected, String scheduleSelected, ArrayList<String> featuresSelected, int capacity) throws Exception 
	{
		int freeRooms = 0;
		this.myBooking = PersistFactory.getInstance().createBooking();
		
		this.myBooking.setDate(dateSelected);
		
		int s = 0;
		
		while(!this.manager.getSchedules().get(s).toString().equals(scheduleSelected))
		{
			s++;
		}
		
		this.myBooking.setSchedule(this.manager.getSchedules().get(s));
		
		ArrayList<Feature> alf = new ArrayList<Feature>();
		Iterator<String> i = featuresSelected.iterator();
		while(featuresSelected!=null && i.hasNext())
		{
			String ft = i.next();
			int ftus = 0;
			while(!this.manager.getFeatures().get(ftus).toString().equals(ft))
			{
				ftus++;
			}
			alf.add(this.manager.getFeatures().get(ftus));
		}
		
		this.myBooking.setFeatures(alf);
		
		this.myBooking.capacity = capacity;
		
		freeRooms = this.myBooking.checkFreeRooms();

		return freeRooms;
	}

}
