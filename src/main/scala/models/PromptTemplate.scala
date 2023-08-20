package models
import base.{StringInput, Output}
import base.Runnable

final case class PromptTemplate(template: String, outputKey: String = "prompt")
    extends Runnable {

  val parameters: Seq[String] =
    """\$\w+""".r.findAllIn(template).toSeq.map(_.drop(1))

  def run(input: StringInput): Output = Map(
    outputKey -> parameters.foldLeft(template) { (acc, param) =>
      acc.replace("$" + param, input(param))
    }
  )

}
