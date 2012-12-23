package PersistJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BL.Feature;

/**
 * Persistance des données de la caractéristique vers une base de données.
 * @author URM Team
 */
class FeatureJDBC extends Feature 
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
	public FeatureJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Feature#load(String)
	 * @param id
	 * 			id de la caractéristique à charger
	 */
	public void load(String id) throws Exception 
	{
		String query = "select count(*) from CARACTERISTIQUE where ID_CARACTERISTIQUE = '" + id + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);

		// vérifier qu'il y a qu'une seule caractéristique
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// Récupérer les infos du créneau
		query = "select * from CARACTERISTIQUE where ID_CARACTERISTIQUE = '" + id + "'";
		results = stmt.executeQuery(query);
		results.next();
		
		this.id = results.getString(1).trim();
		this.name = results.getString(2).trim();
		stmt.close();
		results.close();
	}

}
