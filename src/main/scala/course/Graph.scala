package course

import scala.annotation.tailrec

trait Graph[V] {

  def vertices: Set[V]

  def edges: Set[(V, V)]

  def addEdge(a: V, b: V): Graph[V]

  def neighbours(a: V): Set[V]

  private def dfs(start: V, finishCondition: V => Boolean, transformAllVisited: List[V] => Option[List[V]]): Option[List[V]] = {
    @tailrec
    def dfs(needToVisit: List[V], visited: List[V]): Option[List[V]] = {
      needToVisit match {
        case Nil => transformAllVisited(visited.reverse)
        case nowVisit :: tailToVisit =>
          if (visited.contains(nowVisit)) {
            dfs(tailToVisit, visited)
          } else {
            val visitedNext = nowVisit :: visited
            if (finishCondition(nowVisit)) {
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

  def dfs(start: V, end: V): Option[List[V]] = dfs(start, v => v == end, _ => None)

  def traversalDfs(start: V): Option[List[V]] = dfs(start, _ => false, x => if (x.isEmpty) None else Some(x))

  private def dfsRec(start: V, finishCondition: V => Boolean, transformAllVisited: List[V] => Option[List[V]], merge: (V, List[V]) => Option[List[V]]): Option[List[V]] = {

    def dfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (visited.contains(nowVisit)) {
        transformAllVisited(visited)
      } else if (finishCondition(nowVisit)) {
        Some(nowVisit :: visited)
      } else {
        val visitedZero = nowVisit :: visited
        neighbours(nowVisit).foldLeft(Option.empty[List[V]]) {
          case (result, neighbour) => result match {
            case None => dfsRec(neighbour, visitedZero)
            case Some(visitedNow) => merge(neighbour, visitedNow)
          }
        }
      }

    }

    dfsRec(start, Nil).map(_.reverse)
  }

  def dfsRec(start: V, end: V): Option[List[V]] = {

    def dfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (visited.contains(nowVisit)) {
        None
      } else if (nowVisit == end) {
        Some(nowVisit :: visited)
      } else {
        val visitedZero = nowVisit :: visited
        neighbours(nowVisit).foldLeft(Option.empty[List[V]]) {
          case (result, neighbour) => result match {
            case None => dfsRec(neighbour, visitedZero)
            case some => some
          }
        }
      }

    }

    dfsRec(start, Nil).map(_.reverse)
  }

  def traversalDfsRec(start: V): Option[List[V]] = {
    def traversalDfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (visited.contains(nowVisit)) {
        Some(visited)
      } else {
        val neighboursNow = neighbours(nowVisit)
        val visitedZero = nowVisit :: visited
        neighboursNow.foldLeft(Option.empty[List[V]]) {
          case (result, neighbour) => result match {
            case None => traversalDfsRec(neighbour, visitedZero)
            case Some(visitedNow) => traversalDfsRec(neighbour, visitedNow)
          }
        }
      }
    }

    traversalDfsRec(start, Nil).map(_.reverse)
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
