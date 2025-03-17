package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;

public interface Command {
    void execute(LeoEvent event); // In order to obligate all comands to have execute method
}