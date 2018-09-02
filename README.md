# parks-and-rec
SWENG 894 Team 7 - Recreational Department website 2.0

Trello board invite link - https://trello.com/invite/b/NpOEo9Gg/f0fc49a24b581bd7e74dc06695e5ab54/parks-and-rec


How to run from commandline?
============================
Run the Jetty configuration from command line
###############################################
Go to project root folder and execute "mvn jetty:run"

to debug from command line
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
mvn jetty:run

How to run from Intellij?
++++++++++++++++++++++++++++
EditConfiguration--->choose maven --> jetty:run

How to test?
=============
Import the provided "capstone.postman_collection.json" in to POSTMAN. (Free tool to test web services)
