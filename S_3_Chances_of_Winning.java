import java.io.*;
import java.util.*;

public class S_3_Chances_of_Winning {

	static int t, g;
	static int[] scores = new int[4];
	static ArrayList<Game> unfinishedGames = new ArrayList<>(Arrays.asList(
			new Game[] { new Game(0, 1), new Game(0, 2), new Game(0, 3), new Game(1, 2), new Game(1, 3), new Game(2, 3) }));

	public static void main(String args[]) throws IOException {

		t = readInt() - 1; // your team - 0 / 1 / 2 / 3
		g = readInt(); // num games played 0 <= g <= 5

		// read games
		for (int i = 0; i < g; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			Game game = new Game(a, b);
			unfinishedGames.remove(game);
			int scoreA = readInt();
			int scoreB = readInt();
			if (scoreA > scoreB) {
				scores[a] += 3;
			} else if (scoreA < scoreB) {
				scores[b] += 3;
			} else {
				scores[a]++;
				scores[b]++;
			}
		}

		System.out.println(unfinishedGames);

		for (Game game : unfinishedGames) {
			int[] winScoresA = scores.clone();
			winScoresA[game.teamA] += 3;

			int[] winScoresB = scores.clone();
			winScoresB[game.teamB] += 3;

			int[] tieScores = scores.clone();
			tieScores[game.teamA]++;
			tieScores[game.teamB]++;
		}

		exit();
	}

	static int tryGame(int gameId, int[] scores, boolean[] visited) {
		Game game = unfinishedGames.get(gameId);
		int[] winScoresA = scores.clone();
		winScoresA[game.teamA] += 3;

		int[] winScoresB = scores.clone();
		winScoresB[game.teamB] += 3;

		int[] tieScores = scores.clone();
		tieScores[game.teamA]++;
		tieScores[game.teamB]++;

		int sum = 0;
		for (int i=0;i<g;i++) {
			if (!visited[i]) {
				
			}
		}

		return sum;
	}

	static class Game {

		final int teamA, teamB;

		public Game(int teamA, int teamB) {
			this.teamA = Math.min(teamA, teamB);
			this.teamB = Math.max(teamA, teamB);
		}

		@Override
		public int hashCode() {
			return Objects.hash(teamA, teamB);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Game))
				return false;
			Game g = (Game) o;
			return teamA == g.teamA && teamB == g.teamB;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("Game ").append(teamA).append(" vs ").append(teamB).toString();
		}

	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static final DataInputStream din = new DataInputStream(System.in);
	private static final byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static String readLine() throws IOException {
		byte[] buf = new byte[64]; // line length
		int cnt = 0, c;
		while ((c = Read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}

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

	public static long readLong() throws IOException {
		long ret = 0;
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

	public static double readDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = Read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}

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