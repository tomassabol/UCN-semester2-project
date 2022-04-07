package database;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBConnection {
	
	private static DBConnection instance = null;
	private Connection connection;
	
	/**
	 * Constructor for the DbConnection
	 */
	private DBConnection() throws SQLException {

		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setEncrypt(false);
		ds.setPortNumber(1433);
		ds.setUser("CSC-CSD-S211_10407575");
		ds.setPassword("Password1!");
		ds.setServerName("hildur.ucn.dk");
		ds.setDatabaseName("CSC-CSD-S211_10407575");
		connection = ds.getConnection();

	}
	
	/**
	 * @return the instance of DbConnection
	 * @throws SQLException
	 */
	public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * closes connection
	 * sets instance of class DBConnection to null
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException {
		connection.close();
		instance = null;
	}
	
	/**
	 * Prints out the information of the connection
	 * @param conn the connection to print out information about
	 */
	public void printSessionInfo(Connection conn) throws SQLException {
		
		PreparedStatement selectSessionInfo = null;
		
		selectSessionInfo = conn.prepareStatement("select @@SPID");
		ResultSet sessionInfoRows = selectSessionInfo.executeQuery();
		sessionInfoRows.next();
		System.out.println(Thread.currentThread().getName()+ " - Session: "+ sessionInfoRows.getInt(1) + ", IsolationLevel: "+ conn.getTransactionIsolation());	

	}
	
	/**
	 * Executes insert and gets the generated key of it
	 * @param ps the prepared statement to execute
	 * @return the generated key
	 */
	public int executeSqlInsertWithIdentity(PreparedStatement ps) throws SQLException {
		
		int res = -1;

		res = ps.executeUpdate();
		if (res > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);
		}

		return res;

	}

	/**
	 * Sets auto commit to false and starts the transaction
	 */
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	/**
	 * Commits transaction and sets the auto commit to true
	 */
	public void commitTransaction() {
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rollbacks transaction and sets the auto commit to true
	 */
	public void rollbackTransaction() {
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Sets the isolation level of the transaction
	 * @param level the level to set to
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}

}
