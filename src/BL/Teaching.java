package BL;

/**
 * Contient toutes les données d'un enseignement et les méthodes pour le gérer.
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
	 * Matière de cet enseignement.
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
	 * Charge l'enseignement correspondant à l'id donné en paramètre.
	 * @param id
	 * 			identifiant de l'enseignement.
	 * @throws Exception 
	 * 			Problème d'accés aux données.
	 */
	public abstract void load(String id) throws Exception ;
	
	/**
	 * Crée un enseignement.
	 * @param id
	 * 			Identifiant de l'enseignement.
	 * @param hours
	 * 			Nombre d'heures de l'enseignement.
	 * @param group
	 * 			Groupe qui suit cet enseignement.
	 * @param teacher
	 * 			Enseignant qui effectue cet enseignement.
	 * @param field
	 * 			Matière de cet enseignement.
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
	 * Modifie l'enseignant en le remplaçant par t.
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
	 * @return la matière de l'enseignement.
	 */
	public String getField() 
	{
		return field;
	}
	
	/**
	 * @return une chaîne de caractére avec le titre d'un enseignement.
	 */
	public String toString()
	{
		return this.type + " " +this.field+" "+this.group;
		
	}
}
