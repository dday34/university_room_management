package BL;

/**
 * Contient toutes les données sur un créneau et les méthodes permettant de le gérer.
 * @author URM Team
 */
public abstract class Schedule 
{
	/**
	 * Identifiant du créneau.
	 */
	protected String id;
	
	/**
	 * Heure de début du créneau.
	 */
	protected String startTime;
	
	/**
	 * Heure de fin du créneau.
	 */
	protected String endTime;

	/**
	 * Charge le créneau avec l'id correspondant.
	 * @param id
	 * 			identifiant du créneau à charger.
	 * @throws Exception 
	 * 			Problème d'accés aux données.
	 */
	public abstract void load(String id) throws Exception;
	
	/**
	 * Création d'un créneau.
	 * @param id
	 * 			Identifiant du créneau.
	 * @param startTime
	 * 			Heure de début du créneau.
	 * @param endTime
	 * 			Heure de fin du créneau.
	 */
	public void create(String id, String startTime, String endTime)
	{
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * @return l'id du créneau.
	 */
	public String getId() 
	{
		return this.id;
	}

	/**
	 * @return une chaîne de caractère avec toutes les informations du créneau.
	 */
	public String toString() 
	{
		return this.startTime+" - "+this.endTime;
	}

}
