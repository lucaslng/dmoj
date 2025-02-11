import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S_3_Strategic_Bombing {

	static byte x, y;
	static ArrayList<Byte>[] adj = new ArrayList[26];
	static ArrayList<byte[]> edges = new ArrayList<>();
	static boolean[] v = new boolean[26];
	static Queue<Byte> q = new LinkedList<>();
	static byte zero = (byte) 0;
	static byte ans = zero;

	public static void main(String args[]) throws IOException {

		for (int i=0;i<26;i++) {
			adj[i] = new ArrayList<>();
		}

		while ((x = Read()) != '*' && (y = Read()) != '*') {
			Read(); // read \n
			x -= 65;
			y -= 65;
			adj[x].add(y);
			adj[y].add(x);
			edges.add(new byte[] { x, y });
		}

		for (byte[] edge : edges) {
			Arrays.fill(v, false);
			v[0] = true;
			q.add(zero);
			while (!q.isEmpty()) {
				byte cur = q.poll();
				for (byte i : adj[cur]) {
					if (cur == edge[0] && i == edge[1]) continue;
					if (cur == edge[1] && i == edge[0]) continue;
					if (!v[i]) {
						v[i] = true;
						q.add(i);
					}
				}
			}
			if (!v[1]) {
				println((char) (edge[0] + 65) + "" + (char) (edge[1] + 65));
				ans++;
			}
		}

		println("There are " + ans + " disconnecting roads.");
		
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