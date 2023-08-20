package base

import scala.compiletime.ops.string

trait Runnable {
  def run(input: StringInput): Output

  private def toStringInput(input: Input): StringInput =
    val stringInputs = input.collect(_ match
      case (key, value: String) => (key, value)
    )
    val evaluatedRunnables = input.values.collect(_ match
      case runnable: Runnable => runnable.run(stringInputs)
    )
    evaluatedRunnables.foldLeft(stringInputs) { (acc, output) =>
      acc ++ output
    }

  def invoke(input: Input): Output =
    run(toStringInput(input))

  def >>[T <: Runnable](runnable: T): Chain =
    Chain(this, runnable)
}
