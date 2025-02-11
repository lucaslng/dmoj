import java.io.*;
import java.util.*;

public class Binary_Search_Tree_Test {

	static int n, m, lastAns;
	static Integer a[];
	static TreeSet<Integer> tree;

	public static void main(String args[]) throws IOException {

		n = readInt();
		m = readInt();
		a = new Integer[n];
		for (int i=0;i<n;i++) {
			a[i] = readInt();
		}

		tree = new TreeSet<>(Arrays.asList(a));

		for (int i=0;i<m;i++) {
			switch (Read()) {
				case 'I': break;
				case 'R': break;
				case 'S': break;
				case 'L': break;
			}
		}
		
		exit();
	}

	static int v() throws IOException {
		return readInt();
		// return readInt() ^ lastAns;
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

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