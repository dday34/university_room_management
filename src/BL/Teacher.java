package BL;

import java.util.ArrayList;

/**
 * Classe contenant toutes les informations d'un enseignant et permettant de les gérer.
 * @author URM Team
 */
public abstract class Teacher 
{
	/**
	 * Identifiant de l'enseignant.
	 */
	protected String id;
	
	/**
	 * Prénom de l'enseignant.
	 */
	protected String firstName;
	
	/**
	 * Nom de l'enseignant.
	 */
	protected String lastName;
	
	/**
	 * Mot de passe de l'enseignant.
	 */
	protected String password;
	
	/**
	 * Numéro de téléphone de l'enseignant.
	 */
	protected String phone;
	
	/**
	 * e-mail de l'enseignant.
	 */
	protected String mail;
	
	/**
	 * Vrai si cet enseignant est le gestionnaire de l'emploi du temps, faux sinon.
	 */
	protected boolean superUser;
	
	/**
	 * Enseignements effectués par l'enseignant.
	 */
	protected ArrayList<Teaching> myTeachings;

	/**
	 * Charge l'enseignant qui a le nom donné en paramètre, si le mot de passe est le bon.
	 * @param nom
	 * 			nom de l'enseignant
	 * @param pwd 
	 * 			mot de passe de l'enseignant
	 * @throws Exception 
	 * 			Problème lors de l'accès aux données.
	 */
	public abstract void load(String nom, String pwd) throws Exception;

	/**
	 * @return le nom de l'enseignant.
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * @return l'id de l'enseignant.
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * @return le mot de passe de l'utilisateur.
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * @return la liste des enseignements de cet enseignant.
	 */
	public ArrayList<Teaching> getTeachings()
	{
		return this.myTeachings;
	}

	/**
	 * @return vrai si l'enseignant est le gestionnaire, faux sinon.
	 */
	public boolean isSuperUser() 
	{
		return this.superUser;
	}

	/**
	 * @return la liste des réservations qui ont été faites par l'utilisateur et qui ont été validées (Qui ont reçu une salle). 
	 * @param week 
	 * 			Semaine pour laquelle on veut les réservations validées.
	 * @throws Exception 
	 * 			Problème d'accès aux données.
	 */
	public abstract ArrayList<Booking> getValidBooking(int week) throws Exception;

}
