package base

case class Chain(first: Runnable, second: Runnable) extends Runnable {
  def run(input: Input): Output =
    second.run(first.run(input))
}
