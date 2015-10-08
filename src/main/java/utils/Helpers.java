package utils;

import java.util.Random;

/**
 * Created by tymofiymishutin on 08/10/15.
 */
public class Helpers {

    public String generateRandom(int lenght, boolean withNumbers)
    {
        String defaultChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (withNumbers) {
            defaultChars += "0123456789";
        }

        char[] chars = defaultChars.toCharArray();
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < lenght; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

}
