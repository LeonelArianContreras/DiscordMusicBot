package dev.discordMusicBot.persistence;

import dev.discordMusicBot.models.UserDB;

import java.sql.*;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;

public class UserDAO {

    public void insertUser(String discord_id, String name, String password) {
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

    public void deleteUserByDiscordID(String discord_id) {
        String sql = "DELETE FROM users WHERE discord_id = ?";

        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, discord_id);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Deleted user successfully" : "Deleted user failed");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Deleted user: " + discord_id + " failed");
        }
    }

    public void updateUserByDiscordID(String discord_id, String name, String password) { // I suppose that discord_id will never change
        String sql = "UPDATE users SET name = ?, password = ? WHERE discord_id = ?";

        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, discord_id);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected > 0 ? "User deleted" : "User not found");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Updated user: " + name + " failed");
        }
    }

    public UserDB getUserByDiscordID(String discord_id) {
        String sql = "SELECT * FROM users WHERE discord_id = ?";

        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, discord_id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new UserDB(
                        resultSet.getString("name"),
                        resultSet.getString("discord_id"),
                        resultSet.getString("password"),
                        resultSet.getInt("user_id")
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("User not found");
        }

        System.out.println("No user found with discord_id: " + discord_id);
        return null;
    }

    public boolean userExists(String discord_id) {
        return getUserByDiscordID(discord_id) != null;
    }

    public boolean passwordIsCorrect(String discord_id, String password) { // ToDo
        return true;
    }

}