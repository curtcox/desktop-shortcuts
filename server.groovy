import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import java.awt.*
import java.awt.event.*

def toggle() {
  if (stopped()) {
    go()
  } else {
    stop()
  }
}

def stopped() {
  status.text == 'stopped'
}

def queue(action) {
  EventQueue.invokeLater({action()})
}

def run(command,success,failure) {
  Thread.start {
     try {
       println scriptFor(command).execute().text
       queue(success)
     } catch (e) {
       println e.message
       queue(failure)
     }
  }
}

def scriptExistsFor(key) {
   new File(scriptFor(key)).exists()
}

def scriptFor(name) {
   "./scripts/${name}.sh"
}

def blind(script) {
  run(script,{},{})
}

def go() {
  showStarting()
  run('go',{showStarted()},{showStopped()})
}

def stop() {
  showStopping()
  run('stop',{showStopped()},{showStarted()})
}

def showStarted() {
  status.text = 'running'
  button.text = 'Go'
  button.background = Color.RED
  button.enabled = true
}

def showStopped() {
  status.text = 'stopped'
  button.text = 'Stop'
  button.background = Color.GREEN
  button.enabled = true
}

def showStarting() {
  status.text = 'starting'
  button.enabled = false
}

def showStopping() {
  status.text = 'stopping'
  button.enabled = false
}

def keypress(keyChar) {
  if (scriptExistsFor(keyChar)) {
     blind(keyChar)
  } else {
    println "No binding for $keyChar"
  }
}

def installHotkeys() {
  KeyboardFocusManager.currentKeyboardFocusManager
      .addKeyEventDispatcher( [
      dispatchKeyEvent: { e ->
        if (e.id == KeyEvent.KEY_TYPED) {
          keypress(e.keyChar)
        }
        return false
      } ] as KeyEventDispatcher );
}

new SwingBuilder().edt {
  frame(title: '', size: [100, 100], show: true, defaultCloseOperation: WC.EXIT_ON_CLOSE) {
    borderLayout()
    status = label(text: '', constraints: BL.SOUTH)
    button = button(text:'', actionPerformed: {toggle()}, constraints:BL.CENTER)
    showStopped()
  }
  installHotkeys()
}
