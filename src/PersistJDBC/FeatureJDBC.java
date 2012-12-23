package PersistJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BL.Feature;

/**
 * Persistance des donn�es de la caract�ristique vers une base de donn�es.
 * @author URM Team
 */
class FeatureJDBC extends Feature 
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
	public FeatureJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Feature#load(String)
	 * @param id
	 * 			id de la caract�ristique � charger
	 */
	public void load(String id) throws Exception 
	{
		String query = "select count(*) from CARACTERISTIQUE where ID_CARACTERISTIQUE = '" + id + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);

		// v�rifier qu'il y a qu'une seule caract�ristique
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// R�cup�rer les infos du cr�neau
		query = "select * from CARACTERISTIQUE where ID_CARACTERISTIQUE = '" + id + "'";
		results = stmt.executeQuery(query);
		results.next();
		
		this.id = results.getString(1).trim();
		this.name = results.getString(2).trim();
		stmt.close();
		results.close();
	}

}
