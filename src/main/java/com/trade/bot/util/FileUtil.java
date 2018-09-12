package com.trade.bot.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Ozan Ay
 */
public class FileUtil {
    private FileUtil() {}
    
    public static String read(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
