import java.io.*;
import java.util.*;

public class S_3_Contest_Sites {

	static int n, m, k;
	static long ans = 0L;
	static int[] population;
	static long[] dist1, dist2;
	static Integer[] sorted;
	static ArrayList<int[]>[] adj;
	static PriorityQueue<Integer> pq;
	static final long INF = Long.MAX_VALUE;

	public static void main(String args[]) throws IOException {
		n = readInt(); // towns
		m = readInt(); // roads
		k = readInt(); // secondary site limit

		population = new int[n];
		for (int i = 0; i < n; i++) {
			population[i] = readInt();
		}

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1, b = readInt() - 1, d = readInt();
			adj[b].add(new int[] { a, d });
		}

		dist1 = new long[n];
		Arrays.fill(dist1, INF);
		dist1[0] = 0;

		pq = new PriorityQueue<>((a, b) -> Long.compare(dist1[a], dist1[b]));
		pq.add(0);

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			for (int[] route : adj[cur]) {
				long newDist = dist1[cur] + route[1];
				if (newDist < dist1[route[0]]) {
					dist1[route[0]] = newDist;
					pq.add(route[0]);
				}
			}
		}

		dist2 = new long[n];
		Arrays.fill(dist2, INF);
		dist2[1] = 0;
		pq = new PriorityQueue<>((a, b) -> Long.compare(dist2[a], dist2[b]));
		pq.add(1);

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			for (int[] route : adj[cur]) {
				long newDist = dist2[cur] + route[1];
				if (newDist < dist2[route[0]]) {
					dist2[route[0]] = newDist;
					pq.add(route[0]);
				}
			}
		}

		sorted = new Integer[n];
		for (int i = 0; i < n; i++) {
			sorted[i] = i;
		}
		Arrays.sort(sorted, (a, b) -> Long.compare(dist1[b] - dist2[b], dist1[a] - dist2[a]));

		for (int i : sorted) {
			if (population[i] > 0) {
				if (dist1[i] == INF && dist2[i] == INF) {
					ans = -1;
					break;
				} else if (dist1[i] == INF) {
					k -= population[i];
					if (k < 0) {
						ans = -1;
						break;
					}
					ans += dist2[i] * population[i];
				} else if (dist1[i] <= dist2[i]) {
					ans += dist1[i] * population[i];
				} else {
					int amount2 = Math.min(k, population[i]);
					k -= amount2;
					int amount1 = population[i] - amount2;
					ans += dist1[i] * amount1 + dist2[i] * amount2;
				}
			}
		}

		System.out.println(ans);
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
		return neg ? -ret : ret;
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