package dev.discordMusicBot.commands.databaseCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.models.UserEvent;
import dev.discordMusicBot.utils.LeoEvent;

public class ChangePassword implements Command {

    public void execute(LeoEvent event) {
        UserEvent userEvent = new UserEvent(event.getAuthor());
        // ToDo

    }
}