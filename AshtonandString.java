import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'ashtonString' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    static char ashtonString(String a, int k) {

        TreeSet<String> subStringSet = new TreeSet<>();
        TreeSet<String> resultSet = new TreeSet<>();

        int index = 0;
        int len = a.length();
        int tempIndex = 1;

        while (index < len){
            subStringSet.add(a.substring(index++));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true){

            String str = subStringSet.pollFirst();

            if(str.length() > 1){
                subStringSet.add(str.substring(1));
            }

            len = str.length();
            tempIndex = 0;

            while (tempIndex++ < len){
                String res = str.substring(0, tempIndex);
                if(resultSet.add(res)){
                    stringBuilder.append(res);
                }
            }

            int strLen = stringBuilder.length();
            if(strLen > k){
                char ch = stringBuilder.charAt(k-1);
                resultSet.clear();
                subStringSet.clear();
                resultSet = null;
                subStringSet = null;
                stringBuilder = null;
                return ch;
            }
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                char res = Result.ashtonString(s, k);

                bufferedWriter.write(String.valueOf(res));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
