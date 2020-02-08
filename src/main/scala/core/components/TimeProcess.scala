package core.components

trait TimeProcess[+T] {
  def isForward: Option[Boolean]
}

object TimeProcess {

  trait InvariantTimeProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = None
  }

  trait ForwardTimeProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = Some(true)
  }

  trait RevertTimeProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = Some(false)
  }

}
