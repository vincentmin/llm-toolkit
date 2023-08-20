package models.llm
import base.{Runnable, Input, Output}

final case class Repeater() extends LLM {
  def run(input: Input): Output = Map(
    "response" -> s"The answer to \"${input("prompt")}\" is 42"
  )
}
