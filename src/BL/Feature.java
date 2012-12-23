package BL;

/**
 * Contient toute les données d'une caractéristique.
 * @author URM Team
 */
public abstract class Feature 
{
	/**
	 * Identifiant de la caractéristique.
	 */
	protected String id;
	
	/**
	 * Nom de la caractéristique.
	 */
	protected String name;

	/**
	 * Charge la caractéristique correspondant à l'id donné en paramètre.
	 * @param id
	 * 			identifiant de la caractéristique
	 * @throws Exception 
	 * 			Problème lors de la récupération des données
	 */
	public abstract void load(String id) throws Exception ;

	/**
	 * Cré une nouvelle caractéristique.
	 * @param id
	 * 			identifiant de la caractéristique
	 * @param name
	 * 			Nom de la caractéristique.
	 */
	public void create(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return Retourne l'id de la caractéristique.
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * @return Retourne le nom de la caractéristique.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de la caractéristique.
	 * @param name
	 * 			nouveau nom choisi.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return le nom de la caractéristique
	 */
	public String toString()
	{
		return this.name;
	}

}
