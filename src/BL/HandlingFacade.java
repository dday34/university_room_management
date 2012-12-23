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
	 * Selectionne la r�servation avec la reference donn�e en param�tre.
	 */
	public void selectBooking(int reference) {
	}

	/**
	 * retourne sous la forme d'une structure compos�e de strings toutes les informations sur le teacher, c'est � dire nom, pr�nom, t�l�phone, mail et enseignement.
	 */
	public String getBookingTeacherInfos() {
		return null;
	}

	/**
	 * Retourne la date de la r�servation selectionn�e.
	 */
	public String getSelectedBookingDate() {
		return null;
	}

	/**
	 * Retourne la listes de toutes les caract�ristiques disponibles.
	 */
	public ArrayList<String> getFeatures() {
		return null;
	}

	/**
	 * Retourne la liste des caract�ristiques de la r�servation selectionn�e.
	 */
	public ArrayList<String>  getSelectedBookingFeatures() {
		return null;
	}

	/**
	 * Retourne la liste de tout les cr�neaux disponibles.
	 */
	public ArrayList<String>  getSchedules() {
		return null;
	}

	/**
	 * Retourne le cr�neau de la r�servation selectionn�e.
	 */
	public String getSelectedBookingSchedule() {
		return null;
	}

	/**
	 * Retourne toutes les cr�neaux pour toutes les dates de la semaine pour lesquels il y a des salles de disponibles avec cette r�servation.
	 */
	public String getWeekValidSchedules() {
		return null;
	}

	/**
	 * Sauvegarde la r�servation et lui associe une salle.
	 */
	public void validBooking() {
	}

	/**
	 * Supprime la r�servation.
	 */
	public void deleteBooking() {
	}

	/**
	 * Remplace les donn�es dans la r�servation selectionn�e.
	 */
	public void changeBookingData(String date, String schedule, ArrayList<String> features) {
	}

}
