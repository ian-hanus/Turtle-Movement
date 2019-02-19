# SLogo Architecture Design
1. When does parsing need to take place and what does it need to start properly? 
    
    Parsing must occur between commands recieved by the front end and commands executed by the backend. To start properly, a command needs a beginning and and end.

2. What is the result of parsing and who receives it?
    
    The result of parsing a command is a string. It should be received by a unit that parses the string into a 'command' abstraction that is executable by the backend.


3. When are errors detected and how are they reported?
    
    Errors can be detected when an invalid command is input into the command line.
    Errors should be handled by the backend within the command parser and errors reported back to the user by the frontend.


4. What do commands know, when do they know it, and how do they get it?

    Commands should know the number of and type of their parameters, and their return output. They should recieve this information as a result of their state (binary command, ternary command, etc)

5. How is the GUI updated after a command has completed execution?
    
    Split GUI into multiple displays, independent windows representing the turtle moving component, terminal component, and possibly others including a configuration portion.
    

# Create APIs
## External APIs: 
### backend:
    ProgramParser
    LineParser
    TurtleState
### Frontend:
     History
    

## Internal APIs:
### backend:
     Subroutine 
     Variable
     Expression
     Turtle

### Front End:
     View
     Display 
     Terminal
     Config
     Drawing
     Variable Display
     Error Display

# Use Cases:
## FD 50
     Move turtle forward 50 in backend
     Pass new turtle state to frontend
     Set turtle image to 50 forward
     Check if pd to draw line between old x and x + 50
     
## 50 FD
     Try catch to say "invalid command" in error window
     
## PU FD 50 PD FD 50
     Set pen equal to false
     Pass new turtleState forward 50
     Set pen equal to true
     Pass new turtleState forward 50

     