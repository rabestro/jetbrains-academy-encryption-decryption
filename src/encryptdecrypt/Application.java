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

        final var message = config.isEncode()
                ? algorithm.encode(key, data)
                : algorithm.decode(key, data);

        writeData(message);
    }

    private String readData() {
        final var fileName = requireNonNull(
                config.getInputFileName(),
                "Neither data or input file is specified.");
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeData(final String message) {
        if (nonNull(config.getOutputFileName())) {
            try (final var out = new PrintStream(config.getOutputFileName())) {
                out.println(message);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }
    }

}
