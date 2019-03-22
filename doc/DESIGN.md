# SLogo Team 1 Design

## High Level Design Goals

### Frontend
Major design goals on the front end included making it simple to add and track new features, such as new boxes that displayed information about the simulation, and allowing us to control a large amount of configuration based off of frontend controls such as colors and the actual images of the turtle. By having the turtlestates on the backend only affect the visualization of the canvas, we separated the information making sure the information was only passed to the objects that needed that information. Having the results object pass separated lists for turtlestates and maps for the command history, user defined commands, and variables made it easy to display pertinent information without risk of interfering with any of the calculation that the backend had made, creating a clear seperation between the two parts with the parse acting as an interface between them.

### Backend
Major design goals on the backend include creating an Expression inheritance hierarchy that handles the evaluation of commands in a tree-like pattern. Expression objects would be created by the parser which would read in the appropriate properties files, translate commands, and read the command strings (from the frontend) to appropriately create the corresponding Expression objects with their arguments. The backend would then give the frontend access to a Result object that held the return value and resultant Turtle states upon execution of commands.

## Adding New Features

### Frontend
Adding new boxes to display new features is made simple by using the rightbox class. By creating a new rightbox with the indices corresponding to the desired row and column of the display, a new vbox of the correct format and label will be placed to the side of the canvas. new save features is easy as well.The canvas is essentially a display on which any new components or features can be added by leveraging the modularity of normal JavaFX. This vbox can then be filled with whatever the new feature is. By adding whatever is supposed to be saved to either the preferences object (containing the visible layout preferences such as color and language) you can create a new preferences object that is serialized and saved with the preference. The same can be done with the library object, which contains information pertinent to the backend such as variables and user commands.

### Backend
New commands can be added by simply creating a new class that extends the Expression base class and zero or more of several marker interfaces (such as VariableArgumentTaker). So long as the execute() method is defined (as required by the inheritance hierarchy), there are no other constraints on what sort of activity occurs inside of the expression. A notable but not-strictly enforced design pattern is that Expressions typically do not take in doubles or integers as arguments but rather other Expressions. This is in order to ensure consistency inside of control structures since the model at large relies on nested expression trees. 

For the most part, the parser should be able to recognize most new commands, provided their behavior is marginally similar to the built-in commands. If not, some additional logic should be added to the parser, but, overall, the parser should be quite extensible.


## Major Design Choices
### Frontend 
The major high level design on the frontend was the updating of the canvas based on TurtleStates that were passed from the backend. Basically, by creating a parser object and getting the Results object relating to the new user input each time, the list of turtlestates would allow us to update how the turtle moved on the canvas. The turtlestate would contain information such as the ID of the turtle moving, whether its pen was up or down, the orientation and placement of the turtle, along with other possible configuration identities. The canvas essentially takes the delta of what was run since the last executed command, which is presented to it through its API by the backend, and applies those delta to the canvas, resulting in the movement of turtles, appearance of lines, etc.
Another major decision we made was in the formatting of the actual display of the simulation. By adding side boxes of the same type and style, we made it easier to add new boxes to hold more information. For example, in the basic implementation we only had 3 boxes, but to extend it we simply widened the view and added 3 more boxes.

### Backend
The design of the backend was predicated upon the idea that every expression (barring the special cases of variables and constants) could be thought of as a nested Expression tree. Evaluating an expression was as simple as evaluating its children and processing the returned values. This ultimately allowed us to utilize a backwards propogation approach using stacks to parse string inputs and pass each Expression to a parent Expression as demanded by the number of arguments the parent took. The tradeoff here, however, was that the design became inflexible to commands such as Tell that fundamentally did not work under the assumption that child commands would always need to be evaluated before parents. 

## Assumptions
1. The pen color would be the same at all times for all turtles
2. There were two separate palettes: both a shape and a color could have the same index
3. An image that could be loaded in as the turtle had to be of the form *.jpg of *.png
4. All programs that were to be loaded in had to be of the form *.slogo
5. That programs could be loaded into the terminal to be examined first instead of being run immediately
6. The turtle will tend to stay within the bounds of the window (this is an assumption that prevents some of the bugs associated with the frontend/incomplete features.)