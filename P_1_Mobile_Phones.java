import java.io.*;

public class P_1_Mobile_Phones {

	static int s;
	static byte o;
	static FenwickTree fenwickTree;

	public static void main(String args[]) throws IOException {

		Read(); // read zero
		s = readInt() + 1;

		fenwickTree = new FenwickTree();

		while ((o = Read()) != '3') {
			if (o == '1') fenwickTree.add(readInt() + 1, readInt() + 1, readInt());
			else println(fenwickTree.rangeSum(readInt() + 1, readInt() + 1, readInt() + 1, readInt() + 1));
		}
		
		exit();
	}

	static final class FenwickTree {
		
		private final int[][] tree;

		public FenwickTree() {
			tree = new int[s][s];
		}

		public void add(int x, int y, int v) {
			for (;x<s;x+=x&-x) {
				for (int j=y;j<s;j+=j&-j) {
					tree[x][j] += v;
				}
			}
		}

		public int rangeSum(int l, int b, int r, int t) {
			return sum(r, t) - sum(l - 1, t) - sum(r, b - 1) + sum(l - 1, b - 1);
		}

		private int sum(int x, int y) {
			int sum = 0;
			for (;x>0;x-=x&(-x)) {
				for (int j=y;j>0;j-=j&-j) {
					sum += tree[x][j];
				}
			}
			return sum;
		}

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
	static void println(Object o) {
		pr.println(o);
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}