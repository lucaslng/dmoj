import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class P_6_Tunnels {
	
	static int n, t;
	static int total = 0;
	static int maxa = 0;
	static int maxia = 0;
	static ArrayList<int[]>[] adj;
	static Stack<Integer> stack;
	static int[] a, b;

	public static void main(String args[]) throws IOException {
		n = readInt();
		t = readInt();

		adj = new ArrayList[n];
		for (int i=0;i<n;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=0;i<n-1;i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			int dist = readInt();
			adj[x].add(new int[] { y, dist });
			adj[y].add(new int[] { x, dist });
			total += dist;
		}

		stack = new Stack<>();
		stack.add(0);
		a = new int[n];
		Arrays.fill(a, -1);
		a[0] = 0;
		
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int[] tunnel : adj[cur]) {
				if (a[tunnel[0]] == -1) {
					a[tunnel[0]] = a[cur] + tunnel[1];
					stack.add(tunnel[0]);
				}
			}
		}

		for (int i=0;i<n;i++) {
			if (maxa < a[i]) {
				maxa = a[i];
				maxia = i;
			}
		}

		Arrays.fill(a, -1);
		a[maxia] = 0;
		stack.add(maxia);

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int[] tunnel : adj[cur]) {
				if (a[tunnel[0]] == -1) {
					a[tunnel[0]] = a[cur] + tunnel[1];
					stack.add(tunnel[0]);
				}
			}
		}

		maxa = 0;
		maxia = 0;
		for (int i=0;i<n;i++) {
			if (maxa < a[i]) {
				maxa = a[i];
				maxia = i;
			}
		}

		b = new int[n];
		Arrays.fill(b, -1);
		b[maxia] = 0;
		stack.add(maxia);

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			for (int[] tunnel : adj[cur]) {
				if (b[tunnel[0]] == -1) {
					b[tunnel[0]] = b[cur] + tunnel[1];
					stack.add(tunnel[0]);
				}
			}
		}

		total *= 2;
		for (int i=0;i<n;i++) {
			if (adj[i].size() == t) {
				pr.println((i + 1) + " " + (total - Math.max(a[i], b[i])));
			}
		}
		
		exit();
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