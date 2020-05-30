package encryptdecrypt;

import static java.util.Objects.*;

public class Configuration {
    private int key;
    private String data;
    private String mode;
    private String inputFileName;
    private String outputFileName;
    private EncryptionAlgorithm algorithm;

    Configuration(String... args) {
        for (int i = 0; i < args.length - 1; ++i) {
            final var option = args[i];
            if (option.charAt(0) == '-') {
                setOption(option, args[++i]);
            }
        }
    }

    public String getMode() {
        return requireNonNullElse(mode, "enc");
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

    public boolean isEncoding() {
        return "enc".equals(getMode());
    }

    private void setOption(final String option, final String value) {
        switch (option) {
            case "-alg":
                algorithm = EncryptionAlgorithm.getInstance(value);
                break;
            case "-mode":
                mode = value;
                break;
            case "-key":
                key = Integer.parseInt(value);
                break;
            case "-in":
                inputFileName = value;
                break;
            case "-out":
                outputFileName = value;
                break;
            case "-data":
                data = value;
            default:
                throw new IllegalArgumentException("Illegal option: " + option);
        }
    }

}
