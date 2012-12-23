package PersistJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BL.Teaching;

/**
 * Persistance des donn�es de l'enseignement vers une base de donn�es.
 * @author URM Team
 */
class TeachingJDBC extends Teaching 
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
	public TeachingJDBC(Connection dbConnection) 
	{
		this.dbConnection = dbConnection;
	}

	/**
	 * @see Teaching#load(String)
	 * @param reference
	 * 			r�f�rence de l'enseignement � charger
	 */
	public void load(String reference) throws Exception 
	{
		String idField;
		
		// v�rifier qu'il y a qu'un seul enseignement
		String query = "select count(*) from ENSEIGNEMENT where ID_ENSEIGNEMENT = '" + reference + "'";
		Statement stmt = dbConnection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		results.next();
		if(results.getInt(1) != 1)
		{
			throw new SQLException(); 
		}

		// R�cup�rer les infos de l'enseignement
		query = "select * from ENSEIGNEMENT where ID_ENSEIGNEMENT = '" + reference + "'";
		results = stmt.executeQuery(query);
		results.next();
		
		// on r�cup�re l'id
		this.id = results.getString(1).trim();

		// on r�cup�re les heures
		this.hours = results.getInt(5);
		
		// on r�cup�re le groupe
		String query1 = "select g.LIBELLE_GROUPE from GROUPE g, ENSEIGNEMENT e where e.ID_ENSEIGNEMENT = '" + reference + "' and e.ID_GROUPE=g.ID_GROUPE";
		Statement stmt1 = dbConnection.createStatement();
		ResultSet results1 = stmt1.executeQuery(query1);
		results1.next();
		this.group = results1.getString(1).trim();
		
		// R�cup�rer l'intitul� du cours
		idField = results.getString(2).trim();
		query = "select COUNT(*) from COURS c, MATIERE m where c.ID_MATIERE = m.ID_MATIERE and c.ID_COURS = '" + idField + "'";
		results = stmt.executeQuery(query);
		results.next();
		if(results.getString(1).trim() == "")
		{
			throw new SQLException(); 
		}
		query = "select m.LIBELLE_MATIERE from COURS c, MATIERE m where c.ID_MATIERE = m.ID_MATIERE and c.ID_COURS = '" + idField + "'";
		results = stmt.executeQuery(query);
		results.next();
		this.field = results.getString(1).trim();
		
		// R�cup�rer le type de l'enseignement
		query1 = "select tc.LIBELLE_TYPE_DE_COURS from COURS c, TYPECOURS tc, ENSEIGNEMENT e where e.ID_ENSEIGNEMENT = '" + reference + "' and c.ID_TYPE_DE_COURS=tc.ID_TYPE_DE_COURS and c.ID_COURS=e.ID_COURS";
		stmt1 = dbConnection.createStatement();
		results1 = stmt1.executeQuery(query1);
		results1.next();
		this.type = results1.getString(1).trim();
		
		stmt.close();
		results.close();
		stmt1.close();
		results1.close();
	}

}
