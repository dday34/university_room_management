package BL;
import PersistJDBC.PersistFactoryJDBC;
//import PersistSerialization.PersistFactorySerialization;

/**
 * Fabrique abstraite qui permet de cr�er des objets en charge de la persistance des donn�es.
 * @author URM Team
 */
public abstract class PersistFactory 
{
	/**
	 * Instance static de la persistFactory (Pattern singleton)
	 */
	private static PersistFactory PF;

	/**
	 * @return l'instance de la persistFactory si existant, sinon cr�e l'instance et la renvoie.
	 * @throws Exception 
	 * 				Probl�me de connexion aux donn�es.
	 */
	public static PersistFactory getInstance() throws Exception
	{
		if(PF==null) PF = new PersistFactoryJDBC();
		//if(PF==null) PF = new PersistFactorySerialization();
		return PF;
	}

	/**
	 * @return et cr�e un objet de type enseignant.
	 */
	public abstract Teacher createTeacher();

	/**
	 * @return et cr�e un objet de type enseignement.
	 */
	public abstract Teaching createTeaching();

	/**
	 * @return et cr�e un objet de type reservation.
	 */
	public abstract Booking createBooking();

	/**
	 * @return et cr�e un objet de type caract�ristique.
	 */
	public abstract Feature createFeature();

	/**
	 * @return et cr�e un objet de type cr�neau horaire.
	 */
	public abstract Schedule createSchedule();

	/**
	 * @return et cr�e un objet de type gestionnaire.
	 */
	public abstract Manager createManager();

}
