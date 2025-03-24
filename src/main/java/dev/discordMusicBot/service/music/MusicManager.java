package dev.discordMusicBot.service.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.discordMusicBot.utils.LeoEvent;
import lavalink.client.io.Lavalink;
import lavalink.client.io.Link;
import lavalink.client.player.LavalinkPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private final Lavalink lavalink;
    private final Map<Long, Link> links;

    public MusicManager(Lavalink lavalink) {
        this.lavalink = lavalink;
        this.links = new HashMap<>();
    }

    public LavalinkPlayer getPlayer(Guild guild) {
        return links.computeIfAbsent(guild.getIdLong(), id -> {
            Link link = lavalink.getLink(guild.getId());

            AudioChannel channel = guild.getAudioManager().getConnectedChannel();
            if (channel != null && link.getState() != Link.State.CONNECTED) {
                link.setChannel(channel.getId()); // ← Usar setChannel en lugar de connect
            }
            return link;
        }).getPlayer();
    }

    public void loadAndPlay(LeoEvent event, LavalinkPlayer player, String trackUrl) {
        lavalink.getAudioPlayerManager().loadItem(trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                player.playTrack(track);
                event.sendBasicMessage("Now playing: " + track.getInfo().title);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getTracks().get(0);
                player.playTrack(firstTrack);
                event.sendBasicMessage("Now playing: " + firstTrack.getInfo().title);
            }

            @Override
            public void noMatches() {
                event.sendBasicMessage("No matches found for: " + trackUrl);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                event.sendBasicMessage("Could not play: " + exception.getMessage());
            }
        });
    }

    public Lavalink getLavalink() {
        return lavalink;
    }
}
