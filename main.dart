import 'dart:math';
import 'dart:io';
import 'dart:collection';

class StringTokenizer {

  static DoubleLinkedQueue<String> q = DoubleLinkedQueue();
  final Pattern pattern = ' ';

  void readNextLine() {
    q.addAll(readLine().split(pattern));
  }
  
  String next() {
    if (q.isEmpty) readNextLine();
    return q.removeFirst();
  }

  String readLine() {
    return stdin.readLineSync()!;
  }

  int readInt() {
    return int.parse(next());
  }

  double readDouble() {
    return double.parse(next());
  }
}

final pr = (Object object) => stdout.write(object);

void main() {
  StringTokenizer st = StringTokenizer();

  while (true) {
    pr(st.next());
  }

}