package com.rduda.Model;

/**
 * @author Robin Duda
 *
 * Matches bits from a string.
 */
public class ByteMatcher {
    private static final char UNKNOWN_TOKEN = '?';

    /**
     * Matches bits of a string.
     * @param password the string to be matched.
     * @param bytes number of bytes to match.
     * @return the matching bytes padded with a token.
     */
    public static String match(String password, int bytes) {
        return matchBytes(password, bytes) + addPadding(password, bytes);
    }

    private static String matchBytes(String password, int bytes) {
        int index = 0;
        StringBuilder match = new StringBuilder(password.length());

        while (index < bytes) {
            match.append(Character.toString(password.charAt(index)));
            index += 1;
        }

        return match.toString();
    }

    private static String addPadding(String password, int bytes) {
        int padding = (password.length() - bytes);
        StringBuilder result = new StringBuilder(password.length());

        for (int i = 0; i < padding; i++)
            result.append(UNKNOWN_TOKEN);

        return result.toString();
    }
}
