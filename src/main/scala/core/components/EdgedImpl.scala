package core.components


import core.components.Edge._
import core.components.TimeProcess.InvariantTimeProcess


object EdgedImpl {

  case class DirectEdge[+T, +E](inVertex: T, outVertex: T, valueEdge: E = Unit) extends DirectEdgeTrait[T, E] with InvariantTimeProcess

  case class SimpleEdge[+T, +E](inVertex: T, outVertex: T, valueEdge: E = Unit) extends SimpleEdgeTrait[T, E] with InvariantTimeProcess

  case class DirectMultiEdge[+T, +E](edges: Seq[DirectEdge[T, E]]) extends DirectMultiEdgeTrait[T, E] with InvariantTimeProcess

  case class MultiEdge[+T, +E](edges: Seq[SimpleEdge[T, E]]) extends MultiEdgeTrait[T, E] with InvariantTimeProcess

  case class DirectEdge2Type[+I, +E, +O](inVertex: I, outVertex: O, valueEdge: E = Unit) extends DirectEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class SimpleEdge2Type[+I, +E, +O](inVertex: I, outVertex: O, valueEdge: E = Unit) extends EdgeSimpleEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class DirectMultiEdge2Type[+I, +E, +O](edges: Seq[DirectEdge2Type[I, E, O]]) extends DirectMultiEdge2TypeTrait[I, E, O] with InvariantTimeProcess

  case class MultiEdge2Type[+I, +E, +O](edges: Seq[SimpleEdge2Type[I, E, O]]) extends MultiEdge2TypeTrait[I, E, O] with InvariantTimeProcess

}
