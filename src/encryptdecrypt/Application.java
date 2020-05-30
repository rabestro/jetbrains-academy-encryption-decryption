package encryptdecrypt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Objects.*;

class Application implements Runnable {
    private final Configuration config;

    Application(final Configuration config) {
        this.config = config;
    }

    @Override
    public void run() {
        final var algorithm = config.getAlgorithm();
        final var key = config.getKey();
        final var data = requireNonNullElseGet(config.getData(), this::readData);

        final var message = config.isEncoding()
                ? algorithm.encode(key, data)
                : algorithm.decode(key, data);

        writeData(message);
    }

    private String readData() {
        try {
            return new String(Files.readAllBytes(Paths.get(
                    requireNonNull(config.getInputFileName(),
                            "Neither data or input file is specified."))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeData(final String message) {
        if (nonNull(config.getOutputFileName())) {
            try {
                new PrintStream(config.getOutputFileName()).println(message);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }
    }
}
