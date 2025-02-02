import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class P_5_Migrant_Mascot {
	final static Reader e = new Reader();

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Size of N Complexity needed to pass
		// N <= 10 O(N!)
		// N <= 20 O(2^n)
		// N <= 100 O(N^4)
		// N <= 400 O(N^3)
		// N <= 5000 O(N^2)
		// N <= 100000 O(Nsqrt(N)), O(Nlog(N)), O(Nlog(log(N)))
		// N <= 1000000 O(N), O(Nlog(N))
		// N <= 10000000 O(N)
		// N <= 10 ^ 12 O(sqrt(N)), O(log(N))
		// N <= 10 ^ 18 O(log(N)), O(1)

		int n = e.nextInt(); // num nodes
		int[] weights = new int[n];
		weights[0] = Integer.MAX_VALUE;
		ArrayList<int[]>[] adj = new ArrayList[n];
		for (int i=0;i<n;i++) {
			adj[i] = new ArrayList<>();
		}

		int m = e.nextInt();
		for (int i=0;i<m;i++) {
			int a = e.nextInt() - 1;
			int b = e.nextInt() - 1;
			int p = e.nextInt();
			adj[a].add(new int[] { b, p });
			adj[b].add(new int[] { a, p });
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(((a, b) -> weights[b] - weights[a]));
		pq.add(0);

		while (!pq.isEmpty()) {
			int cur = pq.poll();

			for (int[] route : adj[cur]) {
				int newWeight = Integer.min(weights[cur], route[1]);
				if (weights[route[0]] < newWeight) {
					weights[route[0]] = newWeight;
					pq.add(route[0]);
				}
			}
		}

		System.out.println(0);
		for (int i=1;i<n;i++) {
			System.out.println(weights[i]);
		}

	}

	static class Pair implements Comparable<Pair> {
		int f, s;

		Pair(int f, int s) {
			this.f = f;
			this.s = s;
		}

		@Override
		public int compareTo(Pair other) {
			if (this.f != other.f)
				return this.f - other.f;
			return this.s - other.s;
		}
	}

	static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private final DataInputStream din;
		private final byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}
			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}