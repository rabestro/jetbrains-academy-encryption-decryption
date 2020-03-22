package encryptdecrypt;

public interface EncryptionAlgorithm {

    static EncryptionAlgorithm getInstance(String type) {
        switch (type) {
            case "shift":
                return new ShiftAlgorithm();
            case "unicode":
                return new UnicodeAlgorithm();
            default:
                return null;
        }
    }

    String encode(final int key, final String message);

    String decode(final int key, final String message);
}
