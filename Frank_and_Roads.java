import java.io.*;
import java.util.*;

public class Frank_and_Roads {
	final static Reader e = new Reader();
	static int[] dists;

	public static void main(String[] args) throws IOException, InterruptedException {

		int km = e.nextInt(); // safe distance
		int n = e.nextInt() + 1; // num buildings
		int m = e.nextInt(); // num roads
		int g = e.nextInt(); // num food basics

		int[] foodBasics = new int[g];
		for (int i = 0; i < g; i++) {
			foodBasics[i] = e.nextInt();
		}

		ArrayList<int[]>[] adj = new ArrayList[n]; // adj[building] = {destination, distance}
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			adj[e.nextInt()].add(new int[] {e.nextInt(), e.nextInt()});
		}

		dists = new int[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dists[i]));
		pq.add(0);
		dists[0] = 0;

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

		int ans = 0;
		for (int foodBasic : foodBasics) {
			if (dists[foodBasic] <= km) {
				ans++;
			}
		}

		System.out.println(ans);

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

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
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
	}
}