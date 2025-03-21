package dev.discordMusicBot.commands.databaseCommand;

import static dev.discordMusicBot.service.AuthService.*;

public class ChangeUsername extends UpdateField {

    public ChangeUsername() {
        this.typeOfChange = ((event, discord_id) -> {
            event.argumentsVerification("changeusername", "<password> <new username>", 3, null);
            updateUserField(discord_id, event, "name", event.getArgument(2));
        } );
    }

}