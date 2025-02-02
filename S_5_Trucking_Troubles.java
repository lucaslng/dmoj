import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class S_5_Trucking_Troubles {
	private final static Reader e = new Reader();

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

		int c = e.nextInt();
		int r = e.nextInt();
		int d = e.nextInt();

		ArrayList<int[]>[] adj = new ArrayList[c];
		for (int i=0;i<c;i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i=0;i<r;i++) {
			int x = e.nextInt() - 1;
			int y = e.nextInt() - 1;
			int w = e.nextInt();
			adj[x].add(new int[] { y, w });
			adj[y].add(new int[] { x, w });
		}

		int[] destinationCities = new int[d];
		for (int i=0;i<d;i++) {
			destinationCities[i] = e.nextInt() - 1;
		}

		int[] weights = new int[c];
		weights[0] = Integer.MAX_VALUE;

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> weights[b] - weights[a]);
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

		int ans = Integer.MAX_VALUE;
		for (int destination : destinationCities) {
			ans = Integer.min(ans, weights[destination]);
		}
		System.out.println(ans);

	}

	private static class Reader {
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