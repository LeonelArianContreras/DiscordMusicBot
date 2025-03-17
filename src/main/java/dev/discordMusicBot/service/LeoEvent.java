package dev.discordMusicBot.service;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class LeoEvent {

    private final MessageReceivedEvent event;

    public LeoEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    public int getSizeOfMessage() {
        return getMessageContent().length;
    }

    public String[] getMessageContent() {
        return event.getMessage().getContentRaw().split("\\s+");
    }

    public List<Member> getMentionedMembers() {
        return event.getMessage().getMentions().getMembers();
    }

    public Member getMentionedMember(int index) {
        return getMentionedMembers().get(index);
    }

    public String getAvatarUrlOfMentionedMember(int index) {
        return getMentionedMember(index).getEffectiveAvatarUrl();
    }

    public Member getAuthor() {
        return event.getMember();
    }

    public String getAuthorEffectiveName() {
        return getAuthor().getEffectiveName();
    }

    public String getAuthorEffectiveAvatarUrl() {
        return getAuthor().getEffectiveAvatarUrl();
    }

    public String getArgument(int index) {
        return getMessageContent()[index];
    }

    public void sendBasicMessage(String message) {
        event.getChannel().sendMessage(message).queue();
    }

    public void sendMessageAsEmbed(EmbedBuilder embed) {
        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }

    public void sendFile(InputStream inputStream, String fileName) {
        event.getChannel().sendFiles(FileUpload.fromData(inputStream, fileName)).queue(); // ToDo: delegate to another utils class
    }

    public void sendEmbedWithFile(EmbedBuilder embed, File file) {
        event.getChannel().sendMessageEmbeds(embed.build())
                .addFiles(FileUpload.fromData(file))
                .queue();
    }

}