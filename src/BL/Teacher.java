package BL;

import java.util.ArrayList;

/**
 * Classe contenant toutes les informations d'un enseignant et permettant de les g�rer.
 * @author URM Team
 */
public abstract class Teacher 
{
	/**
	 * Identifiant de l'enseignant.
	 */
	protected String id;
	
	/**
	 * Pr�nom de l'enseignant.
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
	 * Num�ro de t�l�phone de l'enseignant.
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
	 * Enseignements effectu�s par l'enseignant.
	 */
	protected ArrayList<Teaching> myTeachings;

	/**
	 * Charge l'enseignant qui a le nom donn� en param�tre, si le mot de passe est le bon.
	 * @param nom
	 * 			nom de l'enseignant
	 * @param pwd 
	 * 			mot de passe de l'enseignant
	 * @throws Exception 
	 * 			Probl�me lors de l'acc�s aux donn�es.
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
	 * @return la liste des r�servations qui ont �t� faites par l'utilisateur et qui ont �t� valid�es (Qui ont re�u une salle). 
	 * @param week 
	 * 			Semaine pour laquelle on veut les r�servations valid�es.
	 * @throws Exception 
	 * 			Probl�me d'acc�s aux donn�es.
	 */
	public abstract ArrayList<Booking> getValidBooking(int week) throws Exception;

}
