package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        final var task = new EncryptionDecryption(args);

        task.read();
        task.process();
        task.write();
    }
}
