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
              val needToVisitNext = neighbours(nowVisit).filterNot(visited.contains).toList ++ needToVisit
              dfs(needToVisitNext, visitedNext)
            }
          }
      }
    }

    dfs(List(start), Nil)
  }

  def dfs(start: V, end: V): Option[List[V]] = dfs(start, v => v == end, _ => None)

  def traversalDfs(start: V): List[V] = dfs(start, _ => false, x => if (x.isEmpty) None else Some(x)).getOrElse(Nil)

  private def dfsRec(start: V, finishCondition: V => Boolean, transformIfVisit: List[V] => Option[List[V]], isFirstResult: Boolean): Option[List[V]] = {

    def dfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (visited.contains(nowVisit)) {
        transformIfVisit(visited)
      } else {
        val visitedZero = nowVisit :: visited
        if (finishCondition(nowVisit)) {
          Some(visitedZero)
        } else {
          neighbours(nowVisit).foldLeft(Option.empty[List[V]]) {
            case (result, neighbour) => result match {
              case None => dfsRec(neighbour, visitedZero)
              case Some(visitedNow) => if (isFirstResult) Some(visitedNow) else dfsRec(neighbour, visitedNow)
            }
          }
        }
      }

    }

    dfsRec(start, Nil).map(_.reverse)
  }

  def dfsRec(start: V, end: V): Option[List[V]] = dfsRec(start, v => v == end, _ => None, isFirstResult = true)

  def traversalDfsRec(start: V): List[V] = dfsRec(start, _ => false, x => if (x.isEmpty) None else Some(x), isFirstResult = false).getOrElse(Nil)


  private def bfsRec(start: V, finishCondition: V => Boolean, transformIfVisit: List[V] => Option[List[V]], isFirstResult: Boolean): Option[List[V]] = {

    def bfsRec(nowVisit: V, visited: List[V]): Option[List[V]] = {
      if (visited.contains(nowVisit)) {
        transformIfVisit(visited)
      } else {
        val visitedNext = nowVisit :: visited
        if (finishCondition(nowVisit)) {
          Some(visitedNext)
        } else {
          val listVisited = neighbours(nowVisit).map(neighbour => bfsRec(neighbour, visitedNext))
          if (isFirstResult) {
            listVisited.find(_.isDefined).flatten
          } else {
            val result = listVisited.collect { case Some(x) => x }.reduce(_ ++ _) // todo refactor clever merge _ ++ _
            if (result.isEmpty) None else Some(result)
          }
        }
      }

    }

    bfsRec(start, Nil).map(_.reverse)
  }

  def bfsRec(start: V, end: V): Option[List[V]] = bfsRec(start, v => v == end, _ => None, isFirstResult = true)

  def traversalBfsRec(start: V): List[V] = bfsRec(start, _ => false, x => if (x.isEmpty) None else Some(x), isFirstResult = false).getOrElse(Nil)

}

object Graph {
  def apply[V](edgeMap: Map[V, Set[V]]): Graph[V] = new DirectedGraph[V](edgeMap)

  def apply[V](): Graph[V] = new DirectedGraph[V](Map.empty)

}
