package PersistJDBC;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import BL.Booking;
import BL.PersistFactory;
import BL.Teacher;
import BL.Teaching;

/**
 * Persistance des données d'un enseignant vers une base de données.
 * @author URM Team
 */
class TeacherJDBC extends Teacher 
{
	/**
	 * Connection vers la base de données.
	 */
	Connection dbConnection;

	/**
	 * Constructeur
	 * @param dbConnection
	 * 			Connection commune à la base de données.
	 */
	public TeacherJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Teacher#load(String, String)
	 * @param pseudo
	 * @param pwd
	 * @throws Exception 
	 * 			Problème de récupération des données dans la base SQL
	 */
	public void load(String pseudo, String pwd) throws Exception
	{
		String query = "select count(*) from enseignant where nom = '" + pseudo + "' and mdp = '" + pwd + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);

		// vérifier qu'il y a qu'un seul enseignant
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// Récupérer les infos de l'enseignant
		query = "select * from enseignant where nom = '" + pseudo + "' and mdp = '" + pwd + "'";
		results = stmt.executeQuery(query);

		results.next();
		this.id = results.getString(1).trim();
		this.lastName = results.getString(2).trim();
		this.firstName = results.getString(3).trim();
		this.password = results.getString(4).trim();
		this.superUser = results.getBoolean(5);
		
		ArrayList<Teaching> scs = new ArrayList<Teaching>();

		String query3 = "select * from ENSEIGNEMENT e where e.ID_ENSEIGNANT='"+this.id+"'";
		Statement stmt3 = dbConnection.createStatement();
		ResultSet results3 = stmt3.executeQuery(query3);

		while(results3.next())
		{
			Teaching tc = PersistFactory.getInstance().createTeaching();
			tc.load(results3.getString(1));
			tc.setTeacher(this);
			scs.add(tc);
		}
		this.myTeachings = scs;
		stmt.close();
		results.close();
		stmt3.close();
		results3.close();
	}

	/**
	 * @see Teacher#getValidBooking(int)
	 * @param week
	 * 			numéro de la semaine dont on veut les réservations validées
	 */
	public ArrayList<Booking> getValidBooking(int week) throws Exception 
	{
		ArrayList<Booking> listBookings = new ArrayList<Booking>();
		Calendar cal = new GregorianCalendar();
		String debut = new String();
		String fin = new String();

		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		debut += (cal.get(Calendar.DAY_OF_MONTH) < 10)?"0" + cal.get(Calendar.DAY_OF_MONTH):cal.get(Calendar.DAY_OF_MONTH);
		debut += '-';
		debut += (cal.get(Calendar.MONTH) < 9)?"0" + (cal.get(Calendar.MONTH) + 1):cal.get(Calendar.MONTH) + 1;
		debut += '-';
		debut += cal.get(Calendar.YEAR);

		cal.add(Calendar.DAY_OF_WEEK, 6);
		fin += (cal.get(Calendar.DAY_OF_MONTH) < 10)?"0" + cal.get(Calendar.DAY_OF_MONTH):cal.get(Calendar.DAY_OF_MONTH);
		fin += '-';
		fin += (cal.get(Calendar.MONTH) < 9)?"0" + (cal.get(Calendar.MONTH) + 1):cal.get(Calendar.MONTH) + 1;
		fin += '-';
		fin += cal.get(Calendar.YEAR);
		

		String query = "select res.ID_RESERVATION from RESERVATION res, ENSEIGNEMENT ens where ID_SALLE is not null and res.id_enseignement = ens.id_enseignement AND ens.id_enseignant = '" 
				+ this.id + "' and DATE_RESERVATION >= '"+debut+"' AND DATE_RESERVATION <= '"+fin+"'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		
		while(results.next())
		{
			Booking booking = new BookingJDBC(dbConnection);
			booking.load(results.getString(1).trim());
			listBookings.add(booking);
		}
		
		stmt.close();
		results.close();

		return listBookings;
	}

}
