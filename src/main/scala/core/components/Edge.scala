package core.components

import core.components.EdgeSkelet.{DirectEdgeSkelet, DirectableEdgeSkelet, EdgeSkeletType, MultiEdgeSkelet, MultiableEdgeSkelet, NonDirectEdgeSkelet, NonMultiEdgeSkelet}

sealed trait Edge[+I, +O]
  extends EdgeSkelet[I, O, _, _]
    with EdgeSkeletType
    with MultiableEdgeSkelet
    with DirectableEdgeSkelet
    with TimeProcess[_]

object Edge {

  trait Edge1Type[+T] extends Edge[T, T]

  trait Edge2Type[+I, +O] extends Edge[I, O]


  trait DirectEdgeTrait[+T] extends Edge1Type[T] with NonMultiEdgeSkelet[T, T] with DirectEdgeSkelet[T, T]

  trait EdgeSimpleTrait[+T] extends Edge1Type[T] with NonMultiEdgeSkelet[T, T] with NonDirectEdgeSkelet[T, T]

  trait DirectMultiEdgeTrait[+T] extends Edge1Type[T] with MultiEdgeSkelet[T, T] with DirectEdgeSkelet[T, T]

  trait MultiEdgeTrait[+T] extends Edge1Type[T] with MultiEdgeSkelet[T, T] with NonDirectEdgeSkelet[T, T]

  trait DirectEdge2TypeTrait[+I, +O] extends Edge2Type[I, O] with NonMultiEdgeSkelet[I, O] with DirectEdgeSkelet[I, O]

  trait EdgeSimple2TypeTrait[+I, +O] extends Edge2Type[I, O] with NonMultiEdgeSkelet[I, O] with NonDirectEdgeSkelet[I, O]

  trait DirectMulti2TypeTrait[+I, +O] extends Edge2Type[I, O] with MultiEdgeSkelet[I, O] with DirectEdgeSkelet[I, O]

  trait MultiEdge2TypeTrait[+I, +O] extends Edge2Type[I, O] with MultiEdgeSkelet[I, O] with NonDirectEdgeSkelet[I, O]

}