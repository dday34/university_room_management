package BL;

/**
 * Contient toute les donn�es d'une caract�ristique.
 * @author URM Team
 */
public abstract class Feature 
{
	/**
	 * Identifiant de la caract�ristique.
	 */
	protected String id;
	
	/**
	 * Nom de la caract�ristique.
	 */
	protected String name;

	/**
	 * Charge la caract�ristique correspondant � l'id donn� en param�tre.
	 * @param id
	 * 			identifiant de la caract�ristique
	 * @throws Exception 
	 * 			Probl�me lors de la r�cup�ration des donn�es
	 */
	public abstract void load(String id) throws Exception ;

	/**
	 * Cr� une nouvelle caract�ristique.
	 * @param id
	 * 			identifiant de la caract�ristique
	 * @param name
	 * 			Nom de la caract�ristique.
	 */
	public void create(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return Retourne l'id de la caract�ristique.
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * @return Retourne le nom de la caract�ristique.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de la caract�ristique.
	 * @param name
	 * 			nouveau nom choisi.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return le nom de la caract�ristique
	 */
	public String toString()
	{
		return this.name;
	}

}
