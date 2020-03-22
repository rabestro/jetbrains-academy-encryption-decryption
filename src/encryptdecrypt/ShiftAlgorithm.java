package encryptdecrypt;

public class ShiftAlgorithm implements EncryptionAlgorithm {

    @Override
    public String encode(final int key, final String message) {
        System.out.printf("SA:Key=%d Message: %s%n", key, message);
        return message.chars()
                .map(i -> i >= 'a' && i <= 'z' ? 'a' + (i - 'a' + key) % ('z' - 'a' + 1) : i)
                .map(i -> i >= 'A' && i <= 'Z' ? 'A' + (i - 'A' + key) % ('Z' - 'A' + 1) : i)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String decode(final int key, final String message) {
        System.out.printf("Shift Algorithm (decode): Key=%d%nMessage: %s%n", key, message);
        return message.chars()
                .map(symbol -> decodeChar(key, symbol))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private int decodeChar(final int key, int symbol) {
        if (symbol >= 'a' && symbol <= 'z') {
            symbol -= 'a' + key;
            if (symbol < 0) {
                symbol += 26;
            }
            symbol += 'a';
        } else if (symbol >= 'A' && symbol <= 'Z') {
            symbol = symbol - 'A' - key;
            symbol = 'A' + (symbol < 0 ? symbol + 26 : symbol);
        }
        return symbol;
    }
}
