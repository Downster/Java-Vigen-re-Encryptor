import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Encryption {
    private List<Integer> cypher = new ArrayList<>();
    private int length;

    public Encryption(int length){
        Random rand = new Random();
        this.length = length;
        for (int i = 0; i < length; i++){
            cypher.add(rand.nextInt(9));
        }
    }

    public List<Integer> getCypher() {
        return cypher;
    }


    public String encryptText(Encryptor file){
        int cypherIndex = 0;
        StringBuilder outputFile = new StringBuilder();
        char[] characters = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".toCharArray();
        HashMap<Character, Character> encryptionKey = new HashMap<>();
        for (int i = 0; i < characters.length; i++){
            if (cypherIndex < length){
                if ((i + cypher.get(cypherIndex)) < characters.length) {
                    encryptionKey.put(characters[i], characters[i + cypher.get(cypherIndex)]);
                    cypherIndex++;
                } else {
                    encryptionKey.put(characters[i], characters[Math.abs(i + cypher.get(cypherIndex) - characters.length - 1)]);
                    cypherIndex++;
                }
            }else {
                cypherIndex = 0;
                if ((i + cypher.get(cypherIndex)) < characters.length) {
                    encryptionKey.put(characters[i], characters[i + cypher.get(cypherIndex)]);
                    cypherIndex++;
                } else {
                    encryptionKey.put(characters[i], characters[Math.abs(i + cypher.get(cypherIndex) - characters.length - 1)]);
                    cypherIndex++;
                }
            }
        }
        char[] inputFile = file.toString().toCharArray();
        for (char c : inputFile){
            outputFile.append(encryptionKey.get(c));
        }
        return outputFile.toString();

    }

}
