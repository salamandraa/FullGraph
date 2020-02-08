package core.components


trait EdgeSkelet[+I, +E, +O]

object EdgeSkelet {

  // types
  trait EdgeSkeletType

  trait EdgeSkelet1Type[+T, +E] extends EdgeSkelet[T, E, T] with EdgeSkeletType

  trait EdgeSkelet2Type[+I, +E, +O] extends EdgeSkelet[I, E, O] with EdgeSkeletType

  // multi
  trait MultiableEdgeSkelet {
    def isMulti: Boolean
  }

  trait MultiEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with MultiableEdgeSkelet {
    override def isMulti: Boolean = true

    def edges: Seq[NonMultiEdgeSkelet[I, E, O]]
  }

  trait NonMultiEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with MultiableEdgeSkelet {
    override def isMulti: Boolean = false
  }

  // direct
  trait DirectableEdgeSkelet {
    def isDirect: Boolean
  }

  trait DirectEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with DirectableEdgeSkelet {
    override def isDirect: Boolean = true
  }

  trait NonDirectEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with DirectableEdgeSkelet {
    override def isDirect: Boolean = false
  }


}