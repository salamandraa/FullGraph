package core.graph

import core.edges.Edge
import core.vertex.Vertex

case class Graph[+V, +E](edges: Seq[Edge[V, E, V]]) {

  lazy val vertexes: Seq[Vertex[V]] = Edge.inferVertices(edges)
}


object Graph {

}
