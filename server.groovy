import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import java.awt.Color

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

def start() {
  showStarting()
  showStarted()
}

def stop() {
  showStopping()
  showStopped()
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
