import java.io.*;
import java.util.*;

public class Bicikli {

	static int n, m, v;
	static ArrayList<Integer>[] adj, radj;
	static long[] dp;
	static int[] degree;
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<Integer> topological = new ArrayList<>();
	static boolean[] v0, v1, isInGraph, isModded;
	static final int MOD = 1000000000;

	public static void main(String args[]) throws IOException {

		n = readInt(); // num nodes
		if (n == 1) {
			pr.println(0);
			exit();
		}
		m = readInt(); // num edges

		isModded = new boolean[n];

		adj = new ArrayList[n];
		radj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
			radj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj[a].add(b);
			radj[b].add(a);
		}

		// run bfs from node 0 to every other node
		q.add(0);
		v0 = new boolean[n];
		v0[0] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i : adj[cur]) {
				if (!v0[i]) {
					v0[i] = true;
					q.add(i);
				}
			}
		}

		// run bfs from node 1 to every other node
		q.add(1);
		v1 = new boolean[n];
		v1[1] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i : radj[cur]) {
				if (!v1[i]) {
					v1[i] = true;
					q.add(i);
				}
			}
		}

		isInGraph = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (v0[i] && v1[i]) {
				isInGraph[i] = true;
				v++;
			}
		}

		if (!isInGraph[1] || !isInGraph[0]) {
			println(0L);
			exit();
		}

		degree = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j : adj[i]) {
				if (isInGraph[j]) {
					degree[i]++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (degree[i] == 0 && isInGraph[i]) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			topological.add(cur);
			for (int j : radj[cur]) {
				if (isInGraph[j]) {
					if (--degree[j] == 0) {
						q.add(j);
					}
				}
			}
		}
		// System.out.println(Arrays.toString(isInGraph));
		// System.out.println(topological);
		// System.out.println(topological.size());
		// System.out.println(v);
		if (topological.size() != v) {
			println("inf");
			exit();
		}

		Collections.reverse(topological);
		dp = new long[n];
		dp[0] = 1L;
		for (int i : topological) {
			for (int j : adj[i]) {
				if (isInGraph[j]) {
					dp[j] += dp[i];
					if (dp[j] >= MOD) {
						dp[j] %= MOD;
						isModded[j] = true;
					}
					if (isModded[i]) isModded[j] = true;
				}
			}
		}
		// pr.println();
		println(dp[1]);

		exit();
	}

	static void println(long l) {
		if (isModded[1]) {
			pr.println(String.format("%09d", l));
		} else {
			pr.println(l);
		}
	}

	static void println(String s) {
		pr.println(s);
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static int readInt() throws IOException {
		int ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (neg)
			return -ret;
		return ret;
	}

	private static void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if (bytesRead == -1)
			buffer[0] = -1;
	}

	private static byte Read() throws IOException {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}

	public void close() throws IOException {
		if (din == null)
			return;
		din.close();
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}