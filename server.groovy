import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import java.awt.*

def toggle() {
  if (stopped()) {
    start()
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

def runScript(script,success,failure) {
  Thread.start {
     try {
       println script.execute().text
       queue(success)
     } catch (e) {
       println e.message
       queue(failure)
     }
  }
}

def start() {
  showStarting()
  runScript("./go.sh",{showStarted()},{showStopped()})
}

def stop() {
  showStopping()
  runScript("./stop.sh",{showStopped()},{showStarted()})
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

new SwingBuilder().edt {
  frame(title: '', size: [100, 100], show: true, defaultCloseOperation: WC.EXIT_ON_CLOSE) {
    borderLayout()
    status = label(text: '', constraints: BL.SOUTH)
    button = button(text:'', actionPerformed: {toggle()}, constraints:BL.CENTER)
    showStopped()
  }
}
