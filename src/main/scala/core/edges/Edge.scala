package core.edges

import core.edges.EdgeSkelet.{DirectEdgeSkelet, DirectableEdgeSkelet, EdgeSkelet1Type, EdgeSkelet2Type, EdgeSkeletType, MultiEdgeSkelet, MultiableEdgeSkelet, NonDirectEdgeSkelet, NonMultiEdgeSkelet}
import core.vertex.Vertex

sealed trait Edge[+I, +E, +O]
  extends EdgeSkelet[I, E, O]
    with EdgeSkeletType
    with MultiableEdgeSkelet
    with DirectableEdgeSkelet
    with TimeProcess

object Edge {

  def inferVertices[I](edges: Seq[Edge[I, _, I]]): Seq[Vertex[I]] = {
    val (inV, outV) = inferVertices2Type(edges)
    inV ++ outV
  }

  def inferVertices2Type[I, O](edges: Seq[Edge[I, _, O]]): (Seq[Vertex[I]], Seq[Vertex[O]]) = {

    val (leftDouble, rightDouble) = edges.foldLeft(Seq.empty[Vertex[I]] -> Seq.empty[Vertex[O]]) { (acc, e) =>
      val (inV, outV) = acc
      e match {
        case eMulti: MultiEdgeSkelet[I, _, O] => (inV ++ eMulti.inVertices) -> (outV ++ eMulti.outVertices)
        case eNonMulti: NonMultiEdgeSkelet[I, _, O] =>
          val inVertices = inV :+ Vertex(eNonMulti.inVertex)
          val outVetices = outV :+ Vertex(eNonMulti.outVertex)
          inVertices -> outVetices
      }

    }
    leftDouble.distinct -> rightDouble.distinct
  }

  trait Edge1Type[+V, +E] extends EdgeSkelet1Type[V, E]

  trait Edge2Type[+I, +E, +O] extends EdgeSkelet2Type[I, E, O]


  trait DirectEdgeTrait[+V, +E] extends Edge1Type[V, E] with NonMultiEdgeSkelet[V, E, V] with DirectEdgeSkelet[V, E, V]

  trait SimpleEdgeTrait[+V, +E] extends Edge1Type[V, E] with NonMultiEdgeSkelet[V, E, V] with NonDirectEdgeSkelet[V, E, V]

  trait DirectMultiEdgeTrait[+V, +E] extends Edge1Type[V, E] with MultiEdgeSkelet[V, E, V] with DirectEdgeSkelet[V, E, V]

  trait MultiEdgeTrait[+V, +E] extends Edge1Type[V, E] with MultiEdgeSkelet[V, E, V] with NonDirectEdgeSkelet[V, E, V]

  trait DirectEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with NonMultiEdgeSkelet[I, E, O] with DirectEdgeSkelet[I, E, O]

  trait EdgeSimpleEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with NonMultiEdgeSkelet[I, E, O] with NonDirectEdgeSkelet[I, E, O]

  trait DirectMultiEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with MultiEdgeSkelet[I, E, O] with DirectEdgeSkelet[I, E, O]

  trait MultiEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with MultiEdgeSkelet[I, E, O] with NonDirectEdgeSkelet[I, E, O]

}