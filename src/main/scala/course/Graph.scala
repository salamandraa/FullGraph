package course

import scala.annotation.tailrec

trait Graph[V] {

  def vertices: Set[V]

  def edges: Set[(V, V)]

  def addEdge(a: V, b: V): Graph[V]

  def neighbours(a: V): Set[V]


  def dfs(start: V, end: V): Option[List[V]] = {
    @tailrec
    def dfs(needToVisit: List[V], visited: List[V]): Option[List[V]] = {
      needToVisit match {
        case Nil => None
        case nowVisit :: tailToVisit =>
          if (visited.contains(nowVisit)) {
            dfs(tailToVisit, visited)
          } else {
            val visitedNext = nowVisit :: visited
            if (nowVisit == end) {
              Some(visitedNext.reverse)
            }
            else {
              val needToVisitNext = neighbours(nowVisit).toList ++ needToVisit
              dfs(needToVisitNext, visitedNext)
            }
          }
      }
    }

    dfs(List(start), Nil)
  }


  def dfsRec(start: V, end: V): Option[List[V]] = {

    def dfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (nowVisit == end) {
        Some((nowVisit :: visited).reverse)
      } else {

        if (visited.contains(nowVisit)) {
          None
        } else {
          val visitedNext = nowVisit :: visited
          neighbours(nowVisit).foldLeft(Option.empty[List[V]]) {
            case (result, neighbour) => result match {
              case None => dfsRec(neighbour, visitedNext)
              case some => some
            }
          }
        }
      }

    }

    dfsRec(start, Nil)
  }

  def bfs(start: V, end: V): Option[List[V]] = {

    def bfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (nowVisit == end) {
        Some((nowVisit :: visited).reverse)
      } else {

        if (visited.contains(nowVisit)) {
          None
        } else {
          val visitedNext = nowVisit :: visited
          neighbours(nowVisit).map(neighbour => bfsRec(neighbour, visitedNext)).find(_.isDefined).flatten
        }
      }

    }

    bfsRec(start, Nil)
  }

}

object Graph {
  def apply[V](edgeMap: Map[V, Set[V]]): Graph[V] = new DirectedGraph[V](edgeMap)

  def apply[V](): Graph[V] = new DirectedGraph[V](Map.empty)

}
