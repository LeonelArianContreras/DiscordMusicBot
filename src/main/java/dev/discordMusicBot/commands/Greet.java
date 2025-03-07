package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;

public class Greet implements Command {

    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Hello, " + event.getAuthor().getEffectiveName() + "! \uD83D\uDC99");
        eb.setColor(0x1DA1F2);
        event.getChannel().sendMessageEmbeds(eb.build()).queue();
    }
}