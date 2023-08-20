package models.llm
import base.{StringInput, Output}

final case class Repeater() extends LLM {
  def run(input: StringInput): Output = Map(
    "response" -> s"The answer to \"${input("prompt")}\" is 42"
  )
}
