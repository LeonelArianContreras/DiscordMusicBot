package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import static dev.discordMusicBot.service.EmbedUtils.getEmbedSpeech;

public class Greet implements Command {

    public void execute(LeoEvent event) {
        EmbedBuilder eb = getEmbedSpeech(event.getAuthorEffectiveName());
        event.sendMessageAsEmbed(eb);
    }
}