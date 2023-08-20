package models.llm
import base.{StringInput, Output}

final case class Randomizer(n: Int = 100) extends LLM {
  def run(input: StringInput): Output = Map(
    "response" -> scala.util.Random.nextInt(n).toString
  )
}
