package sc.cukeamermaid.plugin

import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.ui.ConsoleView
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.{ToolWindowAnchor, ToolWindowManager}

trait ConsolePrinter {
  var console: ConsoleView = null

  def grabConsole(project: Project, title: String): ConsoleView = {
    val windowManager = ToolWindowManager.getInstance(project)

    Option(windowManager.getToolWindow(title)) match {
      case Some(tw) =>
        tw.show(null)
        console
      case None =>
        console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole
        val tw = windowManager.registerToolWindow(title, console.getComponent, ToolWindowAnchor.BOTTOM)
        tw.show(null)
        console
    }


  }
}
