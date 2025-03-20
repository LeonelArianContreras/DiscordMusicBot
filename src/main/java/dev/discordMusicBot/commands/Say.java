package dev.discordMusicBot.commands;

import dev.discordMusicBot.utils.LeoEvent;

public class Say implements Command {

    public void execute(LeoEvent event) {
        event.argumentsVerification("say", "<something>", 2, null);
        event.sendString();
    }
}