import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class Hello_Officer {
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
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
		int[] dists = new int[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		ArrayList<int[]>[] adj = new ArrayList[n];
		for (int i=0;i<n;i++) {
			adj[i] = new ArrayList<>();
		}

		int m = e.nextInt(); // num edges
		int d = e.nextInt() - 1; // dest node
		dists[d] = 0;
		int q = e.nextInt(); // num queries

		for (int i=0;i<m;i++) {
			int x = e.nextInt() - 1;
			int y = e.nextInt() - 1;
			int z = e.nextInt();
			adj[x].add(new int[] { y, z });
			adj[y].add(new int[] { x, z });
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dists[a] - dists[b]);
		pq.add(d);

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			for (int[] route : adj[cur]) {
				int newDist = dists[cur] + route[1];
				if (dists[route[0]] > newDist) {
					dists[route[0]] = newDist;
					pq.add(route[0]);
				}
			}
		}

		for (int i=0;i<q;i++) {
			int query = e.nextInt() - 1;
			if (dists[query] == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(dists[query]);
			}
		}

		pr.close();
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