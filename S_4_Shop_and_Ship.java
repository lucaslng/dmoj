import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class S_4_Shop_and_Ship {
  static final Reader e = new Reader();

  public static void main(String[] args) throws IOException, InterruptedException {
    int n = e.nextInt(); // number of cities
    City[] cities = new City[n];
    for (int i = 0; i < n; i++) {
      cities[i] = new City(i);
    }

    int t = e.nextInt(); // number of routes
    for (int i = 0; i < t; i++) {
      int x = e.nextInt() - 1; // city x id
      int y = e.nextInt() - 1; // city y id
      int cost = e.nextInt();
      cities[x].routes.add(new Route(y, cost));
      cities[y].routes.add(new Route(x, cost));
    }

    int k = e.nextInt(); // number of cities that sell pencils
    HashSet<PencilCityData> pencilCityDatas = new HashSet<>(k);
    for (int i = 0; i < k; i++) {
      int id = e.nextInt() - 1;
      int pencilCost = e.nextInt();
      pencilCityDatas.add(new PencilCityData(id, pencilCost));
    }

    int destId = e.nextInt() - 1; // destination city id

    PriorityQueue<Integer> q = new PriorityQueue<>(n, Comparator.comparingInt(i -> cities[i].calculatedCost));
    cities[destId].calculatedCost = 0;
    q.add(destId);

    while (!q.isEmpty()) {
      int cityId = q.poll();

      for (Route route : cities[cityId].routes) {
        int newCost = cities[cityId].calculatedCost + route.cost;
        if (cities[route.destId].calculatedCost > newCost) {
          cities[route.destId].calculatedCost = newCost;
          q.add(route.destId);
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for (PencilCityData pencilCityData : pencilCityDatas) {
      ans = Math.min(ans, cities[pencilCityData.id].calculatedCost + pencilCityData.cost);
    }
    System.out.println(ans);

  }

  // route object with a destination id and cost
  static class Route {
    final int destId, cost;

    Route(int dest, int cost) {
      this.destId = dest;
      this.cost = cost;
    }
  }

  // city that stores its id, calculated cost, and adjacent cities and the costs
  // of the routes
  static class City {
    public final int id;
    public int calculatedCost;
    public final ArrayList<Route> routes;

    City(int id) {
      this.id = id;
      this.calculatedCost = 1000000000;
      this.routes = new ArrayList<>();
    }
  }

  // store city id and cost information for pencil cities
  static class PencilCityData {
    public final int id, cost;

    public PencilCityData(int id, int cost) {
      this.id = id;
      this.cost = cost;
    }

    @Override
    public int hashCode() {
      return this.id;
    }

    @Override
    public boolean equals(Object o) {
      if (this.getClass() != o.getClass())
        return false;
      PencilCityData pencilCityData = (PencilCityData) o;
      return this.id == pencilCityData.id;
    }
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
  }
}