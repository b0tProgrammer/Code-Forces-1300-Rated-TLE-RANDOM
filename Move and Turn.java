
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    private static final long lif = (long) 1e9 + 7; //long infinity
    private static final int iif = 998244353; // int infinity!

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

            Comparator<Pair> com = Comparator.comparingInt(i -> i.v1);

            int testcases = 1;

//            testcases = in.nextInt();

            while (testcases-- > 0) {
                /*
                    Move and turn
                    an observational and a math problem!
                    In this we will come to know how to solve this!
                    just write some example on the paper
                    and we notice a pattern
                    Ex:
                    steps : 1 : ans 4
                    steps : 2 : ans 4
                    steps : 3 : ans 12
                    steps : 4 : ans 9
                    steps : 5 : ans 24
                    steps : 6 : ans 16
                    steps : 7 : ans 40
                    writing all even answers
                    4 9 16 ?
                    writing all odd answers except 1
                    12 24 40 ?

                    if we find the '?' then we got the answer!
                    Step 1 :
                    check the difference of evens it's increasing by 2
                    i.e
                    9-4 = 5
                    16-9 = 7
                    The difference of differences = 7-5 = 2;
                    so, if you see wisely the difference of differences is in AP
                    so, we know that to know the nth term let's use ap formula
                    i.e a+(n-1)*d
                    a = 5 (known)
                    n (known)
                    d = 2(known)
                    so let's say if n = 6 then we know that it is 3rd even number, just divide it with 2
                    so we add 4+(5+7) = 16
                    else
                    lets say n = 5
                    we add 12+(12) = 24
                    lets say n = 7
                    we add 12+(12+16) = 40
                 */

                long num = in.nextInt();

                if(num == 1) out.println(4);
                else {
                    long range = num/2-1;
                    if(!odd(num)) {
                        // assuming n = 6
                        // we need 5+7
                        // range = n>>1-1
                        // start num = 5
                        // common diff = 2
                        out.println(4+getSum(5,2,range));
                    } else {
                        out.println(12+getSum(12,4,range));
                    }
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

    public static List<Integer> reversed(List<Integer> lis) {
        List<Integer> ans = new ArrayList<>();
        for(int i = lis.size()-1; i >= 0; i--) {
            ans.add(lis.get(i));
        }
        return ans;
    }

    private static int update(int ans, int i) {
        return (int)((ans*1L*i)%iif);
    }

    private static void getFactors(int num, Map<Integer, Integer> map) {

        for(int i = 2; i <= (int) Math.sqrt(num); i++) {
            while(num%i == 0) {
                map.put(i,map.getOrDefault(i,0)+1);
                num /= i;
            }
        }
        if(num > 1) map.put(num,map.getOrDefault(num,0)+1);
    }

    public static void print(boolean cond) {
        System.out.println(cond ? "YES" : "NO");
    }

    public static long fastPow(long b, long e) {
        long res = 1;
        while (e > 0) {
            if (odd(e)) res = (res * b) % lif;
            b = (b * b) % lif;
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

    private static long getSum(int startNum, int commonDiff, long range) {
        // Calculate the last term of the series
        long lastNum = startNum + (range - 1) * commonDiff;

        // Use the formula for the sum of an arithmetic progression
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
    int v1,v2;
    public Pair(int v,int w) {v1 = v; v2 = w;}
}