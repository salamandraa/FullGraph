package core.edges


import core.edges.Edge._
import core.edges.TimeProcess.InvariantTimeProcess


object EdgedImpl {

  case class DirectEdge[+V, +E](inVertex: V, outVertex: V, valueEdge: E = Unit) extends DirectEdgeTrait[V, E] with InvariantTimeProcess

  case class SimpleEdge[+V, +E](inVertex: V, outVertex: V, valueEdge: E = Unit) extends SimpleEdgeTrait[V, E] with InvariantTimeProcess

  case class DirectMultiEdge[+V, +E](edges: Seq[DirectEdge[V, E]]) extends DirectMultiEdgeTrait[V, E] with InvariantTimeProcess

  case class MultiEdge[+V, +E](edges: Seq[SimpleEdge[V, E]]) extends MultiEdgeTrait[V, E] with InvariantTimeProcess

  case class DirectEdge2Type[+I, +E, +O](inVertex: I, outVertex: O, valueEdge: E = Unit) extends DirectEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class SimpleEdge2Type[+I, +E, +O](inVertex: I, outVertex: O, valueEdge: E = Unit) extends EdgeSimpleEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class DirectMultiEdge2Type[+I, +E, +O](edges: Seq[DirectEdge2Type[I, E, O]]) extends DirectMultiEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class MultiEdge2Type[+I, +E, +O](edges: Seq[SimpleEdge2Type[I, E, O]]) extends MultiEdge2TypeTrait[I, E, O] with InvariantTimeProcess

}
