package dev.discordMusicBot.commands.databaseCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.models.UserEvent;
import dev.discordMusicBot.utils.LeoEvent;

import static dev.discordMusicBot.service.AuthService.*;

public class ChangePassword implements Command {

    public void execute(LeoEvent event) {
        UserEvent userEvent = new UserEvent(event.getAuthor());
        event.argumentsVerification("changePassword", "<previous password> " +
                "<new password> <new password>", 4, null);

        String discord_id = userEvent.getUserId();
        changePassword(discord_id, event);
    }
}