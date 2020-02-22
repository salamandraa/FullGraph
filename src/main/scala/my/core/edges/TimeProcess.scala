package my.core.edges

trait TimeProcess {
  def isForward: Option[Boolean]
}

object TimeProcess {

  trait InvariantTimeProcess extends TimeProcess {
    override def isForward: Option[Boolean] = None
  }

  trait NonInvariantTimeProcess[+V] extends TimeProcess {
    def getTime: V
  }

  trait ForwardTimeProcess[+V] extends NonInvariantTimeProcess[Unit] {
    override def isForward: Option[Boolean] = Some(true)

    override def getTime: Unit = ()
  }

  trait RevertTimeProcess[+V] extends NonInvariantTimeProcess[Unit] {
    override def isForward: Option[Boolean] = Some(false)

  }

}
