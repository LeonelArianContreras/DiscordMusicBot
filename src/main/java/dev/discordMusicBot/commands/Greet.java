package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;

public class Greet implements Command {

    public void execute(LeoEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Hello, " + event.getAuthorEffectiveName() + "! \uD83D\uDC99");
        eb.setColor(0x1DA1F2);
        event.sendMessageAsEmbed(eb);
    }
}