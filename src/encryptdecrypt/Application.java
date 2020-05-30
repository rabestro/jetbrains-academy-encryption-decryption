package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Application implements Runnable {
    private int key = 0;
    private String data = "";
    private String in = null;
    private String mode = "enc";
    private String out = null;
    private EncryptionAlgorithm algorithm = null;
    private String message;

    Application(String... args) {
        for (int i = 0; i < args.length - 1; ++i) {
            final var option = args[i];
            if (option.charAt(0) != '-') {
                continue;
            }
            final var value = args[++i];

            switch (option) {
                case "-alg":
                    algorithm = EncryptionAlgorithm.getInstance(value);
                    break;
                case "-mode":
                    mode = value;
                    break;
                case "-key":
                    try {
                        key = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        key = 0;
                    }
                    break;
                case "-in":
                    in = value;
                    break;
                case "-out":
                    out = value;
                    break;
                case "-data":
                    data = value;
            }
        }
        if (algorithm == null) {
            algorithm = new ShiftAlgorithm();
        }
    }

    @Override
    public void run() {
        read();
        process();
        write();
    }

    void process() {
        if ("enc".equals(mode)) {
            message = algorithm.encode(key, data);
        } else {
            message = algorithm.decode(key, data);
        }
    }

    void read() {
        if (data.isEmpty() && in != null) {
            try {
                data = new String(Files.readAllBytes(Paths.get(in)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void write() {
        if (out != null) {
            try (final var writer = new FileWriter(out)) {
                writer.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }
    }

}
