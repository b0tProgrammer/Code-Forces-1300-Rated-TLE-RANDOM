import javax.print.DocFlavor;
import javax.print.attribute.standard.ColorSupported;
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

            testcases = in.nextInt();

            boolean[] poss = new boolean[(int)1e6+1];

            for(int i = 2; i < 1_000; i++) {
                int val = i*(i+1)+1;
                int c = 3;
                for(int j = 1; j <= 20; j++) {
                    if(val > 10_00_000) break;
                    poss[val] = true;
                    val += (int) fastPow(i,(c++));
                }
            }

            while(testcases-- > 0) {
                /*
                    Credits :
                    https://codeforces.com/blog/entry/102710
                    Thanks to MikeMirzayanov for this wonderful explanation!
                    In this we will be given a tree and the nodes are colored with either white or black.
                    we have to determine the number of subTrees whose black and white nodes are equal in frequency including the root of that sub tree!
                    We will be solving this question using DP!
                 */


                //The construction of tree is done with the help of array of lists! And then we will use DP in that we will check the colors of the current node to all its child nodes.
                // if the color is white it’s 1 else -1 we add those to a variable if the variable returns 0 for a subTree then it's a balacnced subTree!
                // Else not balances subTree! we will have a an variable which counts the number of trees of that type! and we will print that at the end!


                int siz = in.nextInt();

                List<Integer>[] tree = new ArrayList[siz];

                for(int i = 0; i < siz; i++) tree[i] = new ArrayList<>();

                for(int child = 2; child <= siz; child++) {
                    int parent = in.nextInt();
                    tree[parent].add(child);
                }

                String s = in.next();

                int[] ans = {0};

                class BFS{
                    public int dp(int x) {
                        int val = s.charAt(x-1) == 'W' ? 1 : -1;
                        if(x >= siz) return val;
                        if(tree[x].isEmpty()) return val;
                        for(int child : tree[x]) {
                            val += dp(child); // this checks the all subtrees recursively!
                        }
                        if(val == 0) ans[0]++; // this states that the subTree  selected is in balanced condition!
                        return val;
                    }
                }

                BFS search = new BFS();
                search.dp(1);

                out.println(ans[0]);

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

    private static char check(int i, int siz,String s) {
        for(char c = 'a'; c < 'z'; c++) {
            if(c != s.charAt(i-1)) {
                if(i == siz-1) return c;
                else if(c != s.charAt(i+1)) return c;
            }
        }
        return 'z';
    }

    private static long kadane(int l, int r, int[] nums) {
        long sum = 0;
        long mSum = 0;
        for(int i = l; i < r; i++) {
            if(sum < 0) sum = nums[i];
            else sum += nums[i];
            mSum = max(sum,mSum);
        }
        return mSum;
    }

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

    public static void print(boolean cond,FastWriter out) throws IOException {
        out.println(cond ? "Yes" : "No");
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
    int v1,v2;
    public Pair(int v,int w) {v1 = v; v2 = w;}
}
class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
    }
}