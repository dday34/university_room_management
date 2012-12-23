package PersistJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BL.Schedule;

/**
 * Persistance des donn�es du cr�neau vers une base de donn�es.
 * @author URM Team
 */
class ScheduleJDBC extends Schedule 
{
	/**
	 * Connection vers la base de donn�es.
	 */
	Connection dbConnection;

	/**
	 * Constructeur
	 * @param dbConnection
	 * 			Connection commune � la base de donn�es.
	 */
	public ScheduleJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Schedule#load(String)
	 * @param id
	 * 			id du cr�neau � charger
	 */
	public void load(String id) throws Exception 
	{
		String query = "select count(*) from Crenaux where id_creneau = '" + id + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		
		// v�rifier qu'il y a qu'un seul cr�neau
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// R�cup�rer les infos du cr�neau
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
