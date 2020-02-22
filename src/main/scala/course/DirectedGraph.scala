package course

class DirectedGraph[V](edgeMap: Map[V, Set[V]]) extends Graph[V] {
  override def vertices: Set[V] = edgeMap.keySet

  override def edges: Set[(V, V)] = edgeMap.flatMap { case (a, seqB) => seqB.map(b => a -> b) }.toSet

  override def addEdge(a: V, b: V): Graph[V] = {
    val neighboursA = neighbours(a) + b
    new DirectedGraph(edgeMap + (a -> neighboursA))
  }

  override def neighbours(a: V): Set[V] = edgeMap.getOrElse(a, Set.empty)
}
