package sc.cukeamermaid.plugin

import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.actionSystem.{LangDataKeys, AnAction, AnActionEvent, PlatformDataKeys}
import com.intellij.openapi.fileEditor.FileEditorManager

class Gherkin2Mermaid extends AnAction with BddTransformer with ConsolePrinter {
    def actionPerformed(event: AnActionEvent): Unit = {
      val project = event.getProject
      val text = FileEditorManager.getInstance(project).getSelectedTextEditor.getDocument.getText

      val body = transformBdd(text, Gherkin2MermaidTransformation)

      val console = grabConsole(project, "Mermaid output")

      console.clear()
      console.print(body, ConsoleViewContentType.NORMAL_OUTPUT)
    }
}
