package core.components

trait TimeProcess[T] {
  def isForward: Option[Boolean]
}

object TimeProcess {

  case object InvariantProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = None
  }

  case object ForwardProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = Some(true)
  }

  case object RevertProcess extends TimeProcess[Any] {
    override def isForward: Option[Boolean] = Some(false)
  }

}
