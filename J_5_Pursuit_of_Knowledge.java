import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class J_5_Pursuit_of_Knowledge {

	static int n, m, t, q;
	static int[][] dists;
	static int[][] queries;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String args[]) throws IOException {

		n = readInt(); // num rooms
		m = readInt(); // num hallways
		t = readInt(); // time per hallway

		adj = new ArrayList[n];
		for (int i=0;i<n;i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i=0;i<m;i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj[a].add(b); // hallways are one way
		}
		// System.out.println(Arrays.deepToString(adj));

		dists = new int[n][n];
		for (int i=0;i<n;i++) {

			Arrays.fill(dists[i], Integer.MAX_VALUE);
			dists[i][i] = 0;
			queue.add(i);

			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int j : adj[cur]) {
					// System.out.println(cur + " " + j);
					int newDist = dists[i][cur] + 1;
					// System.out.println(newDist);
					if (newDist < dists[i][j]) {
						dists[i][j] = newDist;
						queue.add(j);
					}
				}
			}

		}

		q = readInt();
		// System.out.println(Arrays.deepToString(dists));
		for (int i=0;i<q;i++) {
			int a = readInt() - 1; int b = readInt() - 1;
			if (dists[a][b] == Integer.MAX_VALUE) {
				println("Not enough hallways!");
			} else {
				int ans = dists[a][b] * t;
				println(ans);
			}
		}
		

		
		exit();
	}

	static class MyComparator implements Comparator<Integer> {

		private final int i;

		public MyComparator(int i) {
			this.i = i;
		}

		@Override
		public int compare(Integer a, Integer b) {
			return dists[i][a] - dists[i][b];
		}
		
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static String readLine() throws IOException {
		byte[] buf = new byte[64]; // line length
		int cnt = 0, c;
		while ((c = Read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}

	public static String read() throws IOException {
		byte[] ret = new byte[1024];
		int idx = 0;
		byte c = Read();
		while (c <= ' ') {
			c = Read();
		}
		do {
			ret[idx++] = c;
			c = Read();
		} while (c != -1 && c != ' ' && c != '\n' && c != '\r');
		return new String(ret, 0, idx);
	}

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

	public static long readLong() throws IOException {
		long ret = 0;
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

	public static double readDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = Read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}

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

	static void print(Object o) {
		pr.print(o);
	}

	static void println(Object o) {
		pr.println(o);
	}

	static void println() {
		pr.println();
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}