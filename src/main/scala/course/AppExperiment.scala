package course

object AppExperiment extends App {

  val graph = Graph[String]()
    .addEdge("London", "Lisbon")
    .addEdge("Lisbon", "Madrid")
    .addEdge("Madrid", "London")
    .addEdge("Madrid", "Rome")
    .addEdge("Paris", "Rome")
    .addEdge("Rome", "London")

  println(s"vertices = ${graph.vertices}")

  println("first")
  println(graph.dfs("Rome", "Paris"))
  println(graph.dfsRec("Rome", "Paris"))
  println(graph.bfsRec("Rome", "Paris"))
  println(graph.traversalDfs("Rome"))
  println(graph.traversalDfsRec("Rome"))
  println(graph.traversalBfsRec("Rome"))

  println()

  println("second")
  println(graph.dfs("London", "Rome"))
  println(graph.dfsRec("London", "Rome"))
  println(graph.bfsRec("London", "Rome"))
  println(graph.traversalDfs("London"))
  println(graph.traversalDfsRec("London"))
  println(graph.traversalBfsRec("London"))


}
