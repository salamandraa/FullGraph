package core.components

import core.components.EdgeSkelet.{DirectEdgeSkelet, DirectableEdgeSkelet, EdgeSkelet1Type, EdgeSkelet2Type, EdgeSkeletType, MultiEdgeSkelet, MultiableEdgeSkelet, NonDirectEdgeSkelet, NonMultiEdgeSkelet}

sealed trait Edge[+I, +E, +O]
  extends EdgeSkelet[I, E, O]
    with EdgeSkeletType
    with MultiableEdgeSkelet
    with DirectableEdgeSkelet
    with TimeProcess[Any]

object Edge {

  trait Edge1Type[+T, +E] extends EdgeSkelet1Type[T, E]

  trait Edge2Type[+I, +E, +O] extends EdgeSkelet2Type[I, E, O]


  trait DirectEdgeTrait[+T, +E] extends Edge1Type[T, E] with NonMultiEdgeSkelet[T, E, T] with DirectEdgeSkelet[T, E, T]

  trait SimpleEdgeTrait[+T, +E] extends Edge1Type[T, E] with NonMultiEdgeSkelet[T, E, T] with NonDirectEdgeSkelet[T, E, T]

  trait DirectMultiEdgeTrait[+T, +E] extends Edge1Type[T, E] with MultiEdgeSkelet[T, E, T] with DirectEdgeSkelet[T, E, T]

  trait MultiEdgeTrait[+T, +E] extends Edge1Type[T, E] with MultiEdgeSkelet[T, E, T] with NonDirectEdgeSkelet[T, E, T]

  trait DirectEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with NonMultiEdgeSkelet[I, E, O] with DirectEdgeSkelet[I, E, O]

  trait EdgeSimpleEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with NonMultiEdgeSkelet[I, E, O] with NonDirectEdgeSkelet[I, E, O]

  trait DirectMultiEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with MultiEdgeSkelet[I, E, O] with DirectEdgeSkelet[I, E, O]

  trait MultiEdge2TypeTrait[+I, +E, +O] extends Edge2Type[I, E, O] with MultiEdgeSkelet[I, E, O] with NonDirectEdgeSkelet[I, E, O]

}