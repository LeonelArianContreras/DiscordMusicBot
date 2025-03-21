package dev.discordMusicBot.service;

import dev.discordMusicBot.persistence.UserDAO;
import dev.discordMusicBot.utils.LeoEvent;

public class AuthService {

    private static UserDAO userDAO = new UserDAO();

    public static boolean areEqualPasswords(LeoEvent event, int index1, int index2) {
        String onePassword = event.getArgument(index1);
        String secondPassword = event.getArgument(index2);
        return onePassword.equals(secondPassword);
    }

    public static boolean isPreviousPassword(String passwordInput, String discord_id) {
        String previousPassword = userDAO.getPassword(discord_id);
        return passwordInput.equals(previousPassword);
    }

    public static boolean isRegistered(LeoEvent event, String discord_id) {
        return userDAO.userExists(discord_id);
    }

    public static void registerUser(String discord_id, String name, LeoEvent event) {
        if(isRegistered(event, discord_id)) {
            event.sendBasicMessage("You are already registered!");
            return;
        }
        if(!areEqualPasswords(event, 1, 2)) {
            event.sendBasicMessage("Passwords do not match!");
            return;
        }
        String password = event.getArgument(2);
        userDAO.insertUser(discord_id, name, password);
        event.sendBasicMessage("Successfully registered!\nNow you are able to use special commands " +
                "such as !playlist");
    }

    public static void updateUserField(String discord_id, LeoEvent event, String field, String value) {

        if (!isRegistered(event, discord_id)) {
            event.sendBasicMessage("You need to register first!");
            return;
        }

        if (!isPreviousPassword(event.getArgument(1), discord_id)) {
            event.sendBasicMessage("Your password is incorrect!");
            return;
        }

        userDAO.updateUserByDiscordID(discord_id, field, value);
        event.sendBasicMessage(field.equals("password") ? "Password successfully updated!" : "Username successfully updated!");
    }

}