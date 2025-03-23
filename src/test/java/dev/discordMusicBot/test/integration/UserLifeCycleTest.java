package dev.discordMusicBot.test.integration;

import dev.discordMusicBot.models.UserDB;
import dev.discordMusicBot.persistence.UserDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserLifeCycleTest {

    private final UserDAO userDAO = new UserDAO();
    private static Connection connection;

    private final String discord_id = "123456789";
    private final String name = "Pepe";
    private final String password = "Necroja1111";

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = connect();
        assertNotNull(connection);
    }

    @Test
    @Order(1)
    public void insertUserTest() {
        userDAO.insertUser(discord_id, name, password);
        UserDB userDB = userDAO.getUserByDiscordID(discord_id);
        assertNotNull(userDB, "userDB is found");
    }

    @Test
    @Order(2)
    public void changePasswordTest() {
        userDAO.updateUserByDiscordID(discord_id, "password", password);
        UserDB userDB = userDAO.getUserByDiscordID(discord_id);
        assertEquals(password, userDB.getPassword(), "Password are equals");
    }

    @Test
    @Order(3)
    public void changeNameTest() {
        userDAO.updateUserByDiscordID(discord_id, "name", name);
        UserDB userDB = userDAO.getUserByDiscordID(discord_id);
        assertEquals(name, userDB.getUsername(), "Username are equals");
    }

    @Test
    @Order(4)
    public void unregisterTest() {
        userDAO.deleteUserByDiscordID(discord_id);
        UserDB userDB = userDAO.getUserByDiscordID(discord_id);
        assertNull(userDB, "userDB is not found");
    }

    @AfterAll
    public static void shutDownConnection() throws SQLException {
        if(!connection.isClosed() && connection != null)
            connection.close();

    }

}