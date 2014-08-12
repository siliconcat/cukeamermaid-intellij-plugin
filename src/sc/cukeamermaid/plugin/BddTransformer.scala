package sc.cukeamermaid.plugin

import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.PostMethod

trait Transformation
object Mermaid2GherkinTransformation extends Transformation
object Gherkin2MermaidTransformation extends Transformation

trait BddTransformer {

  def transformBdd(text: String, transformation: Transformation): String = {
    val endpoint = transformation match {
      case Mermaid2GherkinTransformation => "cuke-the-mermaid"
      case Gherkin2MermaidTransformation => "splash-the-cuke"
    }

    val post = new PostMethod(s"http://cuke-the-mermaid.herokuapp.com/$endpoint")
    post.setRequestBody(text)

    val client = new HttpClient

    client.executeMethod(post)

    val body = new String(post.getResponseBody)

    post.releaseConnection()
    body
  }

}
