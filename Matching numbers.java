import java.awt.geom.PathIterator;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    private static final int mod = (int) 1e9 + 7; // mod
    private static final int iif = 998244353; // int infinity!

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

            int testcases = 1;

            Comparator<Pair> com = Comparator.comparingInt(i -> i.v1);


            testcases = in.nextInt();
            //<------------------------ Main Code starts Here --------------------------------------------->

            while (testcases-- > 0) {
                int siz = in.nextInt();
                // given,
                // sum of 1 to 2n numbers should be equal to x+x+1+x+2, ... x+n-1
                // 1+2+3+...+2n == x+x+1+x+2+..+x+n-1
                // ((2n+1)*2n)/2 == (x+x+n-1)*n/2
                // equal to (2n+1)*2 == 2x+n-1
                // 4n+2 == 2x+n-1
                // if n is even then the above condition fails so, if n is even answer is no
                // else 3n+3 == 2x
                // x == (3n+3)/2
                if(!odd(siz)) out.println("NO");
                else {
                    out.println("YES");
                    int[] ans = new int[siz];
                    int curr = 2*siz;
                    for(int i = 0; i < siz; i+=2) {
                        ans[i] = curr--;
                    }
                    for(int i = 1; i < siz; i+=2) {
                        ans[i] = curr--;
                    }
                    for(int i = 0; i < siz; i++) {
                        out.println((i+1)+" "+ans[i]);
                    }
                }

            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    //<-------------------------------------- Fast reader ----------------------------------------->
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

        public Integer[] nextIntArray(int start, int end) {
            Integer[] nums = new Integer[end];
            for (int i = start; i < end; i++) {
                nums[i] = Integer.parseInt(next());
            }
            return nums;
        }

        public Pair[] nextPairArray(int start, int siz) {
            Pair[] nums = new Pair[siz];
            for (int i = start; i < siz; i++) {
                int v1 = Integer.parseInt(next());
                int v2 = Integer.parseInt(next());
                nums[i] = new Pair(v1, v2);
            }
            return nums;
        }

        public Long[] nextLongArray(int start, int siz) {
            Long[] nums = new Long[siz];
            for (int i = start; i < siz; i++) {
                nums[i] = Long.parseLong(next());
            }
            return nums;
        }
    }

    //<--------------------------------------- Fast writer ----------------------------------------->

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

    //<-------------------------------------------- Some helper methods ---------------------------------------->

    private static long kadane(int l, int r, int[] nums) {
        long sum = 0;
        long mSum = 0;
        for (int i = l; i < r; i++) {
            if (sum < 0) sum = nums[i];
            else sum += nums[i];
            mSum = max(sum, mSum);
        }
        return mSum;
    }

    public static int getMax(Integer[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) max = max(max,nums[i]);
        return max;
    }

    public static int getMin(Integer[] nums) {
        int min = nums[0];
        for(int i = 1; i < nums.length; i++) min = min(min,nums[i]);
        return min;
    }

    public static List<Integer> reversed(List<Integer> lis) {
        List<Integer> ans = new ArrayList<>();
        for (int i = lis.size() - 1; i >= 0; i--) {
            ans.add(lis.get(i));
        }
        return ans;
    }

    public static void print(boolean cond, FastWriter out) throws IOException {
        out.println(cond ? "YES" : "NO");
    }

    public static long fastPow(long b, long e) {
        long res = 1;
        while (e > 0) {
            if (odd(e)) res = (res * b) % mod;
            b = (b * b) % mod;
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

    private static long fact(int num) {
        long v = 1;
        for (int i = 1; i <= num; i++) {
            v = (v * i) % mod;
        }
        return v;
    }

    private static long gcd(long a, long b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static int gcd(int a, int b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static long getSum(long startNum, long commonDiff, long range) {
        long lastNum = startNum + (range - 1) * commonDiff;
        return range * (startNum + lastNum) / 2;
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
    int v1, v2;

    public Pair(int v, int w) {
        v1 = v;
        v2 = w;
    }
}

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
    }
}

class Tuple {
    int v1, v2, idx;

    public Tuple(int v, int w, int i) {
        v1 = v;
        v2 = w;
        idx = i;
    }
}