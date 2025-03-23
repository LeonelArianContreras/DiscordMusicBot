package dev.discordMusicBot.test.unit;

import dev.discordMusicBot.models.UserDB;
import dev.discordMusicBot.persistence.UserDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {

    private final UserDAO userDAO = new UserDAO();
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = connect();
        assertNotNull(connection); // Verify before each test that the connection is open
    }

    @Test
    public void testInsertUser() {
        userDAO.insertUser("1999", "Santi", "contrasenia");
        UserDB userDB = userDAO.getUserByDiscordID("1999");
        assertNotNull(userDB, "User should be inserted into database successfully");
    }

    @AfterEach
    public void cleanUpAndShutDown() throws SQLException {
        connection.prepareStatement("DELETE FROM users WHERE discord_id = '1999'").executeUpdate();
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}