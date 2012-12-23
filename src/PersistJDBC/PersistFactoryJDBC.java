package PersistJDBC;
import BL.*;

import java.sql.*;

/**
 * Fabrique qi permet de cr�er des objets � partir d'une base de donn�es.
 * @author URM Team
 */
public class PersistFactoryJDBC extends PersistFactory 
{
	/**
	 * Connection vers la base de donn�es.
	 */
	private Connection dbConnection;
	
	/**
	 * Driver de la base de donn�es
	 */
	private String driver= "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * URL de la base de donn�es
	 */
	private String url="jdbc:oracle:thin:@v240.ig.polytech.univ-montp2.fr:1521:ORA10";
	
	/**
	 * Login de l'acc�s � la base de donn�es
	 */
	private String login="christophe.gire";
	
	/**
	 * Mot de passe de l'acc�s � la base de donn�es.
	 */
	private String password="oracle";
  
	/**
	 * Constructeur
	 * @throws SQLException 
	 * 			Probl�me de connection � la base.
	 * @throws ClassNotFoundException 
	 * 			Probl�me de chargement du Driver.
	 */
	public PersistFactoryJDBC() throws SQLException, ClassNotFoundException
  	{
		super();
	  
		// Charger le driver
		Class.forName(driver); //Or any other driver
  
		// Cr�er la connection
		this.dbConnection=DriverManager.getConnection(url,login,password);
  	}

	/**
	 * @see PersistFactory#createTeacher()
	 */
	public Teacher createTeacher() 
	{
		return new TeacherJDBC(this.dbConnection);
	}

	/**
	 * @see PersistFactory#createTeaching()
	 */
	public Teaching createTeaching() 
	{
		return new TeachingJDBC(this.dbConnection);
	}
  
	/**
	 * @see PersistFactory#createBooking()
	 */
	public Booking createBooking() 
	{
		return new BookingJDBC(this.dbConnection);
	}

	/**
	 * @see PersistFactory#createFeature()
	 */
	public Feature createFeature()
	{
		return new FeatureJDBC(this.dbConnection);
	}

	/**
	 * @see PersistFactory#createSchedule()
	 */
	public Schedule createSchedule() 
	{
		return new ScheduleJDBC(this.dbConnection);
	}

	/**
	 * @see PersistFactory#createManager()
	 */
	public Manager createManager() 
	{
		return new ManagerJDBC(this.dbConnection);
	}
}
