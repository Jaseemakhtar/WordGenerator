import java.net.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class WordGenerator{
    static int count = 0;
    static Set<String> words = new HashSet<>();
    static JSONObject jsonObject;
    static String json;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String word;
        int opt;
        boolean run = true;
        download();

        while(run){

            print("\n1.Find Out?");
            print("2.Exit");
            print("Enter the option: ");
            opt = scan.nextInt();

            switch(opt){
                case 1:
                    print("\n");
                    print("Enter the word");
                    word = scan.next();
                    print("\n\nBelow are the possible words.\n\n");
                    for(int c = 2; c <= word.length(); c++){
                        combination(word, "", word.length(), c); 
                    }

                    for (String s : words) {
                        System.out.println(s);
                    }

                    print("Total Possible words: " + words.size());
                    count = 0;
                    break;

                default:
                    print("Exiting...");
                    run = false;
                    break;
            }
        }
    }

    public static void combination(String whole, String prefix, int n, int k){
        if (k == 0) { 
            if(jsonObject.has(prefix)){
                words.add(prefix);
            }
            count++;
            return; 
        } 

    
        for (int i = 0; i < n; ++i) { 
            String newPrefix = prefix + whole.charAt(i); 
            combination(whole, newPrefix, n, k - 1); 
        }
    }

    public static void download(){
        try{
            String loader = "\\|/-";
            int lCounter = 0;
            URL url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words_dictionary.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String read;
            StringBuilder jsonString = new StringBuilder();
            while((read = bufferedReader.readLine()) != null){
                jsonString.append(read);
                String l = "\r" + "( " + loader.charAt(lCounter) + " ) Initializing...";
                System.out.write(l.getBytes());
                lCounter = (lCounter + 1) % loader.length();
            }
            
            print("\nInitialization Successfully.");

            json = jsonString.toString();
            jsonObject = new JSONObject(json);
            
            inputStream.close();
        }catch(Exception e){
            print("Error:\n" + e.toString());
        }
    }

    public static void print(Object o){
        System.out.println(String.valueOf(o));
    }
}