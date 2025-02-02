import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Silkar {

	final static Reader e = new Reader();
	static int[] d;
	static int[] s;

	public static void main(String[] args) throws IOException, InterruptedException {

		int r = e.nextInt();
		int c = e.nextInt();

		int[][] grid = new int[r][c];
		int[][] dists = new int[r][c];
		for (int i = 0; i < r; i++) {
			Arrays.fill(grid[i], Integer.MAX_VALUE);
			Arrays.fill(dists[i], Integer.MAX_VALUE);
		}
		ArrayList<int[]> floods = new ArrayList<>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				switch (e.read()) {
					case '*' -> {
						grid[i][j] = 0;
						floods.add(new int[] { i, j });
					}
					case 'X' -> grid[i][j] = 0;
					case 'D' -> d = new int[] { i, j };
					case 'S' -> s = new int[] { i, j };
					default -> {
					}
				}
			}
			e.read(); // read \n
		}

		Queue<int[]> q = new LinkedList<>();
		q.addAll(floods);

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int i = cur[0];
			int j = cur[1];

			// up
			if (i > 0 && grid[i][j] + 1 < grid[i - 1][j] && !(i - 1 == d[0] && j == d[1])) {
				grid[i - 1][j] = grid[i][j] + 1;
				q.add(new int[] { i - 1, j });
			}

			// left
			if (j > 0 && grid[i][j] + 1 < grid[i][j - 1] && !(i == d[0] && j - 1 == d[1])) {
				grid[i][j - 1] = grid[i][j] + 1;
				q.add(new int[] { i, j - 1 });
			}

			// down
			if (i + 1 < r && grid[i][j] + 1 < grid[i + 1][j] && !(i + 1 == d[0] && j == d[1])) {
				grid[i + 1][j] = grid[i][j] + 1;
				q.add(new int[] { i + 1, j });
			}

			/// right
			if (j + 1 < c && grid[i][j] + 1 < grid[i][j + 1] && !(i == d[0] && j + 1 == d[1])) {
				grid[i][j + 1] = grid[i][j] + 1;
				q.add(new int[] { i, j + 1 });
			}

		}

		// System.out.println(Arrays.deepToString(grid));
		q.clear();
		q.add(s);
		dists[s[0]][s[1]] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int i = cur[0];
			int j = cur[1];
			
			// up
			if (i > 0 && dists[i][j] + 1 < dists[i - 1][j] && grid[i - 1][j] > dists[i][j] + 1) {
				dists[i - 1][j] = dists[i][j] + 1;
				q.add(new int[] { i - 1, j });
			}

			// left
			if (j > 0 && dists[i][j] + 1 < dists[i][j - 1] && grid[i][j - 1] > dists[i][j] + 1) {
				dists[i][j - 1] = dists[i][j] + 1;
				q.add(new int[] { i, j - 1 });
			}

			// down
			if (i + 1 < r && dists[i][j] + 1 < dists[i + 1][j] && grid[i + 1][j] > dists[i][j] + 1) {
				dists[i + 1][j] = dists[i][j] + 1;
				q.add(new int[] { i + 1, j });
			}

			/// right
			if (j + 1 < c && dists[i][j] + 1 < dists[i][j + 1] && grid[i][j + 1] > dists[i][j] + 1) {
				dists[i][j + 1] = dists[i][j] + 1;
				q.add(new int[] { i, j + 1 });
			}
		}

		// System.out.println(Arrays.deepToString(dists));

		if (dists[d[0]][d[1]] == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(dists[d[0]][d[1]]);
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