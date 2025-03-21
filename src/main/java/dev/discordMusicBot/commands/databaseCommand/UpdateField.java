package dev.discordMusicBot.commands.databaseCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.models.UserEvent;
import dev.discordMusicBot.utils.LeoEvent;

import java.util.function.BiConsumer;

public class UpdateField implements Command {

    protected BiConsumer<LeoEvent, String> typeOfChange;

    public void execute(LeoEvent event) {
        UserEvent userEvent = new UserEvent(event.getAuthor());
        String discord_id = userEvent.getUserId();

        if (typeOfChange != null) {
            typeOfChange.accept(event, discord_id);
        }
    }
}