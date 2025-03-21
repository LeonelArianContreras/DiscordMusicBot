package dev.discordMusicBot.commands.databaseCommand;

import static dev.discordMusicBot.service.AuthService.*;

public class ChangePassword extends UpdateField {

    public ChangePassword() {
        this.typeOfChange = ((event, discord_id) -> {
            event.argumentsVerification("changeusername", "<previous password> " +
                    "<new password> <new password>", 4, null);

            if(!areEqualPasswords(event, 2, 3)) {
                event.sendBasicMessage("Passwords do not match!");
                return;
            }
            updateUserField(discord_id, event, "password", event.getArgument(2));
        });
    }
}