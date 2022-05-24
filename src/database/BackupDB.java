package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.interfaces.BackupDBIF;

public class BackupDB implements BackupDBIF {

    private static final String BACKUP = "backup database [CSC-CSD-S211_10407575] to disk = 'C:/'";

    private PreparedStatement backup;

    /**
     * Constructor for the class BackupDB
     * @throws SQLException
     */
    public BackupDB() throws SQLException {
        backup = DBConnection.getInstance().getConnection().prepareStatement(BACKUP);
    }

    /**
     * backup of the database
     */
    public void backUp() throws SQLException {
        backup.execute();
    }

}
