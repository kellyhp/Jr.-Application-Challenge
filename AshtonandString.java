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

class Result1{
    public static int[] generateSuffixArray(String s) {
        int len = s.length();
        SuffixIndex[] suffixIndexes = new SuffixIndex[len];

        for (int i = 0; i < len; i++) {
            suffixIndexes[i] = new SuffixIndex(i, s.substring(i));
        }
        
        Arrays.sort(suffixIndexes, Comparator.comparing(suffixIndex -> suffixIndex.suffix));

        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = suffixIndexes[i].index;
        }
        return result;
    }

    static class SuffixIndex {
        int index;
        String suffix;

        SuffixIndex(int index, String suffix) {
            this.index = index;
            this.suffix = suffix;
        }
    }
    
    public static int[] generateLCP(String s, int[] suffix) {
        int n = s.length();
        int k = 0;
        int[] lcp = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffix[i]] = i;
        }
        for (int i = 0; i < n; i++, k = (k > 0) ? k - 1 : 0) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = suffix[rank[i] + 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            lcp[rank[i]] = k;
        }
        return lcp;
    }
}

public class AshtonAndString {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                int n = s.length();
                int[] suffix = Result1.generateSuffixArray(s);
                int[] lcp = Result1.generateLCP(s, suffix);

                for(int i = 0; i < n; i++) {
                    int len = n - suffix[i];
                    int count = len * (len + 1) / 2;
                    int prefix = lcp[i];
                    count = count - prefix * (prefix + 1) / 2;
                     if(k > count) {
                        k = k - count;
                        continue;
                     }
                    for(int j = prefix + 1; j <= len; j++) {
                        if (k <= j) {
                            System.out.println(s.charAt((int)(suffix[i] + k - 1)));
                            break;
                        }
                        k = k - j;
                    }
                    break; 
                }
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
