import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class S_4_Daily_Commute {

	static int n, w, d;
	static ArrayList<Integer>[] walkways;
	static int[] stations;
	static int[][] days;
	static int[] dists;
	static PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> dists[a] - dists[b]);

	public static void main(String args[]) throws IOException {

		n = readInt(); // stations
		w = readInt(); // walkways
		d = readInt(); // days
		
		walkways = new ArrayList[n];
		for (int i=0;i<n;i++) {
			walkways[i] = new ArrayList<>();
		}
		for (int i=0;i<w;i++) {
			walkways[readInt() - 1].add(readInt() - 1);
		}

		stations = new int[n];
		for (int i=0;i<n;i++) {
			stations[readInt() - 1] = i;
		}

		days = new int[d][2];
		for (int i=0;i<d;i++) {
			days[i] = new int[] {readInt() - 1, readInt() - 1};
		}

		dists = new int[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		dists[0] = 0;

		q.add(0);

		// for (int i=0;i<d;i++) 
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int walkway : walkways[cur]) {
				int newDist = dists[cur] + 1;
				if (newDist < dists[walkway]) {
					dists[walkway] = newDist;
					q.add(walkway);
				}
			}

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