package course

class UndirectedGraph[V](edgeMap: Map[V, Set[V]]) extends DirectedGraph[V](edgeMap) {
  override def addEdge(a: V, b: V): Graph[V] = {
    val neighbourA = neighbours(a) + b
    val neighbourB = neighbours(b) + a
    new UndirectedGraph(edgeMap + (a -> neighbourA, b -> neighbourB))
  }
}
