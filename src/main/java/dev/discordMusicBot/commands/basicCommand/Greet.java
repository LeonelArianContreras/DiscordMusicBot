package dev.discordMusicBot.commands.basicCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.utils.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import static dev.discordMusicBot.utils.EmbedUtils.getEmbedSpeech;

public class Greet implements Command {

    public void execute(LeoEvent event) {
        EmbedBuilder eb = getEmbedSpeech(event.getAuthorEffectiveName());
        event.sendMessageAsEmbed(eb);
    }
}