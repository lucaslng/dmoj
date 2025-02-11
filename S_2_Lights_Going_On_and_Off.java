import java.io.*;
import java.util.*;

public class S_2_Lights_Going_On_and_Off {

	static int rows, cols, combinationCount;
	static BitSet[] lights;
	static HashSet<String> combinations = new HashSet<>();

	public static void main(String args[]) throws IOException {

		rows = readInt();
		cols = readInt();
		lights = new BitSet[rows];
		for (int r = rows - 1; r >= 0; r--) {
			lights[r] = new BitSet(cols);
			for (int c = 0; c < cols; c++) {
				if (readInt() == 1) {
					lights[r].set(c);
				}
			}
		}

		combinationCount = 1 << (rows - 1);
		for (int i = 0; i < combinationCount; i++) {
			BitSet[] cur = new BitSet[rows];
			for (int j = 0; j < rows; j++) {
				cur[j] = (BitSet) lights[j].clone();
			}
			for (int j = 1; j < rows; j++) {
				if ((i & (1 << (j - 1))) != 0) {
					cur[j - 1].xor(cur[j]);
				}
			}
			combinations.add(bitSetToString(cur[rows-1], cols));
		}

		println(combinations.size());

		exit();
	}

	private static String bitSetToString(BitSet bitSet, int size) {
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			sb.append(bitSet.get(i) ? '1' : '0');
		}
		return sb.toString();
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

	static void println(Object o) {
		pr.println(o);
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}