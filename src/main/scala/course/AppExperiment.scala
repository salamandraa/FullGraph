package course

object AppExperiment extends App {

  val graph = Graph[String]()
    .addEdge("London", "Lisbon")
    .addEdge("Lisbon", "Madrid")
    .addEdge("Madrid", "London")
    .addEdge("Madrid", "Rome")
    .addEdge("Paris", "Rome")
    .addEdge("Rome", "London")

  println(graph.vertices)

  println(graph.dfs("Rome", "Paris"))
  println(graph.dfsRec("Rome", "Paris"))
  println(graph.dfs("London", "Rome"))
  println(graph.dfsRec("London", "Rome"))
}
