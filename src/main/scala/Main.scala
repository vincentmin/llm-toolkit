import scala.compiletime.ops.string
import models.llm.Repeater
import models.PromptTemplate
import base.Chain

@main def run: Unit =
  val prompt = PromptTemplate("Hello $name, welcome to $project")
  println(
    prompt.run(
      Map("name" -> "John", "project" -> "Scala3")
    )("prompt")
  )
  println("hello world!")
  val llm = Repeater()
  println(
    llm.run(
      prompt.run(Map("name" -> "Sarah", "project" -> "Scala3"))
    )
  )

  val seq: Chain = Chain(prompt, llm)

  println(seq.run(Map("name" -> "Bob", "project" -> "Scala3")))

  val multiseq = seq >> PromptTemplate("The output was $response") >> Repeater()

  println(multiseq.run(Map("name" -> "Bob", "project" -> "Scala3")))

  val betterseq = PromptTemplate("Hello $name, welcome to $project") >>
    Repeater() >>
    PromptTemplate("The output was $response") >>
    Repeater()

  println(betterseq.run(Map("name" -> "Charlie", "project" -> "Scala3")))
