# desktop-shortcuts
This is designed to be a starting point rather than immediately useful.

It is intended to support the frequent manual starting and stopping of something.
In my case, it is the frequent starting and stopping of development web servers.

# Intended Usage
1. Customize the stop and go scripts.
Your new versions should exit once the server is started or stopped,
instead of running the entire time the server is up or down.
The stop/go button is disabled while the script is executing.
1. Test changes in these scripts by running the script.
1. Run the start script backgrounded when you are satisfied with the changes.
1. To start or stop the server, tab to the app and press return.

That's it. That's all it does.

# Requirements
There is a supplied shell script that launches the app using Nibus as the look and feel.
It needs an appropriate groovy available on your path.
You might need

# Rationale
The app is distributed as source so it can be easily modified and customized.
Unfortunately, Groovy is pretty slow to start, so this this app is, too.
It seems like the right tradeoff given the intended usage pattern.
