package BL;

import java.util.ArrayList;
import java.util.Date;


/**
 * 	Contient toutes les donn�es d'une r�servation de la demande � la validation.	
 * @author URM Team																																																																																																																																																																																																						
 */
public abstract class Booking 
{
	/**
	 * Identifiant de la r�servation
	 */
	protected String id;
	
	/**
	 * Date de la r�servation
	 */
	protected Date date;
	
	/**
	 * Cr�neau de la r�servation.
	 */
	protected Schedule schedule;
	
	/**
	 * Caract�ristiques de la r�servation.
	 */
	protected ArrayList<Feature> features;
	
	/**
	 * Capacit�e de la r�servation.
	 */
	protected int capacity;

	/**
	 * Enseignement de la r�servation.
	 */
	protected Teaching teaching;
	
	/**
	 * Commentaires de la r�servation.
	 */
	protected String comments;
	
	/**
	 * Salle choisie lors de la validation de la r�servation.
	 */
	protected String room;

	/**
	 * Charge la r�servation qui � pour id reference.
	 * @param reference
	 * 			reference de la r�servation
	 * @throws Exception 
	 * 			Probl�me lors de la r�cup�ration des donn�es.
	 */
	public abstract void load(String reference) throws Exception ;

	/**
	 * @return Retourne le nombre de salles de disponibles pour les param�tres de cette r�servation.
	 * @throws Exception 
	 * 			Probl�me lors de la r�cup�ration des donn�es.
	 */
	public abstract int checkFreeRooms() throws Exception;

	/**
	 * @return Retourne l'enseignement li� � cette r�servation.
	 */
	public Teaching getTeaching() 
	{
		return teaching;
	}

	/**
	 * @return Retourne la date de r�servation.
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
	 * @return Retourne le cr�neau de r�servation.
	 */
	public Schedule getSchedule() 
	{
		return schedule;
	}

	/**
	 * Remplace le cr�neau de r�servation.
	 * @param schedule
	 * 			nouveau cr�neau
	 */
	public void setSchedule(Schedule schedule) 
	{
		this.schedule = schedule;
	}

	/**
	 * @return Retourne la liste des caract�ristiques de la r�servation.
	 */
	public ArrayList<Feature> getFeatures() 
	{
		return features;
	}

	/**
	 * Remplace la liste des caract�ristiques de la r�servation.
	 * @param features
	 * 			liste de caract�ristiques
	 */
	public void setFeatures(ArrayList<Feature> features) 
	{
		this.features = features;
	}

	/**
	 * @return Renvoie la salle de la r�servation.
	 */
	public String getRoom() 
	{
		return room;
	}

	/**
	 * Associe une salle � la r�servation.
	 * @param room
	 * 			salle choisie
	 */
	public void setRoom(String room) 
	{
		this.room = room;
	}

	/**
	 * Sauvegarde la r�servation.
	 * @return vrai si la sauvegarde a r�ussie, faux sinon.
	 * @throws Exception 
	 * 			Probl�me lors de la sauvegarde des donn�es.
	 */
	public abstract boolean save() throws Exception;

	/**
	 * @return le domaine d'activit� de la r�servation.
	 */
	public String getField() 
	{
		return teaching.getField();
	}
	
	/**
	 * Modifie l'enseignement de la r�servation.
	 * @param t
	 * 			le nouvel enseignement
	 */
	public void setTeaching(Teaching t)
	{
		this.teaching = t;
	}

}
