package dev.discordMusicBot.commands;

import dev.discordMusicBot.utils.LeoEvent;

public interface Command {
    void execute(LeoEvent event); // In order to obligate all commands to have execute method
}