import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class P_1_Permutation_Cutting {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException, InterruptedException {	

    // Size of N        Complexity needed to pass
    // N <= 10          O(N!)
    // N <= 20          O(2^n)
    // N <= 100         O(N^4)
    // N <= 400         O(N^3)
    // N <= 5000        O(N^2)
    // N <= 100000      O(Nsqrt(N)), O(Nlog(N)), O(Nlog(log(N)))
    // N <= 1000000     O(N), O(Nlog(N))
    // N <= 10000000    O(N)
    // N <= 10 ^ 12     O(sqrt(N)), O(log(N))
    // N <= 10 ^ 18     O(log(N)), O(1)

		int n = readInt();

		int[] start = new int[n];
		int[] goal = new int[n];
		int[] dict = new int[n+1]; // position of num i in goal array

		for (int i=0;i<n;i++) {
			start[i] = readInt();
		}

		for (int i=0;i<n;i++) {
			goal[i] = readInt();
			dict[goal[i]] = i;
		}

		// pr.println(Arrays.toString(dict));

		int ans = 0;

		for (int i=0;i<n;i++) {

			int groupSize = 0;
			int pos = dict[start[i]];
			
			for (int j=0;j<n;j++) {
				if (j+pos >= n || i+j >= n) break;

				if (start[i+j] == goal[pos+j]) {
					groupSize++;
				} else {
					break;
				}

			}
			i += groupSize - 1;

			ans++;
		}


		pr.println(ans);

    pr.close();
	}
	static class Pair implements Comparable<Pair> {
		int f,s;
		Pair(int f,int s) {
			this.f=f; this.s=s;
		}
    @Override
		public int compareTo(Pair other) {
			if (this.f!=other.f) return this.f-other.f;
			return this.s-other.s;
		}
	}
	static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong() throws IOException {
		return Long.parseLong(next());
	}
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}
	static char readChar() throws IOException {
		return next().charAt(0);
	}
	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}