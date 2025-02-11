import java.io.*;

public class Segment_Tree_Test {

	static int n, m;
	static SegTree segTree;
	static int[] a;

	public static void main(String args[]) throws IOException {

		n = readInt(); // num elements
		m = readInt(); // num operations
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = readInt();
		}
		segTree = new SegTree();

		for (int i = 0; i < m; i++) {
			switch (Read()) {
				case 'C':
					segTree.change(readInt()-1, readInt());
					break;
				case 'M':
					println(segTree.minQuery(readInt()-1, readInt()-1));
					break;
				case 'G':
					println(segTree.gcdQuery(readInt()-1, readInt()-1));
					break;
				case 'Q':
					println(segTree.gcdCount(readInt()-1, readInt()-1));
					break;
			}
		}

		pr.close();
	}

	private static class SegTree {

		private final Node root;

		public SegTree() {
			root = buildTree(0, a.length - 1);
		}

		private Node buildTree(int start, int end) {
			Node node = new Node(start, end);
			if (start == end) {
				node.min = a[start];
				node.gcd = a[start];
				node.gcdCount = 1;
				return node;
			}
			int mid = start + (end - start) / 2;
			node.left = buildTree(start, mid);
			node.right = buildTree(mid + 1, end);
			node.min = Math.min(node.left.min, node.right.min);
			node.gcd = gcd(node.left.gcd, node.right.gcd);

			if (node.left.gcd == node.gcd) node.gcdCount += node.left.gcdCount;
			if (node.right.gcd == node.gcd) node.gcdCount += node.right.gcdCount;
			return node;
		}

		public void change(int i, int x) {
			a[i] = x;
			update(root, i);
		}

		private void update(Node node, int i) {
			if (node.start == node.end) {
				node.min = a[i];
				node.gcd = a[i];
				node.gcdCount = 1;
				return;
			}
			if (i <= node.left.end) {
				update(node.left, i);
			} else {
				update(node.right, i);
			}
			node.min = Math.min(node.left.min, node.right.min);
			node.gcd = gcd(node.left.gcd, node.right.gcd);

			node.gcdCount = 0;
			if (node.left.gcd == node.gcd) node.gcdCount += node.left.gcdCount;
			if (node.right.gcd == node.gcd) node.gcdCount += node.right.gcdCount;
		}

		public int minQuery(int start, int end) {
			return root.minQuery(start, end);
		}

		public int gcdQuery(int start, int end) {
			return root.gcdQuery(start, end);
		}

		public int gcdCount(int start, int end) {
			return root.gcdCount(gcdQuery(start, end), start, end);
		}

	}

	static private class Node {
		public final int start, end;
		public Node left = null;
		public Node right = null;
		public int min, gcd, gcdCount;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int minQuery(int start, int end) {
			if (start > this.end || end < this.start) return Integer.MAX_VALUE;
			if (start <= this.start && end >= this.end) return min;
			return Math.min(left.minQuery(start, end), right.minQuery(start, end));
		}

		public int gcdQuery(int start, int end) {
			if (start > this.end || end < this.start) return 0;
			if (start <= this.start && end >= this.end) return gcd;
			return gcd(left.gcdQuery(start, end), right.gcdQuery(start, end));
		}

		public int gcdCount(int v, int start, int end) {
			if (start > this.end || end < this.start) return 0;
			if (start <= this.start && end >= this.end) {
				if (gcd == v) return gcdCount;
				return 0;
			}
			return this.left.gcdCount(v, start, end) + this.right.gcdCount(v, start, end);
		}

	}

	static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
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

	static void println(Object o) {
		pr.println(o);
	}
}