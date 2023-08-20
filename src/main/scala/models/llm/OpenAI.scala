package models.llm
import base.{Runnable, Input, Output}

final case class OpenAI(model: String) extends LLM {
  def run(input: Input): Output = ???
}
