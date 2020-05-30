package encryptdecrypt.algorithm;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isUpperCase;

public class ShiftAlgorithm implements EncryptionAlgorithm {
    private static final int ALPHABET_LETTERS_COUNT = 26;

    @Override
    public String encode(final int key, final String data) {
        final var sb = new StringBuilder();

        for (final var symbol : data.toCharArray()) {
            if (isAlphabetic(symbol)) {
                final char startLetter = isUpperCase(symbol) ? 'A' : 'a';
                sb.append((char) (startLetter + (symbol - startLetter + key) % ALPHABET_LETTERS_COUNT));
            } else {
                sb.append(symbol);
            }
        }
        return sb.toString();
    }

    @Override
    public String decode(final int key, final String data) {
        final var sb = new StringBuilder();

        for (final var symbol : data.toCharArray()) {
            if (isAlphabetic(symbol)) {
                final var endLetter = isUpperCase(symbol) ? 'Z' : 'z';
                sb.append((char) (endLetter - (endLetter - symbol + key) % ALPHABET_LETTERS_COUNT));
            } else {
                sb.append(symbol);
            }
        }
        return sb.toString();
    }

}
