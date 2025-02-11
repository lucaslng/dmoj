import java.io.*;
import java.util.*;

public class S_3_Phonomenal_Reviews {

	static int n, m, maxi, ans;
	static int max = 0;
	static int totalX2 = 0;
	static ArrayList<Integer>[] adj;
	static int[] maxes, degree;
	static boolean[] d;
	static boolean[] isInSubtree;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String args[]) throws IOException {

		n = readInt();
		m = readInt();

		d = new boolean[n];
		isInSubtree = new boolean[n];
		Arrays.fill(isInSubtree, true);
		for (int i = 0; i < m; i++) {
			int node = readInt();
			d[node] = true;
		}

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int x = readInt(), y = readInt();
			adj[x].add(y);
			adj[y].add(x);
		}

		degree = new int[n];
		for (int i = 0; i < n; i++) {
			degree[i] = adj[i].size();
			if (degree[i] == 1 && !d[i]) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			if (!d[cur] && degree[cur] == 1) {
				isInSubtree[cur] = false;
				for (int neighbor : adj[cur]) {
					if (--degree[neighbor] == 1 && !d[neighbor]) {
						q.add(neighbor);
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (isInSubtree[i]) {
				for (int j : adj[i]) {
					if (isInSubtree[j] && j > i) {
						totalX2 += 2;
					}
				}
			}
		}
		
		maxi = -1;
		for (int i = 0; i < n; i++) {
			if (isInSubtree[i]) {
				maxi = i;
				break;
			}
		}

		maxes = new int[n];
		Arrays.fill(maxes, -1);
		maxes[maxi] = 0;
		q.add(maxi);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int neighbor : adj[cur]) {
				if (isInSubtree[neighbor] && maxes[neighbor] == -1) {
					maxes[neighbor] = maxes[cur] + 1;
					q.add(neighbor);
				}
			}
		}

		max = 0;
		for (int i = 0; i < n; i++) {
			if (maxes[i] > max) {
				max = maxes[i];
				maxi = i;
			}
		}

		Arrays.fill(maxes, -1);
		maxes[maxi] = 0;
		q.add(maxi);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int neighbor : adj[cur]) {
				if (isInSubtree[neighbor] && maxes[neighbor] == -1) {
					maxes[neighbor] = maxes[cur] + 1;
					q.add(neighbor);
				}
			}
		}

		max = 0;
		for (int i = 0; i < n; i++) {
			if (maxes[i] > max) {
				max = maxes[i];
			}
		}

		ans = totalX2 - max;
		println(ans);

		exit();
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