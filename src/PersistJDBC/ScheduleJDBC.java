package PersistJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BL.Schedule;

/**
 * Persistance des données du créneau vers une base de données.
 * @author URM Team
 */
class ScheduleJDBC extends Schedule 
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
	public ScheduleJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Schedule#load(String)
	 * @param id
	 * 			id du créneau à charger
	 */
	public void load(String id) throws Exception 
	{
		String query = "select count(*) from Crenaux where id_creneau = '" + id + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		
		// vérifier qu'il y a qu'un seul créneau
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// Récupérer les infos du créneau
		query = "select * from Crenaux where id_creneau = '" + id + "'";
		results = stmt.executeQuery(query);
		results.next();
		
		this.id = results.getString(1).trim();
		this.startTime = results.getString(2).trim();
		this.endTime = results.getString(3).trim();
		
		stmt.close();
		results.close();
	}

}
