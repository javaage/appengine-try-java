/**
 * 
 */
package myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author P9030576
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String instanceConnectionName = "newagent-25039:asia-northeast1:la";

		// TODO: fill this in
		// The database from which to list tables.
		String databaseName = "la";

		String username = "postgres";

		// TODO: fill this in
		// This is the password that was set via the Cloud Console or empty if
		// never set
		// (not recommended).
		String password = "la123456";

		// [START doc-example]
		String jdbcUrl = String
				.format("jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory"
						+ "&socketFactoryArg=%s", databaseName, instanceConnectionName);

		Connection connection;
		try {
			Class.forName("org.postgresql.Driver");
			// connection = DriverManager.getConnection(jdbcUrl, username,
			// password);
			connection = DriverManager.getConnection("jdbc:postgresql://192.168.120.128:5432/la", "postgres",
					"19786028");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM LA_USER");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + "." + resultSet.getString(2));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		// [END doc-example]

	}

}
