import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class XorCypher {

    public static String source = new String();
    public int a = 171;
    public int c = 11213;
    public int m = 256;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string to decrypt: ");
        source = sc.nextLine();

        Data data = encrypt(source);
        System.out.println("Encryption result as a hex-sequence: " + data.cypherToHex());
        System.out.println("Decryption result as a character sequence: " + decrypt(data));

    }

    public static Data encrypt(String s) {
        StringBuilder sb = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        int cypherBuf = rnd.nextInt() & Integer.MAX_VALUE;
        Data retData = new Data();
        retData.initVect = cypherBuf;

        ArrayList<Integer> cypher = new ArrayList<>();

        for (int sourceCharacter : s.toCharArray()) {
            int curGammaSegm = rnd.nextInt() & Integer.MAX_VALUE;
            retData.key.add(curGammaSegm);
            cypherBuf = sourceCharacter ^ curGammaSegm ^ cypherBuf;
            cypher.add(cypherBuf);
        }
        retData.cypher = cypher;
        retData.cypher.trimToSize();
        retData.key.trimToSize();
        return retData;
    }


    public static String decrypt(Data data){
        Integer[] decrypted = new Integer[data.cypher.size()];
        for(int i = data.cypher.size() - 1; i > 0; i--){
            decrypted[i] = (data.cypher.get(i) ^ data.key.get(i) ^ data.cypher.get(i - 1));
        }
        decrypted[0] = (data.cypher.get(0) ^ data.key.get(0) ^ data.initVect);


        StringBuilder sb = new StringBuilder();
        for(Integer c : decrypted){
            sb.append((char)(int)c);
        }
        return sb.toString();
    }
}
