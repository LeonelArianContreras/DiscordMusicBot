package dev.discordMusicBot.commands.databaseCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.models.UserEvent;
import dev.discordMusicBot.utils.LeoEvent;
import static dev.discordMusicBot.service.AuthService.registerUser;

public class Register implements Command {

    public void execute(LeoEvent event) {
        UserEvent userEvent = new UserEvent(event.getAuthor());
        event.argumentsVerification("register", "<password> <password>", 3, null);
        registerUser(userEvent.getUserId(), userEvent.getUsername(), event);
    }
}