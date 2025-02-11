import java.io.*;

public class S_3_Lunch_Concert {

	static int n;
	static int min = Integer.MAX_VALUE;
	static int max = 0;
	static long x = 0L;
	static long ans;
	static Friend[] f;

	public static void main(String args[]) throws IOException {

		n = readInt(); // number of friends

		f = new Friend[n];

		for (int i = 0; i < n; i++) {
			f[i] = new Friend(readInt(), readInt(), readInt());
			min = Integer.min(min, f[i].p);
			max = Integer.max(max, f[i].p);
		}

		// first calculation
		for (Friend friend : f) {
			if (min + friend.d < friend.p) {
				x += (friend.p - friend.d - min) * friend.w;
			}
		}
		println(x);
		ans = x;

		for (int i=min+1;i<=max;i++) { // looping over distance
			for (Friend friend : f) {
				if (i + friend.d <= friend.p) {
					x -= friend.w;
				} else if (i + friend.d > friend.p) {
					x += friend.w;
				}
			}
			ans = Math.min(ans, x);
		}

		println(ans);

		exit();
	}

	static class Friend implements Comparable<Friend> {

		public final int p, w, d; // position, walking speed, hearing distance

		public Friend(int p, int w, int d) {
			this.p = p; this.w = w; this.d = d;
		}

		@Override
		public int compareTo(Friend o) {
			throw new UnsupportedOperationException("Not supported yet.");
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

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}