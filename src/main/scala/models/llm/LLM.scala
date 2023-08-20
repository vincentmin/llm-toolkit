package models.llm
import base.{Runnable, StringInput, Output}

trait LLM extends Runnable {
  def run(input: StringInput): Output
}
