package my.core.edges

import my.core.vertex.Vertex


trait EdgeSkelet[+I, +E, +O]

object EdgeSkelet {

  // multi
  trait MultiableEdgeSkelet {
    def isMulti: Boolean
  }

  trait MultiEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with MultiableEdgeSkelet {
    override def isMulti: Boolean = true

    def edges: Seq[NonMultiEdgeSkelet[I, E, O]]

    def inVertices[R >: I]: Set[Vertex[R]] = edges.map(x => Vertex(x.inVertex)).toSet

    def outVertices[R >: O]: Set[Vertex[R]] = edges.map(x => Vertex(x.outVertex)).toSet
  }

  trait NonMultiEdgeSkelet[+I, +E, +O] extends EdgeSkelet[I, E, O] with MultiableEdgeSkelet {
    override def isMulti: Boolean = false

    def inVertex: I

    def outVertex: O
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