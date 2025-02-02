import java.io.*;
import java.util.*;

public class S_3_RoboThieves {

	static int rows, cols;
	static char[][] grid;
	static int[][] dists;
	static boolean[][] camgrid;
	static PriorityQueue<int[]> q;
	static int[] s;
	static ArrayList<int[]> cameras = new ArrayList<>();

	public static void main(String args[]) throws IOException {

		rows = readInt();
		cols = readInt();

		grid = new char[rows][cols];
		camgrid = new boolean[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c] = (char) Read();
				if (grid[r][c] == 'C') {
					camgrid[r][c] = true;
					cameras.add(new int[] { r, c });
				} else if (grid[r][c] == 'S') {
					s = new int[] { r, c };
					grid[r][c] = '.';
				}
			}
			Read();
		}

		for (int[] camera : cameras) {
			int r = camera[0] - 1;
			int c = camera[1];
			while (grid[r][c] != 'W') {
				if (grid[r][c] == '.')
					camgrid[r][c] = true;
				r--;
			}
			r = camera[0] + 1;
			while (grid[r][c] != 'W') {
				if (grid[r][c] == '.')
					camgrid[r][c] = true;
				r++;
			}
			r = camera[0];
			c = camera[1] - 1;
			while (grid[r][c] != 'W') {
				if (grid[r][c] == '.')
					camgrid[r][c] = true;
				c--;
			}
			c = camera[1] + 1;
			while (grid[r][c] != 'W') {
				if (grid[r][c] == '.')
					camgrid[r][c] = true;
				c++;
			}
		}

		// edge case, start point can be viewed by camera
		if (camgrid[s[0]][s[1]]) {
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					if (grid[r][c] == '.' && !(r == s[0] && c == s[1])) {
						println(-1);
					}
				}
			}
			exit();
		}

		int[][] dists = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			Arrays.fill(dists[r], Integer.MAX_VALUE);
		}
		q = new PriorityQueue<>((a, b) -> dists[a[0]][a[1]] - dists[b[0]][b[1]]);
		q.add(s);
		dists[s[0]][s[1]] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// if currently standing on a conveyor belt
			if (grid[cur[0]][cur[1]] != '.') {
				int r = cur[0];
				int c = cur[1];
				switch (grid[cur[0]][cur[1]]) {
					case 'L': c--; break;
					case 'R': c++; break;
					case 'U': r--; break;
					case 'D': r++; break;
					default:
				}
				if (dists[r][c] > dists[cur[0]][cur[1]] && grid[r][c] != 'W' && !camgrid[r][c]) {
					dists[r][c] = dists[cur[0]][cur[1]];
					q.add(new int[] { r, c });
				}
				continue;
			}

			int r = cur[0] - 1;
			int c = cur[1];
			if (dists[r][c] > dists[cur[0]][cur[1]] + 1 && grid[r][c] != 'W' && !camgrid[r][c]) {
				dists[r][c] = dists[cur[0]][cur[1]] + 1;
				q.add(new int[] { r, c });
			}
			r = cur[0] + 1;
			if (dists[r][c] > dists[cur[0]][cur[1]] + 1 && grid[r][c] != 'W' && !camgrid[r][c]) {
				dists[r][c] = dists[cur[0]][cur[1]] + 1;
				q.add(new int[] { r, c });
			}
			r = cur[0];
			c = cur[1] - 1;
			if (dists[r][c] > dists[cur[0]][cur[1]] + 1 && grid[r][c] != 'W' && !camgrid[r][c]) {
				dists[r][c] = dists[cur[0]][cur[1]] + 1;
				q.add(new int[] { r, c });
			}
			r = cur[0];
			c = cur[1] + 1;
			if (dists[r][c] > dists[cur[0]][cur[1]] + 1 && grid[r][c] != 'W' && !camgrid[r][c]) {
				dists[r][c] = dists[cur[0]][cur[1]] + 1;
				q.add(new int[] { r, c });
			}
		}

		// for (int i = 0; i < rows; i++) {
		// 	println(Arrays.toString(dists[i]));
		// }
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == '.' && !(r == s[0] && c == s[1])) {
					if (dists[r][c] == Integer.MAX_VALUE) {
						println(-1);
					} else {
						println(dists[r][c]);
					}
				}
			}
		}

		exit();
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static String read() throws IOException {
		byte[] ret = new byte[1024];
		int idx = 0;
		byte c = Read();
		while (c <= ' ') {
			c = Read();
		}
		do {
			ret[idx++] = c;
			c = Read();
		} while (c != -1 && c != ' ' && c != '\n' && c != '\r');
		return new String(ret, 0, idx);
	}

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

	static void print(Object o) {
		pr.print(o);
	}

	static void println(Object o) {
		pr.println(o);
	}

	static void println() {
		pr.println();
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}