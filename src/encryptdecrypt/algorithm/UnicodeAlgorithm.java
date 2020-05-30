package encryptdecrypt.algorithm;

public class UnicodeAlgorithm implements EncryptionAlgorithm {

    @Override
    public String encode(final int key, final String message) {
        return message.chars()
                .map(i -> encodeChar(key, i))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String decode(final int key, final String message) {
        return encode(-key, message);
    }

    private int encodeChar(final int key, final int symbol) {
        long result = symbol + key;

        if (result < 0) {
            result += Character.MAX_VALUE;
        }
        if (result > Character.MAX_VALUE) {
            result %= Character.MAX_VALUE;
        }
        return (int) result;
    }
}
