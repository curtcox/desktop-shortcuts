echo "terminal"
# Open a new terminal.
# Note that this script is MacOS dependent.
# You probably want to specify a different initial directory.
osascript -e 'tell app "Terminal" to do script "cd \"'`pwd`'\""'
