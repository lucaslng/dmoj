import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class S_3_Maze {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {	

    // Size of N        Complexity needed to pass
    // N <= 10          O(N!)
    // N <= 20          O(2^n)
    // N <= 100         O(N^4)
    // N <= 400         O(N^3)
    // N <= 5000        O(N^2)
    // N <= 100000      O(Nsqrt(N)), O(Nlog(N)), O(Nlog(log(N)))
    // N <= 1000000     O(N), O(Nlog(N))
    // N <= 10000000    O(N)
    // N <= 10 ^ 12     O(sqrt(N)), O(log(N))
    // N <= 10 ^ 18     O(log(N)), O(1)

		int t = readByte();
		for (int l=0;l<t;l++) {
			byte r = readByte();
			byte c = readByte();
			boolean[][][] adj = new boolean[r][c][4];
			char[][] grid = new char[r][c];
			for (byte y=0;y<r;y++) {
				String line = readLine();
				for (byte x=0;x<c;x++) {
					grid[y][x] = line.charAt(x);
				}
			}
			for (byte y=0;y<r;y++) {
				for (byte x=0;x<c;x++) {
					if (grid[y][x] == '+' || grid[y][x] == '|') {
						if (y >= 1 && grid[y-1][x] != '*') {
							adj[y][x][0] = true;
						}
						if (y < r - 1 && grid[y+1][x] != '*') {
							adj[y][x][2] = true;
						}
					}
					if (grid[y][x] == '+' || grid[y][x] == '-') {
						if ((x >= 1 && grid[y][x-1] != '*')) {
							adj[y][x][3] = true;
						}
						if (x < c - 1 && grid[y][x+1] != '*') {
							adj[y][x][1] = true;
						}
					}
				}
			}

			short[][] dist = new short[r][c];
			for (short[] row: dist) {
				Arrays.fill(row, (short) -1);
			}
			dist[0][0] = 1;
			boolean[][] visited = new boolean[r][c];
			Queue<Byte[]> q = new LinkedList<>();
			q.add(new Byte[] {0, 0});
			visited[0][0] = true;

			while (!q.isEmpty()) {
				Byte[] node = q.poll();
				byte y = node[0];
				byte x = node[1];
				// System.out.println(y + " " + x);
				// System.out.println(Arrays.toString(adj[y][x]));

				if (y == r - 1 && x == c - 1) break;

				if (adj[y][x][0]) {
					if (!visited[y-1][x]) {
						dist[y-1][x] = (short) (dist[y][x] + 1);
						visited[y-1][x] = true;
						q.add(new Byte[] {(byte) (y-1), x});
					}
				}
				if (adj[y][x][2]) {
					if (!visited[y+1][x]) {
						dist[y+1][x] = (short) (dist[y][x] + 1);
						visited[y+1][x] = true;
						q.add(new Byte[] {(byte) (y+1), x});
					}
				}
				if (adj[y][x][3]) {
					if (!visited[y][x-1]) {
						dist[y][x-1] = (short) (dist[y][x] + 1);
						visited[y][x-1] = true;
						q.add(new Byte[] {y, (byte) (x-1)});
					}
				}
				if (adj[y][x][1]) {
					if (!visited[y][x+1]) {
						dist[y][x+1] = (short) (dist[y][x] + 1);
						visited[y][x+1] = true;
						q.add(new Byte[] {y, (byte) (x+1)});
					}
				}
			}
			pr.println(dist[r-1][c-1]);


		}

		pr.close();
	}
	static class Pair implements Comparable<Pair> {
		final int f,s;
		Pair(int f,int s) {
			this.f=f; this.s=s;
		}
    @Override
		public int compareTo(Pair other) {
			if (this.f!=other.f) return this.f-other.f;
			return this.s-other.s;
		}
	}
	static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong() throws IOException {
		return Long.parseLong(next());
	}
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
	static short readShort() throws IOException {
		return Short.parseShort(next());
	}
	static byte readByte() throws IOException {
		return Byte.parseByte(next());
	}
	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}
	static char readChar() throws IOException {
		return next().charAt(0);
	}
	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}