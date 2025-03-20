package dev.discordMusicBot.commands;

import dev.discordMusicBot.utils.ImageUtils;
import dev.discordMusicBot.utils.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.File;

import static dev.discordMusicBot.utils.EmbedUtils.*;

public class GifCommand implements Command {

    private final String command;

    public GifCommand(String command) {
        this.command = command;
    }

    public void execute(LeoEvent event) {
        event.argumentsVerification(command, "<someone>", 2, null);
        ImageUtils imageUtils = new ImageUtils("gifs/" + command, ".gif");
        File randomGif = imageUtils.getFile();
        String huggedMember = event.getEffectiveNameOfMentionedMember(0);

        EmbedBuilder eb = getEmbedGif(event.getAuthorEffectiveName(), huggedMember, command);
        event.sendEmbedWithFile(eb, randomGif);
    }
}
