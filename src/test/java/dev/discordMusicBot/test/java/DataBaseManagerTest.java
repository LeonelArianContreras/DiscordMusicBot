package dev.discordMusicBot.test.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;
import static org.junit.jupiter.api.Assertions.*;

public class DataBaseManagerTest {

    private Connection connection;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = connect();
        assertNotNull(connection); // Verify before each test that the connection is open
    }

    @Test
    public void testUsersTableNotEmpty() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users")) {

                 assertTrue(resultSet.next(), "Count Table should exist");
                 int count = resultSet.getInt(1);
                 assertTrue(count > 0, "Count Table should have at least 1 element");

        } catch (SQLException e) {
            fail("Exception occurred while testing users table");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }

}