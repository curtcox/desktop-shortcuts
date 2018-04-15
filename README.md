# desktop-shortcuts
A simple desktop app for starting stopping and otherwise manipulating something.

In my case, it is the frequent starting and stopping of development web servers.
This is designed to be a starting point rather than immediately useful.

# Intended Usage
1. **Customize** the stop and go scripts.
Your new versions should exit once the server is started or stopped,
instead of running the entire time the server is up or down.
The stop/go button is disabled while the script is executing.
1. **Hotkey Scripts** can be customized by editing the scripts directory.
Any time a key is pressed, the matching script will be executed, if one exists.
1. **Test Changes** in these scripts by running the script.
1. **Run Backgrounded** when you are satisfied with the changes.
1. **Start or Stop** the server, by tabbing to the app and pressing return.

That's it. That's all it does.

# Requirements
1. **Groovy** There is a supplied shell script that launches the app using Nimbus as the look and feel.
It needs an appropriate groovy available on your path.
You will need to provide an alternate launch mechanism (like explicitly pointing to groovy) otherwise.
1. **Mac** This app uses a Mac-specific API to change the dock icon.
Delete or modify the references to com.apple.eawt.Application if you want to run on a different platform.
You may also want to change the script file extension if you anren't running on a unix variant.

# Rationale
The app is distributed as source so it can be easily modified and customized.
Unfortunately, Groovy is pretty slow to start, so this this app is, too.
It seems like the right tradeoff given the intended usage pattern.
