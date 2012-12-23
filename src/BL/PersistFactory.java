package BL;
import PersistJDBC.PersistFactoryJDBC;
//import PersistSerialization.PersistFactorySerialization;

/**
 * Fabrique abstraite qui permet de créer des objets en charge de la persistance des données.
 * @author URM Team
 */
public abstract class PersistFactory 
{
	/**
	 * Instance static de la persistFactory (Pattern singleton)
	 */
	private static PersistFactory PF;

	/**
	 * @return l'instance de la persistFactory si existant, sinon crée l'instance et la renvoie.
	 * @throws Exception 
	 * 				Problème de connexion aux données.
	 */
	public static PersistFactory getInstance() throws Exception
	{
		if(PF==null) PF = new PersistFactoryJDBC();
		//if(PF==null) PF = new PersistFactorySerialization();
		return PF;
	}

	/**
	 * @return et crée un objet de type enseignant.
	 */
	public abstract Teacher createTeacher();

	/**
	 * @return et crée un objet de type enseignement.
	 */
	public abstract Teaching createTeaching();

	/**
	 * @return et crée un objet de type reservation.
	 */
	public abstract Booking createBooking();

	/**
	 * @return et crée un objet de type caractéristique.
	 */
	public abstract Feature createFeature();

	/**
	 * @return et crée un objet de type créneau horaire.
	 */
	public abstract Schedule createSchedule();

	/**
	 * @return et crée un objet de type gestionnaire.
	 */
	public abstract Manager createManager();

}
