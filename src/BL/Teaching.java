package BL;

/**
 * Contient toutes les donn�es d'un enseignement et les m�thodes pour le g�rer.
 * @author URM Team
 */
public abstract class Teaching 
{
	/**
	 * Identifiant de l'enseignement.
	 */
	protected String id;
	
	/**
	 * Nombre d'heures de l'enseignement.
	 */
	protected int hours;
	
	/**
	 * Groupe qui suit cet enseignement.
	 */
	protected String group;
	
	/**
	 * Mati�re de cet enseignement.
	 */
	protected String field;
	
	/**
	 * Enseignant qui effectue cet enseignement.
	 */
	protected Teacher teacher;
	
	/**
	 * Type d'enseignement (ex: cours ou TD).
	 */
	protected String type;

	/**
	 * Charge l'enseignement correspondant � l'id donn� en param�tre.
	 * @param id
	 * 			identifiant de l'enseignement.
	 * @throws Exception 
	 * 			Probl�me d'acc�s aux donn�es.
	 */
	public abstract void load(String id) throws Exception ;
	
	/**
	 * Cr�e un enseignement.
	 * @param id
	 * 			Identifiant de l'enseignement.
	 * @param hours
	 * 			Nombre d'heures de l'enseignement.
	 * @param group
	 * 			Groupe qui suit cet enseignement.
	 * @param teacher
	 * 			Enseignant qui effectue cet enseignement.
	 * @param field
	 * 			Mati�re de cet enseignement.
	 * @param type
	 * 			Type d'enseignement (ex: cours ou TD).
	 */
	public void create(String id, int hours, String group, Teacher teacher, String field, String type)
	{
		this.id = id;
		this.hours = hours;
		this.group = group;
		this.field = field;
		this.teacher = teacher;
		this.type = type;
	}

	/**
	 * @return l'enseignant qui enseigne cet enseignement.
	 */
	public Teacher getTeacher() 
	{
		return teacher;
	}
	
	/**
	 * Modifie l'enseignant en le rempla�ant par t.
	 * @param t
	 * 		Le nouvel enseignant
	 */
	public void setTeacher(Teacher t) 
	{
		this.teacher = t;
	}

	/**
	 * @return l'id de l'enseignement.
	 */
	public String getId() 
	{
		return id;
	}	

	/**
	 * @return la mati�re de l'enseignement.
	 */
	public String getField() 
	{
		return field;
	}
	
	/**
	 * @return une cha�ne de caract�re avec le titre d'un enseignement.
	 */
	public String toString()
	{
		return this.type + " " +this.field+" "+this.group;
		
	}
}
