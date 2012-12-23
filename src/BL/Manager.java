package BL;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe qui permet de g�rer tous les service qui n'ont pas de lien avec l'enseignant, les services g�n�raux sur les donn�es.
 * @author URM Team
 */
public abstract class Manager 
{
	/**
	 * Contient toutes les caract�ristiques de salle disponibles.
	 */
	protected ArrayList<Feature> allFeatures;
	
	/**
	 * Contient tous les cr�neaux disponibles.
	 */
	protected ArrayList<Schedule> allSchedules;
	
	/**
	 * Contient toutes les r�servations disponibles.
	 */
	protected ArrayList<Booking> allBookings;
	
	/**
	 * R�servation s�lectionn�e par l'utilisateur.
	 */
	protected Booking selectedBooking;

	/**
	 * @return une liste de toutes les caract�ristiques existantes.
	 * @throws Exception 
	 * 				Probl�me d'acc�s aux donn�es
	 */
	public abstract ArrayList<Feature> getFeatures() throws Exception;

	/**
	 * @return une liste de tous les cr�neaux existants.
	 * @throws Exception 
	 * 				Probl�me d'acc�s aux donn�es
	 */
	public abstract ArrayList<Schedule> getSchedules() throws Exception ;

	/**
	 * @return la liste de toutes les r�servations qui ne sont pas encore valid�es, c'est � dire pour lesquelles aucune salle n'est attribu�e.
	 */
	public abstract ArrayList<Booking> getAllUnvalidBookings();

	/**
	 * @return la date de la r�servation selectionn�e.
	 */
	public Date getSelectedBookingDate() 
	{
		return selectedBooking.getDate();
	}

	/**
	 * @return le cr�neau de la r�servation selectionn�e.
	 */
	public Schedule getSelectedBookingSchedule() 
	{
		return selectedBooking.getSchedule();
	}

	/**
	 * @return sous forme de liste de structure les dates et cr�neaux pour lesquelles il y aucune salle libre avec les param�tre de la r�servation selectionn�e.
	 */
	public abstract ArrayList<String> getWeekUnvalidSchedules() ;

	/**
	 * Remplace l'ancienne date de la reservation par la nouvelle date.
	 * @param date
	 * 			la nouvelle date
	 */
	public void setSelectedBookingDate(Date date) 
	{
		selectedBooking.setDate(date);
	}

	/**
	 * Remplace l'ancien cr�neau de la reservation par le nouveau cr�neau.
	 * @param schedule
	 * 			le nouveau cr�neau
	 */
	public void setSelectedBookingSchedule(Schedule schedule) 
	{
		selectedBooking.setSchedule(schedule);
	}

	/**
	 * Remplace les anciennes caract�ristiques de la reservation par les nouvelles.
	 * @param features
	 * 			les nouvelles caract�ristiques
	 */
	public void setSelectedBookingFeatures(ArrayList<Feature> features) 
	{
		selectedBooking.setFeatures(features);
	}

	/**
	 * Sauvegarde la r�servation selectionn�e et lui associe une salle.
	 * @throws Exception 
	 * 				Probl�me lors de la sauvegarde des donn�es
	 */
	public void saveSelectedBooking() throws Exception 
	{
		selectedBooking.save();
	}

	/**
	 * Supprime la r�servation selectionn�e.
	 */
	public abstract void deleteSelectedBooking() ;

}
