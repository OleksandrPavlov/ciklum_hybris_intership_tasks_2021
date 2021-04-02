package lesson5;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put("Brave", "ready face and endure danger or pain");
        dictionary.put("Brilliant", "exceptionally cleaver or talented");
        dictionary.put("Joy", "a feeling of grade pleasure and happiness");
        dictionary.put("Confidence", "ready face and endure danger or pain");

        dictionary.put("Brilliant","xxxxxxxxxx");

//        for(String value: dictionary.values()){
//            System.out.println(value);
//        }
        for(Map.Entry<String,String> entry:dictionary.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
