import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class Can_Shahir_even_get_there {
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
    int m = readInt();
    int a = readInt()-1;
    int b = readInt()-1;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i=0;i<n;i++) {
      ArrayList<Integer> empty = new ArrayList<>();
      adj.add(empty);
    }
    for (int i=0;i<m;i++) {
      int x = readInt()-1;
      int y = readInt()-1;
      adj.get(x).add(y);
      adj.get(y).add(x);
    }

    boolean ans = false;
    boolean[] visited = new boolean[n];
    Queue<Integer> q = new LinkedList<>();
    q.add(a);
    
    while (true) { 
        if (q.isEmpty()) {
          break;
        }
        int cur = q.poll();
        visited[cur] = true;
        if (cur == b) {
          ans = true;
          break;
        }
        ArrayList<Integer> adjs = adj.get(cur);
        // pr.println(cur + " " + adjs.toString());
        for (int i=0;i<adj.get(cur).size();i++) {
          int node = adj.get(cur).get(i);
          if (!visited[node]) {
            // pr.println(node);
            q.add(node);
          }
        }
    }
    pr.println((ans?("GO SHAHIR!"):("NO SHAHIR!")));
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