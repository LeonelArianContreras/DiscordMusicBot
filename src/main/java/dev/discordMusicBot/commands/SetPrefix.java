package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import static dev.discordMusicBot.service.EmbedUtils.getEmbedPrefix;
import dev.discordMusicBot.bot.*;

public class SetPrefix implements Command {

    private final EventListener eventListener;

    public SetPrefix(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute(LeoEvent event) {
        event.argumentsVerification("setprefix", "<prefix>", 2, null);
        eventListener.setPrefix(event.getArgument(1));
        EmbedBuilder embed = getEmbedPrefix(event.getArgument(1));
        event.sendMessageAsEmbed(embed);
    }
}