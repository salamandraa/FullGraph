import my.core.edges.EdgedImpl.DirectEdge
import my.core.graph.Graph

object Sherlock extends App {

  case class Process(value: String, isFull: Boolean)

  case class Data(value: String)

  val general = Data("general")
  val specific = Data("specific")

  val edges = Seq(DirectEdge(general, specific, Process("deduction", isFull = true)),
    DirectEdge(specific, general, Process("induction", isFull = false))
  )

  val graph = Graph(edges)

  println(graph)
  println(graph.vertexes)

}
