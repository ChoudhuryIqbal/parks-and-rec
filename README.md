# parks-and-rec
SWENG 894 Team 7 - Recreational Department website 2.0

Trello board invite link - https://trello.com/invite/b/NpOEo9Gg/f0fc49a24b581bd7e74dc06695e5ab54/parks-and-rec

Postgres
-=======
Configuration defined in application.properties

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

curl -X GET   'http://localhost:8070/parksrec/services/v1/getUser?userName=TestUser'  -H 'Cache-Control: no-cache'

curl -X POST \
  http://localhost:8070/parksrec/services/v1/addUser \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
    -d '{
    "id": null,
    "username": "TestUser",
    "password": null,
     "roles": [
        {
            "rolename": "Admin",
            "description": "Admin role"
        },
        {
            "rolename": "User",
            "description": "user role"
        }
    ]
}'