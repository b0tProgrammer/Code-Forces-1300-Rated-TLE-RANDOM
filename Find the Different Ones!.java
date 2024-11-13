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

            Comparator<Pair> com = Comparator.comparingInt(i -> i.v1);

            // use the minPath method in order to get the minimum cost to travel from the one end of a matrix to another end

            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {

                /*
                    Find the different Ones!
                    In this, let us check the closest un equal number to the current number!
                    And marking that index to the current index value!
                    This can be understood better when there is a code!
                */

                int siz = in.nextInt();
                int[] nums = new int[siz];
                for(int i = 0; i < siz; i++) nums[i] = in.nextInt();
                int[] ans = new int[siz];
                Arrays.fill(ans,siz); // assuming at the start the elements all are equal so, the unequal elements index is siz!
                for(int i = siz-2; i >= 0; i--) {
                    if(nums[i] == nums[i+1]) ans[i] = ans[i+1];
                    else ans[i] = i+1; // the next element is unequal!
                }

                // now taking the queries an input!

                int qs = in.nextInt();

                for(int i = 0; i < qs; i++) {
                    int st = in.nextInt()-1;
                    int en = in.nextInt()-1;
                    if(ans[st] > en) out.println("-1 -1");
                    else out.println((st+1)+" "+(ans[st]+1));
                }

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