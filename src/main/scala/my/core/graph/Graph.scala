package my.core.graph

import my.core.edges.Edge
import my.core.vertex.Vertex

case class Graph[+V, +E](edges: Seq[Edge[V, E, V]]) {

  lazy val vertexes: Seq[Vertex[V]] = Edge.inferVertices(edges)
}


object Graph {

}
