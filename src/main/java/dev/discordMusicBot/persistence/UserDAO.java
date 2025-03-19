package dev.discordMusicBot.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;

public class UserDAO {

    public void insertUser(String discord_id, String name, String password) throws SQLException {
        String sql = "INSERT INTO users (name, discord_id, password) VALUES (?, ?, ?)";

        try(Connection connection = connect()) {
            PreparedStatement statement = connect().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, discord_id);
            statement.setString(3, password);

            statement.executeUpdate();
            System.out.println("Inserted user: " + name + " successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Inserted user: " + name + " failed");
        }
    }

    public void deleteUser(String discord_id) throws SQLException {
        String sql = "DELETE FROM users WHERE discord_id = ?";

        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, discord_id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Deleted user: " + discord_id + " successfully");
            } else {
                System.out.println("Deleted user: " + discord_id + " not founded");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Deleted user: " + discord_id + " failed");
        }
    }

    public void updateUser(String discord_id, String name, String password) throws SQLException { // I suppose that discord_id will never change
        String sql = "UPDATE users SET name = ?, password = ? WHERE discord_id = ?";

        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, discord_id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Updated user: " + name + " successfully");
            } else {
                System.out.println("Updated user: " + name + " not founded");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Updated user: " + name + " failed");
        }
    }

}