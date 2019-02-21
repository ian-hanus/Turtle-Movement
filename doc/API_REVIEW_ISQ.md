Irene Qiao, Sachal Dhillon
isq, ssd27
# SLogo API Review
## Part 1
What about your API/design is intended to be flexible?
* Very few outlets for communication between front and backend - can do whatever on either side as long as the API is maintained
* Inheritance hierarchies
How is your API/design encapsulating your implementation decisions?
* get methods - ex. the front end receives location of turtle only but the calculation of location is completely performed in back-end
* return specified inforamation to front end regardless of implementation in back end
What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
* syntax error, null pointers for variables/routines that are not defined, runtime exceptions
* handle by propogating back to UI as alert to user but handle in backend if possible
Why do you think your API/design is good (also define what your measure of good is)?
* define good as ability to impact large parts of code without few lines without causing many errors
* makes very few assumptions, assumptions made for consistency between parts
Both people should complete Part 1 before continuing on to Part 2.

## Part 2
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
* use of factories - after parsing, allows abstraction/polymorphism
* allows easy introduction of new developers to project because of consistency in format
What feature/design problem are you most excited to work on?
* The parser - will be challenging, especially because need to handle user input errors without crashing
* Wide array of possible errors need to be considered, should be handled at high level
What feature/design problem are you most worried about working on?
* see above section
Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
* user accesses a predefined variable and uses it in an expression
* an error occurs during parsing and needs to be handled
* user wants to see list of predetermined variables
* inputs list of mathematical operations
* user defines a command and calls it
