package models.llm
import base.{StringInput, Output}

final case class Repeater(outputKey: String = "response") extends LLM {
  def run(input: StringInput): Output = Map(
    outputKey -> s"The answer to \"${input("prompt")}\" is 42"
  )
}
