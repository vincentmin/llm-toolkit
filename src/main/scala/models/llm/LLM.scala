package models.llm
import base.{Runnable, Input, Output}

trait LLM extends Runnable {
  def run(input: Input): Output
}
