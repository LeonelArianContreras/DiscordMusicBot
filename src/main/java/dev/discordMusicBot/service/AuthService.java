package dev.discordMusicBot.service;

import dev.discordMusicBot.persistence.UserDAO;
import dev.discordMusicBot.utils.LeoEvent;

public class AuthService {

    private static UserDAO userDAO = new UserDAO();

    public static boolean areEqualPasswords(String onePassword, String anotherPassword) {
        return onePassword.equals(anotherPassword);
    }

    public static void registerUser(String discord_id, String name, LeoEvent event) {
        if(userDAO.userExists(discord_id)) {
            event.sendBasicMessage("Your are already registered!");
            return;
        }

        String onePassword = event.getArgument(1);
        String anotherPassword = event.getArgument(2);

        if(!areEqualPasswords(onePassword, anotherPassword)) {
            event.sendBasicMessage("The passwords do not match!");
            return;
        }

        userDAO.insertUser(discord_id, name, onePassword);
    }

}