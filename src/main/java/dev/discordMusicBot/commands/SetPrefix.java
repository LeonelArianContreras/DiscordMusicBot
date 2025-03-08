package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import dev.discordMusicBot.bot.*;

public class SetPrefix implements Command {

    private final EventListener eventListener;

    public SetPrefix(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder embed = new EmbedBuilder();
        if(args.length < 2) {
            embed.setTitle("Usage: " + eventListener.getPrefix() + "setPrefix <prefix>");
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            return;
        }
        eventListener.setPrefix(args[1]);
        embed.setTitle("Set Prefix to " + eventListener.getPrefix());
        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}