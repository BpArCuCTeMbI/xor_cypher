import java.util.ArrayList;

public class Data {
    public ArrayList<Integer> cypher;
    public ArrayList<Integer> key;
    public int initVect;

    public Data(){
        key = new ArrayList<>();
        cypher = new ArrayList<>();
    }

    public String cypherToHex(){
        StringBuilder sb = new StringBuilder();
        for(Integer c : cypher){
            sb.append(Integer.toHexString(c) + " ");
        }
        return sb.toString();
    }

}
