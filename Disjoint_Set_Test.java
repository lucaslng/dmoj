import java.io.*;
import java.util.ArrayList;

public class Disjoint_Set_Test {

	static int n, m, root;
	static ArrayList<Integer> results = new ArrayList<>();

	public static void main(String args[]) throws IOException {

		n = readInt();
		m = readInt();

		DSU dsu = new DSU(n);

		for (int i = 0; i < m; i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			if (dsu.union(u, v))
				results.add(i + 1);
		}

		root = dsu.find(0);
		// println(dsu.size[root]);
		if (dsu.size[root] != n) {
			println("Disconnected Graph");
		} else {
			for (int result : results) {
				println(result);
			}
		}

		exit();

	}

	static class DSU {
		
		private final int[] parent;
		public final int[] size;

		DSU(int n) {
			parent = new int[n];
			size = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		int find(int u) {
			if (parent[u] != u) {
				parent[u] = find(parent[u]);
			}
			return parent[u];
		}

		boolean union(int u, int v) {
			int rootU = find(u);
			int rootV = find(v);
			if (rootU == rootV)
				return false;

			if (size[rootU] < size[rootV]) {
				parent[rootU] = rootV;
				size[rootV] += size[rootU];
			} else {
				parent[rootV] = rootU;
				size[rootU] += size[rootV];
			}
			return true;
		}
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

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

	static void println(Object o) {
		pr.println(o);
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}