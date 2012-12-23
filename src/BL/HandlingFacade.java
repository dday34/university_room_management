package BL;

import java.util.ArrayList;

/**
 * 	Classe en cours de construction																																																															
 */
class HandlingFacade 
{
	
	@SuppressWarnings("unused")
	private Manager manager;
	
	/**
	 * Constructeur
	 */
	public HandlingFacade() 
	{
	}

	/**
	 * Renvoie la liste de toutes les demandes de reservations 
	 * des enseignants qui ne sont pas validees.
	 */
	public ArrayList<String> getAllUnvalidBookings() {
		return null;
	}

	/**
	 * Selectionne la réservation avec la reference donnée en paramètre.
	 */
	public void selectBooking(int reference) {
	}

	/**
	 * retourne sous la forme d'une structure composée de strings toutes les informations sur le teacher, c'est à dire nom, prénom, téléphone, mail et enseignement.
	 */
	public String getBookingTeacherInfos() {
		return null;
	}

	/**
	 * Retourne la date de la réservation selectionnée.
	 */
	public String getSelectedBookingDate() {
		return null;
	}

	/**
	 * Retourne la listes de toutes les caractéristiques disponibles.
	 */
	public ArrayList<String> getFeatures() {
		return null;
	}

	/**
	 * Retourne la liste des caractéristiques de la réservation selectionnée.
	 */
	public ArrayList<String>  getSelectedBookingFeatures() {
		return null;
	}

	/**
	 * Retourne la liste de tout les créneaux disponibles.
	 */
	public ArrayList<String>  getSchedules() {
		return null;
	}

	/**
	 * Retourne le créneau de la réservation selectionnée.
	 */
	public String getSelectedBookingSchedule() {
		return null;
	}

	/**
	 * Retourne toutes les créneaux pour toutes les dates de la semaine pour lesquels il y a des salles de disponibles avec cette réservation.
	 */
	public String getWeekValidSchedules() {
		return null;
	}

	/**
	 * Sauvegarde la réservation et lui associe une salle.
	 */
	public void validBooking() {
	}

	/**
	 * Supprime la réservation.
	 */
	public void deleteBooking() {
	}

	/**
	 * Remplace les données dans la réservation selectionnée.
	 */
	public void changeBookingData(String date, String schedule, ArrayList<String> features) {
	}

}
