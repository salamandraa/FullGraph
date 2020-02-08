package core.components

import scala.util.Try


abstract class EdgeSkelet[_[_, _] : PairVertex[I, O], +I, +O] {
  def validate: Try[Unit]

  protected def valueVin: Vertex[I]

  protected def valueVout: Vertex[O]

  def valueIn: I = valueVin.value

  def valueOut: O = valueVout.value
}

object EdgeSkelet {

  // types
  trait EdgeSkeletType

  trait EdgeSkelet1Type[+T] extends EdgeSkelet[T, T, _, _] with EdgeSkeletType

  trait EdgeSkelet2Type[+I, +O] extends EdgeSkelet[I, O, _, _] with EdgeSkeletType

  // multi
  trait MultiableEdgeSkelet {
    def isMulti: Boolean
  }

  trait MultiEdgeSkelet[+I, +O] extends EdgeSkelet[I, O, _, _] with MultiableEdgeSkelet {
    override def isMulti: Boolean = true

    def values: List[NonMultiEdgeSkelet[I, O]]
  }

  trait NonMultiEdgeSkelet[+I, +O] extends EdgeSkelet[I, O, _, _] with MultiableEdgeSkelet {
    override def isMulti: Boolean = false
  }

  // direct
  trait DirectableEdgeSkelet {
    def isDirect: Boolean
  }

  trait DirectEdgeSkelet[+I, +O] extends EdgeSkelet[I, O, _, _] with DirectableEdgeSkelet {
    override def isDirect: Boolean = true
  }

  trait NonDirectEdgeSkelet[+I, +O] extends EdgeSkelet[I, O, _, _] with DirectableEdgeSkelet {
    override def isDirect: Boolean = false
  }


}