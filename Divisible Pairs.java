import com.sun.source.tree.Tree;

import java.util.*;
import java.io.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main {

    private static final long MAX = (long) 1e9 + 7; // max size for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

//            Comparator<Pair> com = (i, j) -> (int) ((i.val - j.val));

            // use the minPath method in order to get the minimum cost to travel from the one end of a matrix to another end

            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {
                /*
                   Divisible pairs!
                   In this question, we have to check the number of pairs on adding can be divisible by 'x' and subtraction can be divisible by 'y'
                   for this, we can add the remainders into a hashMap with their occurrences!
                   and do the condition mentioned in the question!
                 */
                int n = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                int[] nums = new int[n];
                Map<String,Integer> cnt = new HashMap<>();
                for(int i = 0; i < n; i++) nums[i] = in.nextInt();
                long ans = 0;
                for(int i = 0; i < n; i++) {
                    int xx = nums[i] % x;
                    int yy = nums[i] % y;
                    // We are looking for the pair ((x - xx) % x, yy) in the map
                    String key = ((x - xx) % x) + "," + yy;
                    ans += cnt.getOrDefault(key, 0);
                    // Add/update the count for the pair (xx, yy)
                    String currentKey = xx + "," + yy;
                    cnt.put(currentKey, cnt.getOrDefault(currentKey, 0) + 1);
                }
                out.println(ans);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
//<--------------------------------------Fast reader----------------------------------------->
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

//<---------------------------------------Fast writer--------------------------------------------->

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println() throws IOException {
            bw.append("\n");
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

// <-------------------------------------------------Some helper methods---------------------------------------->

    public static long fastPow(long b, long e) {
        long res = 1;
        while (e > 0) {
            if (odd(e)) res = (res * b) % MAX;
            b = (b * b) % MAX;
            e = e >> 1;
        }
        return res;
    }

    public static int charToInt(char c) {
        return (c - '0');
    }

    public static char intToChar(int n) {
        return (char) (n + 48);
    }

    public static int[] sieve(int upto) {
        int[] primes = new int[upto + 1];
        Arrays.fill(primes, 1);
        for (int i = 2; i * i <= upto; i++) {
            if (primes[i] == 1)
                for (int j = i * i; j <= upto; j += i) {
                    primes[j] = 0;
                }
        }
        return primes;
    }

    private static long gcd(long a, long b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static int gcd(int a, int b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static long sumN(long siz) {
        return (siz * (siz + 1)) >> 1;
    }

    private static boolean odd(long siz) {
        return (siz & 1) == 1;
    }

    private static boolean powOf2(long siz) {
        return (siz & (siz - 1)) == 0;
    }

    private static List<Integer> primeFactors(int siz) {

        List<Integer> sizs = new ArrayList<>();
        int[] p;
        p = sieve(siz);
        for (int i = 2; i <= siz; i++) {
            while (siz % i == 0 && p[i] == 1) {
                sizs.add(i);
                siz /= i;
            }
        }
        return sizs;
    }
}

class Pair2 {
    long v1;
    long v2;

    public Pair2(long v1, long v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
class Pair {
    int v1,v2;
    public Pair(int v,int w) {v1 = v; v2 = w;}
}