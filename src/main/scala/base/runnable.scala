package base

trait Runnable {
  def run(input: StringInput): Output

  private def toStringInput(input: Input): StringInput =
    val stringInputs = input.collect { case (k, v: String) => (k, v) }
    val evaluatedRunnables = input.values.collect { case runnable: Runnable =>
      runnable.run(stringInputs)
    }
    evaluatedRunnables.foldLeft(stringInputs) { (acc, output) =>
      if acc.keys.exists(output.contains(_)) then
        println(s"Warning: key already exists in input: $output")
      acc ++ output
    }

  def invoke(input: Input): Output =
    run(toStringInput(input))

  def >>[T <: Runnable](runnable: T): Chain =
    Chain(this, runnable)
}
