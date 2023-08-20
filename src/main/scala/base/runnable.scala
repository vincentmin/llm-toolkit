package base

trait Runnable {
  def run(input: Input): Output
  def >>[T <: Runnable](runnable: T): Chain =
    Chain(this, runnable)
}
