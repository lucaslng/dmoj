import java.io.*;

public class Dynamic_Range_Minimum_Test {

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
				case 'M':
					segTree.modify(readInt(), readInt());
					break;
				case 'Q':
					System.out.println(segTree.minQuery(readInt(), readInt()));
					break;
			}
		}
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
				return node;
			}
			int mid = start + (end - start) / 2;
			node.left = buildTree(start, mid);
			node.right = buildTree(mid + 1, end);
			node.min = Math.min(node.left.min, node.right.min);
			return node;
		}

		public void modify(int i, int x) {
			a[i] = x;
			update(root, i);
		}

		private void update(Node node, int i) {
			if (node.start == node.end) {
				node.min = a[i];
				return;
			}
			if (i <= node.left.end) {
				update(node.left, i);
			} else {
				update(node.right, i);
			}
			node.min = Math.min(node.left.min, node.right.min);
		}

		public int minQuery(int start, int end) {
			return root.minQuery(start, end);
		}

	}

	static private class Node {
		public final int start, end;
		public Node left = null;
		public Node right = null;
		public int min;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int minQuery(int start, int end) {
			if (start > this.end || end < this.start)
				return Integer.MAX_VALUE;
			if (this.start == this.end)
				return a[this.start];
			if (start <= this.start && end >= this.end)
				return min;
			return Math.min(left.minQuery(start, end), right.minQuery(start, end));
		}

	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;

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
}