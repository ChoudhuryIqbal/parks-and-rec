# parks-and-rec
SWENG 894 Team 7 - Recreational Department website 2.0

Trello board invite link - https://trello.com/invite/b/NpOEo9Gg/f0fc49a24b581bd7e74dc06695e5ab54/parks-and-rec

Postgres
=======
Configuration defined in application.properties


# Fresh Start
* Open a terminal and go to the folder where you keep your projects.
(Optional) If the parks-and-rec folder exists, delete it.
* Clone the GitHub repository
> git clone https://github.com/oxz3/parks-and-rec.git


# How to run from command line?
* After cloning, change directory into the "parks-and-rec" folder.
* Run the Maven command with the clean and install parameters.
> mvn clean install
* Somewhere near the end of the console output you should see "BUILD SUCCESS"
* After a successful build, execute the .jar created in the target folder.
> java -jar target/parksrec.jar  -Dserver.port=$PORT $JAR_OPTS
* This will spin up tomcat and allow you to test the API endpoints.
* When finished, Ctrl-c in the console will shutdown Tomcat.


# Setting up IntelliJ and running from IntelliJ
* Open IntelliJ to the "parks-and-rec" folder of the working or recently cloned GitHub repo.
* If the JDK for the project isn't configured, Select File -> Project Structure.
Make sure the SDK is Oracle JDK 1.8.
* If the Maven dependencies aren't setup (you see lots of red underlines everywhere), 
Locate the pom.xml file in the root folder and right-click, there should be a menu item near that bottom that says something to the effect of "Add as a Maven Project".
* _Additional Note:_  You can run the same mvn and java commands inside of the Terminal within IntelliJ.

# How to Test
Test cases are in docs/RESTAPIS text file.  https://github.com/oxz3/parks-and-rec/blob/master/docs/RESTAPIS
Don't forget to update this file!
The API endpoints are numbered.
Each number is a single curl command 
Copy and paste the entire curl command into a terminal.

