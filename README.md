Quiz Leaderboard

A simple Java Swing application that acts as a Quiz Leaderboard. It allows users to submit a player name and a score. The application automatically saves all scores to a leaderboard.txt file and displays all submitted scores in a sorted, high-to-low list.

This project is built using basic Java Swing components [cite: FirstSwingExample.java, SwingApp.java], java.io for file handling [cite: ReadWriteDemo1.java], and an ArrayList for in-memory storage [cite: Module 2 (2).pptx].

Features

Submit new player scores (name and score).

Displays a top-down leaderboard, automatically sorted from highest to lowest score.

Persistently saves all scores to a leaderboard.txt file.

Automatically loads all previous scores from the file when the app starts.

GUI auto-refreshes after every new score is submitted.

Includes basic error handling for non-numeric score inputs [cite: Module 3 (2).pptx].

File Structure

LeaderboardApp.java: The main class that builds the Java Swing GUI (JFrame, JButton, etc.) and handles button clicks [cite: LeaderboardApp.java, FirstSwingExample.java].

LeaderboardManager.java: Handles all the logic, including adding scores, saving to the file, loading from the file, and sorting the leaderboard [cite: LeaderboardManager.java].

PlayerScore.java: A simple class to store a player's name and score [cite: PlayerScore.java].

leaderboard.txt: The plain text file used to save and load the scores (this file is created automatically).

How to Run

You must have the Java Development Kit (JDK) installed on your computer to run this.

Save the Files Save all three .java files (LeaderboardApp.java, LeaderboardManager.java, PlayerScore.java) into the same folder.

Open a Terminal Open your Command Prompt (on Windows) or Terminal (on Mac/Linux).

Navigate to the Folder Use the cd command to go to the directory where you saved the files.

Example:
cd C:\Users\YourName\Desktop\LeaderboardProject

Compile the Code Run the Java compiler (javac) to compile all three files. You must run this command from inside the folder.

javac LeaderboardApp.java LeaderboardManager.java PlayerScore.java

Run the Program After compiling, run the main application using the java command.

java LeaderboardApp

The GUI window should now open. A leaderboard.txt file will be created in the same folder as soon as you submit your first score.
