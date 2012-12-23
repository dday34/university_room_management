package BL;

/**
 * Contient toutes les donn�es sur un cr�neau et les m�thodes permettant de le g�rer.
 * @author URM Team
 */
public abstract class Schedule 
{
	/**
	 * Identifiant du cr�neau.
	 */
	protected String id;
	
	/**
	 * Heure de d�but du cr�neau.
	 */
	protected String startTime;
	
	/**
	 * Heure de fin du cr�neau.
	 */
	protected String endTime;

	/**
	 * Charge le cr�neau avec l'id correspondant.
	 * @param id
	 * 			identifiant du cr�neau � charger.
	 * @throws Exception 
	 * 			Probl�me d'acc�s aux donn�es.
	 */
	public abstract void load(String id) throws Exception;
	
	/**
	 * Cr�ation d'un cr�neau.
	 * @param id
	 * 			Identifiant du cr�neau.
	 * @param startTime
	 * 			Heure de d�but du cr�neau.
	 * @param endTime
	 * 			Heure de fin du cr�neau.
	 */
	public void create(String id, String startTime, String endTime)
	{
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * @return l'id du cr�neau.
	 */
	public String getId() 
	{
		return this.id;
	}

	/**
	 * @return une cha�ne de caract�re avec toutes les informations du cr�neau.
	 */
	public String toString() 
	{
		return this.startTime+" - "+this.endTime;
	}

}
