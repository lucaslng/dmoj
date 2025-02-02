import java.io.*;
import java.util.*;

public class P_6_Save_Nagato {
	static ArrayList<Integer>[] adj;
	static int x, y, n, max, idx;
	static Stack<Integer> stack;
	static int[] a, b;
	public static void main(String args[]) throws IOException {
		n = readInt();
		adj = new ArrayList[n];
		for (int i=0;i<n;i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			x = readInt() - 1;
			y = readInt() - 1;
			adj[x].add(y);
			adj[y].add(x);
		}

		stack = new Stack<>();
		stack.add(0);
		a = new int[n];
		a[0] = 1;

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int i : adj[cur]) {
				if (a[i] == 0) {
					a[i] = a[cur] + 1;
					stack.add(i);
				}
			}
		}

		max = 0;
		idx = 0;
		for (int i=0;i<n;i++) {
			if (max < a[i]) {
				max = a[i];
				idx = i;
			}
		}

		Arrays.fill(a, 0);
		a[idx] = 1;
		stack.add(idx);

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int i : adj[cur]) {
				if (a[i] == 0) {
					a[i] = a[cur] + 1;
					stack.add(i);
				}
			}
		}

		max = 0;
		idx = 0;
		for (int i=0;i<n;i++) {
			if (max < a[i]) {
				max = a[i];
				idx = i;
			}
		}

		b = new int[n];
		b[idx] = 1;
		stack.add(idx);

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int i : adj[cur]) {
				if (b[i] == 0) {
					b[i] = b[cur] + 1;
					stack.add(i);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			println(Math.max(a[i], b[i]));
		}
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

	static void println(Object o) {
		pr.println(o);
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}