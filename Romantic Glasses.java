import com.sun.source.tree.Tree;

import java.beans.IntrospectionException;
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
                int siz = in.nextInt();
                // we have to check whether there exists a sub-array in the given array!
                // such that the sum of the numbers at the even indices is equal to the sum of the numbers at the odd indices at the even sub array!

                // let us think in this way! that the elements at the even indices to be considered as the positive elements ans the elements at the odd indices to be negative!
                // then the problem boils down to find a sub-array with sum 0;

                // let's consider a hashMap to store the sums that we seen;

                HashMap<Long,Integer> map = new HashMap<>();

                map.put(0L,1); // thinking that we have already seen the 0 sum!
                // let us take a case !
                // 1 3 2  the sum at even places = 3 and odd places = 3.

                long sum = 0;

                int[] nums = new int[siz];

                for(int i = 0; i < siz; i++) nums[i] = in.nextInt();

                boolean seen = false;

                for(int i = 0; i < siz; i++) {
                    if(odd(i)) sum += (nums[i]*-1);
                    else sum += nums[i];
                    if(map.containsKey(sum)) seen = true;
                    map.put(sum,map.getOrDefault(sum,0)+1);
                }
                out.println(seen ? "yEs" : "nO");
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

    public static List<Integer> reversed(List<Integer> lis) {
        List<Integer> ans = new ArrayList<>();
        for(int i = lis.size()-1; i >= 0; i--) {
            ans.add(lis.get(i));
        }
        return ans;
    }

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