package models
import base.{Runnable, Input, Output}

final case class PromptTemplate(template: String) extends Runnable {

  val parameters: Seq[String] =
    """\$\w+""".r.findAllIn(template).toSeq.map(_.drop(1))

  def run(input: Input): Output = Map(
    "prompt" -> parameters.foldLeft(template) { (acc, param) =>
      acc.replace("$" + param, input(param))
    }
  )

}
