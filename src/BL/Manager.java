package BL;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe qui permet de gérer tous les service qui n'ont pas de lien avec l'enseignant, les services généraux sur les données.
 * @author URM Team
 */
public abstract class Manager 
{
	/**
	 * Contient toutes les caractéristiques de salle disponibles.
	 */
	protected ArrayList<Feature> allFeatures;
	
	/**
	 * Contient tous les créneaux disponibles.
	 */
	protected ArrayList<Schedule> allSchedules;
	
	/**
	 * Contient toutes les réservations disponibles.
	 */
	protected ArrayList<Booking> allBookings;
	
	/**
	 * Réservation sélectionnée par l'utilisateur.
	 */
	protected Booking selectedBooking;

	/**
	 * @return une liste de toutes les caractéristiques existantes.
	 * @throws Exception 
	 * 				Problème d'accés aux données
	 */
	public abstract ArrayList<Feature> getFeatures() throws Exception;

	/**
	 * @return une liste de tous les créneaux existants.
	 * @throws Exception 
	 * 				Problème d'accés aux données
	 */
	public abstract ArrayList<Schedule> getSchedules() throws Exception ;

	/**
	 * @return la liste de toutes les réservations qui ne sont pas encore validées, c'est à dire pour lesquelles aucune salle n'est attribuée.
	 */
	public abstract ArrayList<Booking> getAllUnvalidBookings();

	/**
	 * @return la date de la réservation selectionnée.
	 */
	public Date getSelectedBookingDate() 
	{
		return selectedBooking.getDate();
	}

	/**
	 * @return le créneau de la réservation selectionnée.
	 */
	public Schedule getSelectedBookingSchedule() 
	{
		return selectedBooking.getSchedule();
	}

	/**
	 * @return sous forme de liste de structure les dates et créneaux pour lesquelles il y aucune salle libre avec les paramètre de la réservation selectionnée.
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
	 * Remplace l'ancien créneau de la reservation par le nouveau créneau.
	 * @param schedule
	 * 			le nouveau créneau
	 */
	public void setSelectedBookingSchedule(Schedule schedule) 
	{
		selectedBooking.setSchedule(schedule);
	}

	/**
	 * Remplace les anciennes caractéristiques de la reservation par les nouvelles.
	 * @param features
	 * 			les nouvelles caractéristiques
	 */
	public void setSelectedBookingFeatures(ArrayList<Feature> features) 
	{
		selectedBooking.setFeatures(features);
	}

	/**
	 * Sauvegarde la réservation selectionnée et lui associe une salle.
	 * @throws Exception 
	 * 				Problème lors de la sauvegarde des données
	 */
	public void saveSelectedBooking() throws Exception 
	{
		selectedBooking.save();
	}

	/**
	 * Supprime la réservation selectionnée.
	 */
	public abstract void deleteSelectedBooking() ;

}
