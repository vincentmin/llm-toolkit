import scala.compiletime.ops.string
@main def run: Unit =
  val prompt = PromptTemplate("Hello $name, welcome to $project")
  println(
    prompt.run(
      Map("name" -> "John", "project" -> "Scala3")
    )("prompt")
  )
  println("hello world!")
  val llm = FakeLLM()
  println(
    llm.run(
      prompt.run(Map("name" -> "Sarah", "project" -> "Scala3"))
    )
  )

  val seq: Chain = Chain(prompt, llm)

  println(seq.run(Map("name" -> "Bob", "project" -> "Scala3")))

  val multiseq = seq >> PromptTemplate("The output was $response") >> FakeLLM()

  println(multiseq.run(Map("name" -> "Bob", "project" -> "Scala3")))

  val betterseq = PromptTemplate("Hello $name, welcome to $project") >>
    FakeLLM() >>
    PromptTemplate("The output was $response") >>
    FakeLLM()

  println(betterseq.run(Map("name" -> "Charlie", "project" -> "Scala3")))

type Input = Map[String, String]
type Output = Map[String, String]

trait Runnable {
  def run(input: Input): Output
  def >>[T <: Runnable](runnable: T): Chain =
    Chain(this, runnable)
}

case class Chain(first: Runnable, second: Runnable) extends Runnable {
  def run(input: Input): Output =
    second.run(first.run(input))
}

case class PromptTemplate(template: String) extends Runnable {

  val parameters: Seq[String] =
    """\$\w+""".r.findAllIn(template).toSeq.map(_.drop(1))

  def run(input: Input): Output = Map(
    "prompt" -> parameters.foldLeft(template) { (acc, param) =>
      acc.replace("$" + param, input(param))
    }
  )

}

trait LLM extends Runnable {
  def run(input: Input): Output
}

case class OpenAI(model: String) extends LLM {
  def run(input: Input): Output = ???
}

case class FakeLLM() extends LLM {
  def run(input: Input): Output = Map(
    "response" -> s"The answer to \"${input("prompt")}\" is 42"
  )
}
