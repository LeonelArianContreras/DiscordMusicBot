package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import dev.discordMusicBot.bot.*;

public class SetPrefix implements Command {

    private final EventListener eventListener;

    public SetPrefix(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute(LeoEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        if(event.getSizeOfMessage() < 2) {
            embed.setTitle("Usage: " + eventListener.getPrefix() + "setPrefix <prefix>");
            event.sendMessageAsEmbed(embed);
            return;
        }
        eventListener.setPrefix(event.getArgument(1));
        embed.setTitle("Set Prefix to " + eventListener.getPrefix());
        event.sendMessageAsEmbed(embed);
    }
}