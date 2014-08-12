package sc.cukeamermaid.plugin

import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, PlatformDataKeys}

class Mermaid2Gherkin extends AnAction with BddTransformer with ConsolePrinter {
    def actionPerformed(event: AnActionEvent): Unit = {
      val text = event.getData(PlatformDataKeys.FILE_TEXT)
      val body = transformBdd(text, Mermaid2GherkinTransformation)

      val project = event.getProject

      val console = grabConsole(project, "Gherkin output")

      console.clear()
      console.print(body, ConsoleViewContentType.NORMAL_OUTPUT)
    }
}
