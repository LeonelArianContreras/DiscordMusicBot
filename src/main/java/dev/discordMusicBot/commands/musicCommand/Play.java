package dev.discordMusicBot.commands.musicCommand;

import lavalink.client.io.Link;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.service.music.MusicManager;
import dev.discordMusicBot.utils.LeoEvent;


public class Play implements Command {

    private final MusicManager musicManager;

    public Play(MusicManager musicManager) {
        this.musicManager = musicManager;
    }

    public void execute(LeoEvent event) {
        event.argumentsVerification("play", "<url>", 1, null);
        Guild guild = event.getGuild();
        Member member = event.getAuthor();
        VoiceChannel voiceChannel = (VoiceChannel) member.getVoiceState().getChannel();

        if (voiceChannel == null) {
            event.sendBasicMessage("You are not in a voice channel.");
            return;
        }

        Link link = musicManager.getLavalink().getLink(guild.getId());
        link.setChannel(voiceChannel.getId());
        String trackUrl = event.getArgument(1);
        musicManager.loadAndPlay(event, link.getPlayer(), trackUrl);
    }

}