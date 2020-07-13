package reactivemongo.core.nodeset

final class NodeSetInfo private[reactivemongo] (
  val name: Option[String],
  val version: Option[Long],
  val nodes: Vector[NodeInfo],
  val primary: Option[NodeInfo],
  val mongos: Option[NodeInfo],
  val secondaries: Vector[NodeInfo],
  val nearest: Option[NodeInfo],
  val awaitingRequests: Option[Int],
  val maxAwaitingRequestsPerChannel: Option[Int]) {

  private[reactivemongo] def withAwaitingRequests(count: Int, maxPerChannel: Int): NodeSetInfo = new NodeSetInfo(name, version, nodes, primary, mongos, secondaries, nearest, Some(count), Some(maxPerChannel))

  override def equals(that: Any): Boolean = that match {
    case other: NodeSetInfo => this.tupled == other.tupled
    case _                  => false
  }

  override def hashCode: Int = tupled.hashCode

  private lazy val tupled = Tuple9(name, version, nodes, primary, mongos, secondaries, nearest, awaitingRequests, maxAwaitingRequestsPerChannel)

  override lazy val toString = s"{{NodeSet $name ${nodes.mkString(" | ")} }}"
}
