package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import dev.discordMusicBot.bot.*;

public class SetPrefix implements Command {

    private final EventListener eventListener;

    public SetPrefix(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendTyping().queue();

        if(args.length < 2) {
            event.getChannel().sendMessage("Usage: " + eventListener.getPrefix() + "setPrefix <prefix>").queue();
            return;
        }

        eventListener.setPrefix(args[1]);
        event.getChannel().sendMessage("Prefix set to " + eventListener.getPrefix()).queue();

    }
}