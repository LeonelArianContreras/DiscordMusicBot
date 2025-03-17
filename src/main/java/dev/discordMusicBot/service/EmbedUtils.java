package dev.discordMusicBot.service;

import dev.discordMusicBot.models.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

// Embed lets us beautify the bot interactions
public class EmbedUtils {

    public static EmbedBuilder getEmbedAvatar(Member member) {
        User user = new User(member);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Avatar de: " + member.getEffectiveName());
        embed.setImage(user.getAvatarUrl());
        embed.setColor(0x1DA1F2);

        return embed;
    }

    public static EmbedBuilder getUserData(Member member) {
        User user = new User(member);
        return getEmbedUserInfo(user);
    }

    private static EmbedBuilder getEmbedUserInfo(User user) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("User Info");
        eb.setThumbnail(user.getAvatarUrl());
        eb.addField("Nickname", user.getNickname(), false);
        eb.addField("Username", user.getUsername(), false);
        eb.addField("UserID", user.getUserId(), false);
        eb.addField("Date Joined", user.getJoinDate(), false);
        eb.addField("Roles", user.getRoles(), false);
        eb.setColor(0x1DA1F2);
        return eb;
    }

    public static EmbedBuilder getEmbedGif(String authorEffectiveName, String huggedMemberEffectiveName, String command) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(authorEffectiveName + " has given a " + command + " to " + huggedMemberEffectiveName);
        eb.setColor(0xFF69B4); // Pink colour
        return eb;
    }

    public static EmbedBuilder getEmbedSpeech(String authorName) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Hello, " + authorName + "! \uD83D\uDC99");
        eb.setColor(0x1DA1F2);
        return eb;
    }


}