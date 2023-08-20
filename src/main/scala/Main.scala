import scala.compiletime.ops.string
import models.llm.Repeater
import models.PromptTemplate
import base.Chain

@main def run: Unit =

  // setup
  val prompt = PromptTemplate("Hello $name, welcome to $project")

  val llm = Repeater()

  // run template
  println(prompt.run(Map("name" -> "John", "project" -> "Scala3")))

  // run llm
  println(llm.run(prompt.run(Map("name" -> "Sarah", "project" -> "Scala3"))))

  // run chain
  val chain: Chain = Chain(prompt, llm)

  println(chain.run(Map("name" -> "Bob", "project" -> "Scala3")))

  // run longer chain
  val intermediatePrompt = PromptTemplate("The output was $response")
  val multiseq = chain >> intermediatePrompt >> llm

  println(multiseq.run(Map("name" -> "Bob", "project" -> "Scala3")))

  // run single chain
  val singleChain = prompt >> llm >> intermediatePrompt >> llm

  println(singleChain.run(Map("name" -> "Charlie", "project" -> "Scala3")))
