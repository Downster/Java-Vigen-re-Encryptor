import java.io.*;

public class Encryptor {
    private String filename;
    private Encryption encryption;
    private String fileContent;

    public Encryptor(String filename, String fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public String toString() {
        return this.fileContent;
    }

    public static void main(String[] args) throws IOException {
        try {
            startEncryptor();
        } catch (IOException e) {
            System.out.println("Please input a proper directory");
        }

    }

    public static void startEncryptor() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input a file directory to encrypt: ");
        String fileName = reader.readLine();
        String fileContent = readFile(fileName);
        Encryptor file = new Encryptor(fileName, fileContent);
        System.out.println("Declare cypher length: ");
        file.setEncryption(new Encryption(Integer.parseInt(reader.readLine())));
        String encryptedText = encryptFile(file);
        System.out.println(file.encryption.getCypher());
        System.out.println(encryptedText);
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while (fileReader.ready()) {
            fileContent.append(fileReader.readLine());
        }
        return fileContent.toString();

    }

    public static String encryptFile(Encryptor file){
        return file.encryption.encryptText(file);
    }

}
