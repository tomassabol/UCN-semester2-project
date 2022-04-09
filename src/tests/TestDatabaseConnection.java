package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBConnection;

public class TestDatabaseConnection {

	Connection con;

	@BeforeEach
	public void setUp() throws SQLException {
		con = DBConnection.getInstance().getConnection();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull(con, "Connection succesfully established");	
	}
	
	@AfterEach
	public void cleanUp() throws SQLException {
		DBConnection.getInstance().disconnect();
	}

}
