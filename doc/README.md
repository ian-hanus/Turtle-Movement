SLogo Team 01 README

* Names of all people who worked on the project
    * Sachal Dillon, Ian Hanus, Rishi Tripathy, Jonathan Yu 
* Date you started, date you finished, and an estimate of the number of hours worked on the project
    * Date Started: February 17th
    * Date Ended: March 9th
    * Hours Worked: 100+
* Each person's role in developing the project
    * Sachal: Backend, expressions
    * Ian: Frontend, all non-canvas and canvas configuration features
    * Rishi: Frontend, canvas, display of turtle information, help menu
    * Jonathan: Backend, parser
* Any books, papers, online, or human resources that you used in developing the project
    * N/A
* Files used to start the project (the class(es) containing main)
    * SLogoMain.java
* Files used to test the project and errors you expect your program to handle without crashing
    * Test files: used class examples
    * Errors: file not found, expression not found
* Any data or resource files required by the project 
* (including format of non-standard files)
    * JavaFX jar files
* Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
    * Users enter their commands/programs into the terminal and press Run to execute them
    * The Configuration window has multiple options to select images, colors, and languages (using mouse clicks).
* Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
    * Tells cannot be called as arguments to other Tells
* Any known bugs, crashes, or problems with the project's functionality
    * The very limited functionality of the project is entirely due to the poor implementation of the parser (Jonathan). The frontend and the Expression inheritance hiearchy were well-designed and ready to be linked to the parser. But, the parser had significant bugs and lacked much functionality.
    * On the canvas, when the turtle wraps around, it only sometimes successfully renders as having looped around. Other times, it appears as if it goes offscreen
    * Some information about turtles are not accessible to frontend components that are configured to display them 
* Any extra features included in the project
    * Serializable objects holding relevant information for any part of the simulation, not just the specified features.
* Your impressions of the assignment to help improve it in the future
    * Some of the instructions regarding modularities of composed functions were unclear
