import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class S_2_Escape_Room {
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

		int m = readInt();
		int n = readInt();

		HashMap<Integer, ArrayList<int[]>> g = new HashMap<>();
		for (int r = 1; r <= m; r++) {
			for (int c = 1; c <= n; c++) {
				int val = readInt();
				int[] coord = new int[] {r, c};
				if (!g.containsKey(val)) {
					g.put(val, new ArrayList<>());
				}
				g.get(val).add(coord);
			}
		}

		boolean ans = false;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] v = new boolean[m + 1][n + 1];
		v[m][n] = true;
		q.add(new int[] {m, n});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			// pr.println(r + " " + c);

			if (r == 1 && c == 1) {
				ans = true;
				break;
			}

			int val = r * c;
			if (g.containsKey(val)) {
				for (int[] coord : g.get(val)) {
					if (!v[coord[0]][coord[1]]) {
						v[coord[0]][coord[1]] = true;
						q.add(coord);
					}
				}
			}

		}

		if (ans) {
			pr.println("yes");
		} else {
			pr.println("no");
		}
		pr.close();
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
}