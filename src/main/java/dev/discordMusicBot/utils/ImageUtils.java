package dev.discordMusicBot.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageUtils {

    private final String directory;
    private final String typeOfFile;

    public ImageUtils(String directory, String typeOfFile) {
        this.directory = directory;
        this.typeOfFile = typeOfFile;
    }

    public BufferedImage getBufferedImage() {
        File randomFile = getFile();
        try {
            return ImageIO.read(randomFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public File getFolder() {
        return new File("src/main/resources/" + directory);
    }

    public File[] getFilesInFolder() {
        return getFolder().listFiles((dir, name) -> name.endsWith(typeOfFile));
    }

    public void folderVerification(File folder) {
        if(!folder.exists() || folder.listFiles() == null)
            System.err.println("Images folder not found: " + folder.getAbsolutePath());
    }

    public File getFile() {
        File folder = getFolder();
        folderVerification(folder);
        File[] files = getFilesInFolder();
        return files[new Random().nextInt(files.length)];
    }

}