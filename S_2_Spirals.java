import java.io.*;

public class S_2_Spirals {

	static int x, y, n, rows, cols, r, c, i;
	static int d = 0; // 0 left - 1 down - 2 right - 3 up
	static int size = 1;
	static int s = 0;
	static int[][] g;
	static double sqrt;

	public static void main(String args[]) throws IOException {

		x = readInt();
		y = readInt();
		n = y - x + 1;
		sqrt = Math.sqrt(n);
		rows = (int) Math.ceil(sqrt);
		cols = (int) Math.floor(sqrt);
		r = Math.ceilDiv(rows, 2) - 1;
		c = Math.ceilDiv(cols, 2) - 1;

		i = x;
		g = new int[rows][cols];
		g[r][c] = i;
		r++;
		while (i++ < y) {
			g[r][c] = i;
			if (r > 0 && g[r-1][c] == 0) {
				if (c > 0 && g[r][c-1] == 0) {
					c--;
				} else {
					r--;
				}
			} else {
				if (c + 1 < cols && g[r][c+1] == 0) { // 
					c++;
				} else {
					r++;
				}
			}
			printGrid();
			System.out.println();
		}

		printGrid();
		
		// println(r + " " + c);
		
		exit();
	}

	static void printGrid() {
		for (int[] gr : g) {
			for (int i=0;i<cols-1;i++) {
				System.out.print(gr[i]); System.out.print(" ");
			}
			System.out.println(gr[cols-1]);
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