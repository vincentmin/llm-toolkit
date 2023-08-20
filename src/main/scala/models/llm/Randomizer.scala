package models.llm
import base.{StringInput, Output}

final case class Randomizer(n: Int = 100, outputKey: String = "response")
    extends LLM {
  def run(input: StringInput): Output = Map(
    outputKey -> scala.util.Random.nextInt(n).toString
  )
}
