package encryptdecrypt.algorithm;

public interface EncryptionAlgorithm {
    String ALGORITHM_SHIFT = "shift";
    String ALGORITHM_UNICODE = "unicode";

    static EncryptionAlgorithm getInstance(String type) {
        switch (type) {
            case ALGORITHM_SHIFT:
                return new ShiftAlgorithm();
            case ALGORITHM_UNICODE:
                return new UnicodeAlgorithm();
            default:
                throw new IllegalArgumentException("Unrecognized algorithm.");
        }
    }

    String encode(final int key, final String message);

    String decode(final int key, final String message);
}
