import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class S_3_Degrees_Of_Separation {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException, InterruptedException {	

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

		HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
		for (int i = 1; i <= 18; i++) {
			adj.put(i, new ArrayList<>());
		}
		adj.get(1).add(6);
		adj.get(2).add(6);
		adj.get(3).addAll(Arrays.asList(new Integer[] {4, 5, 6, 15}));
		adj.get(4).addAll(Arrays.asList(new Integer[] {3, 5, 6}));
		adj.get(5).addAll(Arrays.asList(new Integer[] {3, 4, 6}));
		adj.get(6).addAll(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 7}));
		adj.get(7).addAll(Arrays.asList(new Integer[] {6, 8}));
		adj.get(8).addAll(Arrays.asList(new Integer[] {7, 9}));
		adj.get(9).addAll(Arrays.asList(new Integer[] {8, 10, 12}));
		adj.get(10).addAll(Arrays.asList(new Integer[] {9, 11}));
		adj.get(11).addAll(Arrays.asList(new Integer[] {10, 12}));
		adj.get(12).addAll(Arrays.asList(new Integer[] {9, 11, 13}));
		adj.get(13).addAll(Arrays.asList(new Integer[] {12, 14, 15}));
		adj.get(14).add(13);
		adj.get(15).addAll(Arrays.asList(new Integer[] {3, 13}));
		adj.get(16).addAll(Arrays.asList(new Integer[] {17, 18}));
		adj.get(17).addAll(Arrays.asList(new Integer[] {16, 18}));
		adj.get(18).addAll(Arrays.asList(new Integer[] {16, 17}));
		
		char a;
		int x;
		int y;
		while ((a = readChar()) != 'q') {
			switch (a) {
				case 'i': // make person x and person y friends. If they are already friends, no change needs to be made. If either is a new person, add them.
					x = readInt();
					// System.out.println(x + " " + adj.size());
					adj.putIfAbsent(x, new ArrayList<>());
					y = readInt();
					adj.putIfAbsent(y, new ArrayList<>());
					adj.get(x).add(y);
					adj.get(y).add(x);
					break;
				case 'd':
					x = readInt();
					y = readInt();
					adj.get(x).remove(Integer.valueOf(y));
					adj.get(y).remove(Integer.valueOf(x));
					break;
				case 'n':
					x = readInt();
					pr.println(adj.get(x).size());
					break;
				case 'f':
					x = readInt();
					HashSet<Integer> friendsOfFriends = new HashSet<>();
					HashSet<Integer> friends = new HashSet<>();
					for (Integer friend: adj.get(x)) {
						friends.add(friend);
						for (Integer friendOfFriend: adj.get(friend)) {
							friendsOfFriends.add(friendOfFriend);
						}
					}
					friendsOfFriends.removeAll(friends);
					friendsOfFriends.remove(x);
					pr.println(friendsOfFriends.size());
					break;
				case 's':
					x = readInt();
					y = readInt();
					HashMap<Integer, Integer> d = new HashMap<>();
					HashSet<Integer> v = new HashSet<>();
					Queue<Integer> q = new LinkedList<>();
					q.add(x);
					d.put(x, 0);
					v.add(x);
					while (!q.isEmpty()) {
						int cur = q.poll();
						if (cur == y) {
							break;
						}
						for (int friend : adj.get(cur)) {
							if (!v.contains(friend)) {
								q.add(friend);
								d.put(friend, d.get(cur) + 1);
								v.add(friend);
							}
						}
					}
					if (d.containsKey(y)) {
						pr.println(d.get(y));
					} else {
						pr.println("Not connected");
					}
					break;
				default:
			}
		}

		pr.close();
	}
	static class Pair implements Comparable<Pair> {
		int f,s;
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