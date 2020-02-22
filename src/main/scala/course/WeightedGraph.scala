package course

case class EdgeWeight[T](destination: T, weight: Int)

class WeightedGraph[V](edgeMap: Map[V, Set[EdgeWeight[V]]]) extends Graph[V] {
  override def vertices: Set[V] = edgeMap.keySet


  override def edges: Set[(V, V)] = edgeMap.flatMap { case (a, seqB) => seqB.map(b => a -> b.destination) }.toSet

  def addEdge(a: V, b: EdgeWeight[V]): Graph[V] = {
    val neighboursA = neighboursWeight(a) + b
    new WeightedGraph(edgeMap + (a -> neighboursA))
  }

  override def addEdge(a: V, b: V): Graph[V] = this.addEdge(a, EdgeWeight(b, 0))

  def neighboursWeight(a: V): Set[EdgeWeight[V]] = edgeMap.getOrElse(a, Set.empty)

  override def neighbours(a: V): Set[V] = neighboursWeight(a).map(_.destination)
}
