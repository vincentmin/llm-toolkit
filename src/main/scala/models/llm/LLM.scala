package models.llm
import base.{StringInput, Output}
import base.Runnable

trait LLM extends Runnable {
  def run(input: StringInput): Output
}
