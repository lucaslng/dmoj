import java.io.*;
import java.util.*;

public class S_4_Convex_Hull {

	static int k, n, m, A, B;
	static ArrayList<Edge>[] adj;
	static PriorityQueue<State> pq;
	static int[][] times;

	public static void main(String args[]) throws IOException {

		k = readInt(); // thickness of ship
		n = readInt(); // num islands
		m = readInt(); // num routes

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int t = readInt();
			int h = readInt();
			adj[a].add(new Edge(b, t, h));
			adj[b].add(new Edge(a, t, h));
		}

		A = readInt() - 1;
		B = readInt() - 1;

		times = new int[n][k + 1];
		for (int[] row : times)
			Arrays.fill(row, Integer.MAX_VALUE);

		times[A][k] = 0;

		pq = new PriorityQueue<>();
		pq.add(new State(0, k, A));

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			if (cur.island == B) {
				println(cur.time);
				exit();
			}
			if (cur.time > times[cur.island][cur.hull]) continue;
			for (Edge edge : adj[cur.island]) {
				int newTime = cur.time + edge.time;
				int newHull = cur.hull - edge.destruction;
				if (newHull > 0 && newTime < times[edge.dest][newHull]) {
					times[edge.dest][newHull] = newTime;
					pq.add(new State(newTime, newHull, edge.dest));
				}
			}
		}

		println(-1);

		exit();
	}

	static class Edge {
		public final int dest, time, destruction; // haha destruction very cool

		public Edge(int dest, int time, int destruction) {
			this.dest = dest;
			this.time = time;
			this.destruction = destruction;
		}

	}

	static class State implements Comparable<State> {
		public final int time, hull, island;

		public State(int time, int hull, int island) {
			this.time = time;
			this.hull = hull;
			this.island = island;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.time, o.time);
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