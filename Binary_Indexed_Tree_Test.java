import java.io.*;

public class Binary_Indexed_Tree_Test {

	static int n, m;
	static final int MAX = 100_001;
	static int[] a;
	static FenwickTree fenwickTree;

	public static void main(String args[]) throws IOException {

		n = readInt(); // num elements
		m = readInt(); // num operations
		a = new int[n + 1];
		for (int i=1;i<=n;i++) {
			a[i] = readInt();
		}

		fenwickTree = new FenwickTree();

		for (int i=0;i<m;i++) {
			switch (Read()) {
				case 'C': fenwickTree.set(readInt(), readInt()); break;
				case 'S': println(fenwickTree.rangeSum(readInt(), readInt())); break;
				case 'Q': println(fenwickTree.countLower(readInt())); break;
				default: throw new RuntimeException("bad input");
			}
		}
		
		exit();
	}

	static final class FenwickTree {

		private final long[] valueTree;
		private final int[] freqTree = new int[MAX];
		private final int[] freq = new int[MAX];
		

		public FenwickTree() {
			valueTree = new long[n + 1];
			for (int i=1;i<=n;i++) {
				freq[a[i]]++;
			}
			for (int i=1;i<=n;i++) {
				add(i, a[i]);
			}
		}

		private void add(int i, int x) {
			addValue(i, x);
			addFreq(x, 1);
		}

		private void addValue(int i, int x) {
			while (i <= n) {
				valueTree[i] += x;
				i += i & (-i);
			}
		}

		private void addFreq(int x, int count) {
			while (x < MAX) {
				freqTree[x] += count;
				x += x & (-x);
			}
		}

		public void set(int i, int x) {
			addValue(i, x - a[i]);
			addFreq(a[i], -1);
			addFreq(x, 1);
			a[i] = x;
		}

		public long rangeSum(int left, int right) {
			return sum(right) - sum(left - 1);
		}

		private long sum(int i) {
			long sum = 0L;
			while (i > 0) {
				sum += valueTree[i];
				i -= i & (-i);
			}
			return sum;
		}

		public long countLower(int v) {
			long count = 0L;
			while (v > 0) {
				count += freqTree[v];
				v -= v & (-v);
			}
			return count;
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