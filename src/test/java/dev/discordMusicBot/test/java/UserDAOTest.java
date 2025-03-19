package dev.discordMusicBot.test.java;

import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = connect();
        assertNotNull(connection); // Verify before each test that the connection is open
    }

}