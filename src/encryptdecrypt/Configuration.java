package encryptdecrypt;

import encryptdecrypt.algorithm.EncryptionAlgorithm;
import encryptdecrypt.algorithm.EncryptionMode;
import encryptdecrypt.algorithm.ShiftAlgorithm;

import static java.util.Objects.*;

public class Configuration {
    private static final String OPTION_ALGORITHM = "-alg";
    private static final String OPTION_MODE = "-mode";
    private static final String OPTION_KEY = "-key";
    private static final String OPTION_INPUT_FILE = "-in";
    private static final String OPTION_OUTPUT_FILE = "-out";
    private static final String OPTION_DATA = "-data";

    private int key;
    private String data;
    private String inputFileName;
    private String outputFileName;
    private EncryptionMode mode;
    private EncryptionAlgorithm algorithm;

    Configuration(String... args) {
        for (int i = 0; i < args.length - 1; ++i) {
            final var option = args[i];
            if (option.charAt(0) == '-') {
                setOption(option, args[++i]);
            }
        }
    }

    public EncryptionMode getMode() {
        return requireNonNullElse(mode, EncryptionMode.ENC);
    }

    public EncryptionAlgorithm getAlgorithm() {
        return requireNonNullElseGet(algorithm, ShiftAlgorithm::new);
    }

    public String getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean isEncode() {
        return getMode() == EncryptionMode.ENC;
    }

    private void setOption(final String option, final String value) {
        switch (option) {
            case OPTION_ALGORITHM:
                algorithm = EncryptionAlgorithm.getInstance(value);
                break;
            case OPTION_MODE:
                mode = EncryptionMode.valueOf(value.toUpperCase());
                break;
            case OPTION_KEY:
                key = Integer.parseInt(value);
                break;
            case OPTION_INPUT_FILE:
                inputFileName = value;
                break;
            case OPTION_OUTPUT_FILE:
                outputFileName = value;
                break;
            case OPTION_DATA:
                data = value;
            default:
                throw new IllegalArgumentException("Illegal option: " + option);
        }
    }

}
