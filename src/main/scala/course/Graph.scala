package course

import scala.annotation.tailrec

trait Graph[V] {

  def vertices: Set[V]

  def edges: Set[(V, V)]

  def addEdge(a: V, b: V): Graph[V]

  def neighbours(a: V): Set[V]

  private def toOptList[T](list: List[T]): Option[List[T]] = if (list.isEmpty) None else Some(list)

  private def dfs(start: V, finishCondition: V => Boolean, transformIfNotFound: List[V] => List[V]): List[V] = {
    @tailrec
    def dfs(needToVisit: List[V], visited: List[V]): List[V] = {
      needToVisit match {
        case Nil => transformIfNotFound(visited)
        case nowVisit :: tailToVisit =>
          if (visited.contains(nowVisit)) {
            dfs(tailToVisit, visited)
          } else {
            val visitedNext = nowVisit :: visited
            if (finishCondition(nowVisit)) {
              visitedNext
            }
            else {
              val needToVisitNext = neighbours(nowVisit).filterNot(visited.contains).toList ++ needToVisit
              dfs(needToVisitNext, visitedNext)
            }
          }
      }
    }

    dfs(List(start), Nil).reverse
  }

  def dfs(start: V, end: V): Option[List[V]] = toOptList(dfs(start, v => v == end, _ => Nil))

  def traversalDfs(start: V): List[V] = dfs(start, _ => false, list => list)

  private def dfsRec(start: V, finishCondition: V => Boolean, transformIfVisit: List[V] => List[V], isFirstResult: Boolean): List[V] = {

    def dfsRec(start: V, visited: List[V]): List[V] = {
      if (visited.contains(start)) {
        transformIfVisit(visited)
      } else {
        val visitedZero = start :: visited
        if (finishCondition(start)) {
          visitedZero
        } else {
          neighbours(start).foldLeft(List.empty[V]) {
            case (result, neighbour) => result match {
              case Nil => dfsRec(neighbour, visitedZero)
              case visitedNow => if (isFirstResult) visitedNow else dfsRec(neighbour, visitedNow)
            }
          }
        }
      }
    }

    dfsRec(start, Nil).reverse
  }

  def dfsRec(start: V, end: V): Option[List[V]] = toOptList(dfsRec(start, v => v == end, _ => Nil, isFirstResult = true))

  def traversalDfsRec(start: V): List[V] = dfsRec(start, _ => false, list => list, isFirstResult = false)


  private def bfsRec(start: V, finishCondition: V => Boolean, transformIfVisit: List[V] => List[V], isFirstResult: Boolean): List[V] = {

    def bfsRec(nowVisit: V, visited: List[V]): List[V] = {
      if (visited.contains(nowVisit)) {
        transformIfVisit(visited)
      } else {
        val visitedNext = nowVisit :: visited
        if (finishCondition(nowVisit)) {
          visitedNext
        } else {
          val listVisited = neighbours(nowVisit).map(neighbour => bfsRec(neighbour, visitedNext))
          if (isFirstResult) {
            listVisited.find(_.nonEmpty).getOrElse(Nil)
          } else {
            listVisited.reduce(_ ++ _) // todo refactor clever merge _ ++ _
          }
        }
      }

    }

    bfsRec(start, Nil).reverse
  }

  def bfsRec(start: V, end: V): Option[List[V]] = toOptList(bfsRec(start, v => v == end, _ => Nil, isFirstResult = true))

  def traversalBfsRec(start: V): List[V] = bfsRec(start, _ => false, list => list, isFirstResult = false)

}

object Graph {
  def apply[V](edgeMap: Map[V, Set[V]]): Graph[V] = new DirectedGraph[V](edgeMap)

  def apply[V](): Graph[V] = new DirectedGraph[V](Map.empty)

}
