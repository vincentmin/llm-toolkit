package models.llm
import base.{StringInput, Output}

final case class OpenAI(model: String) extends LLM {
  def run(input: StringInput): Output = ???
}
