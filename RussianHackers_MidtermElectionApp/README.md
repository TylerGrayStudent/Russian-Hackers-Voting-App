README

Russian Hackers Midterm Election App

v1.0

How to compile:

From your favorite IDE, import the class files.
Make sure to include the folders under the lib folder in the class build path.
Run main in Main class.

JAR file will also be included.

DOES REQUIRE AN ACTIVE INTERNET CONNECTION!!!
DOES REQUIRE AN ACTIVE INTERNET CONNECTION!!!

Working with the App:

The main screen is the user log in screen. This allows a user to log in to vote,
allows a user to check and see if they are registered, and allows admins to log in.

Check Voter Status:
Click on Check Registration Status.
Type in User Name.
System responds with either you are registered or are not.

Register:
Click on Register.
Enter data for all fields.
The program will show error windows and highlight issues.
All fields must be filled.
User name must be a new one not already registered.
Passwords must match.
SSN and DL must be 9 digits.
Data is stored securely.

Admin:
Admin shows a log in for an admin screen.
Username: root
Password: admin
Press log in to display the settings.

On the left:

Election Creator:
The top text will show the name of the current election, if one exists.
To create one, type in a name, and click create election.
Once an election exists, a user can end it using the end election. This also allows the user to end a published election as well.
Once the user clicked edit election theyll see a screen to edit the offices.
On the left allows users to enter a new office by name. The current offices is listed in the drop down to the right side. A user can delete offices by selecting
offices and hitting delete. A user can edit an office the same way just using the edit office button.
When a user hits edit office, a similar UI is shown, just for the creation and deletion of candidates running for the office.

NOTE: The import and export buttons are currently not working and for a later update.

Election Results:
With an active election, an admin can display results.
The office is shown on the left, and the current front runner is on the right with their vote count.
Recount button will dump the vote tally from the DB and recount using the BallotBox in use while running the program.

User Management:
On left side, enter the username you wish to edit.
User info is displayed and can be edited. All the same rules apply as when the user registered.

Settings:
Log out returns to the log in screen.
