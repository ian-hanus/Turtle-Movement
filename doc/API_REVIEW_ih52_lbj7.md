# SLogo API Review
Louis Jensen (lbj7), Ian Hanus (ih52)

## Part 1
1. What about your API/design is intended to be flexible?
    * Ian: We have a command history that will hold past commands and allow us to implement newer features that involve these past commands. We also have the option of adding new boxes, as we are going to have a basic external box display in case any new features need to be shown.

    * Louis: We're also planning to save the past command history so that the user can call past commands.

2. How is your API/design encapsulating your implementation decisions?

    * Ian: Our design is encapsulating our implementation decisions because it breaks down each section of the problem into different views. For example, the main canvas is a separate view from the terminal, and we can extend other views as needed. 

    * Louis: We plan to create a class for each individual item that will appear on the screen and use one GUI class to add them all to the screen.

3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

    * Ian: Exceptions that we will be accounting for have to do with file choice for the turtle image and invalid command exceptions. These will be returned by our popup error display to the user.

    * Louis: Make sure that a command the user enters that returns an error is not added to the list of past commands, so they can not click on it to use again. 

4. Why do you think your API/design is good (also define what your measure of good is)?

    * Ian: Our definition of a good design would be implementing the basic features in a way that is not too difficult while leaving flexibility for possible extension. The API will be good if the commands are intuitive and not open to any interpretation at all. We will do this by having multiple views and trying to have the turtle movement well linked with the drawing in the canvas. We will do this by passing certain TurtleStates and drawing between these TurtleState.getLocation objects before moving the turtle.

    * Louis: We believe a good design is a design that allows for easy communication between front end and back end and is flexible enough to add new features without significantly changing the design.


## Part 2
1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
    * We will have design patterns in that our turtle backend will act as a factory, creating a list of turtlestates to pass to the frontend that will affect the movement of the turtle and the drawing of the line.


2. What feature/design problem are you most excited to work on?
    * I have not worked with CSS much so it will be exciting to learn how to improve the formatting of the GUI with this new tool.
    
    * I'm excited to work on the display of the turtle because I'm interested in how the back end will connect to the front end and display the moving turtle with the lines. 


3. What feature/design problem are you most worried about working on?
    * I am most worried about how to draw the lines on the turtle canvas in a way that will make extension easier. Right now we pass a series of turtlestates to draw, but I am worried that further challenges may have the turtle move in arcs that are difficult to represent in TurtleStates and may force us to pass segements of movement in each TurtleState.

    * I'm worried about keeping track of the command history and how to implement it. We plan to allow the user to reuse past commands and to do that we will have store the past X commands. 
4. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * User selects help option
        * Louis: Pop up help menu will appear to user with lists of possible commands and their documentation.
        * Ian: call "help command" and return the documentation for the given command. Else return "Command not found"
    * Change the display of the turtle
        * Have a file dropdown that does error checking to see if a file is a valid image. Scale the image to an appropriate size for the turtle. 
    * Draw a Circle
        * Have the turtle move through frames and draw a line between the turle position and the last position at each frame. The shorter the frames are the smoother the line will be.
    * Retrieve past commands from the terminal
        * Hold the past commands in a list to pull off link an integer variable to the arrow key. For every arrow up or down, increment variable appropriately and call get(variable) to return the string of the command. 
    * Display variables that represent expressions
        * Add variables to a list of variables. Display the variables in a "variable explorer" menu. Update the variable explorer when a new variable is created.