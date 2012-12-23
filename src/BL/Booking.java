package BL;

import java.util.ArrayList;
import java.util.Date;


/**
 * 	Contient toutes les données d'une réservation de la demande à la validation.	
 * @author URM Team																																																																																																																																																																																																						
 */
public abstract class Booking 
{
	/**
	 * Identifiant de la réservation
	 */
	protected String id;
	
	/**
	 * Date de la réservation
	 */
	protected Date date;
	
	/**
	 * Créneau de la réservation.
	 */
	protected Schedule schedule;
	
	/**
	 * Caractéristiques de la réservation.
	 */
	protected ArrayList<Feature> features;
	
	/**
	 * Capacitée de la réservation.
	 */
	protected int capacity;

	/**
	 * Enseignement de la réservation.
	 */
	protected Teaching teaching;
	
	/**
	 * Commentaires de la réservation.
	 */
	protected String comments;
	
	/**
	 * Salle choisie lors de la validation de la réservation.
	 */
	protected String room;

	/**
	 * Charge la réservation qui à pour id reference.
	 * @param reference
	 * 			reference de la réservation
	 * @throws Exception 
	 * 			Problème lors de la récupération des données.
	 */
	public abstract void load(String reference) throws Exception ;

	/**
	 * @return Retourne le nombre de salles de disponibles pour les paramètres de cette réservation.
	 * @throws Exception 
	 * 			Problème lors de la récupération des données.
	 */
	public abstract int checkFreeRooms() throws Exception;

	/**
	 * @return Retourne l'enseignement lié à cette réservation.
	 */
	public Teaching getTeaching() 
	{
		return teaching;
	}

	/**
	 * @return Retourne la date de réservation.
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * Remplace la date de reservation.
	 * @param date
	 * 			nouvelle date
	 */
	public void setDate(Date date) 
	{
		this.date=date;
	}

	/**
	 * @return Retourne le créneau de réservation.
	 */
	public Schedule getSchedule() 
	{
		return schedule;
	}

	/**
	 * Remplace le créneau de réservation.
	 * @param schedule
	 * 			nouveau créneau
	 */
	public void setSchedule(Schedule schedule) 
	{
		this.schedule = schedule;
	}

	/**
	 * @return Retourne la liste des caractéristiques de la réservation.
	 */
	public ArrayList<Feature> getFeatures() 
	{
		return features;
	}

	/**
	 * Remplace la liste des caractéristiques de la réservation.
	 * @param features
	 * 			liste de caractéristiques
	 */
	public void setFeatures(ArrayList<Feature> features) 
	{
		this.features = features;
	}

	/**
	 * @return Renvoie la salle de la réservation.
	 */
	public String getRoom() 
	{
		return room;
	}

	/**
	 * Associe une salle à la réservation.
	 * @param room
	 * 			salle choisie
	 */
	public void setRoom(String room) 
	{
		this.room = room;
	}

	/**
	 * Sauvegarde la réservation.
	 * @return vrai si la sauvegarde a réussie, faux sinon.
	 * @throws Exception 
	 * 			Problème lors de la sauvegarde des données.
	 */
	public abstract boolean save() throws Exception;

	/**
	 * @return le domaine d'activité de la réservation.
	 */
	public String getField() 
	{
		return teaching.getField();
	}
	
	/**
	 * Modifie l'enseignement de la réservation.
	 * @param t
	 * 			le nouvel enseignement
	 */
	public void setTeaching(Teaching t)
	{
		this.teaching = t;
	}

}
