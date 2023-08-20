package base
import base.Runnable

case class Chain(first: Runnable, second: Runnable) extends Runnable {

  override def invoke(input: Input): Output =
    second.invoke(first.invoke(input))
  def run(input: StringInput): Output = invoke(input)
}
