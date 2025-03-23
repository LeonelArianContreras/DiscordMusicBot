package dev.discordMusicBot.commands.databaseCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.models.UserEvent;
import dev.discordMusicBot.utils.LeoEvent;

import static dev.discordMusicBot.service.AuthService.isPreviousPassword;
import static dev.discordMusicBot.service.AuthService.removeFromDatabase;

public class Unregister implements Command {

     public void execute(LeoEvent event) {
          event.argumentsVerification("unregister", "<password>", 1, null);
          UserEvent userEvent = new UserEvent(event.getAuthor());
          if(!isPreviousPassword(event.getArgument(1), userEvent.getUserId())) {
               event.sendBasicMessage("Passwords do not match!");
               return;
          }
          removeFromDatabase(event, userEvent.getUserId());
     }
}