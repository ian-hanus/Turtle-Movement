Irene Qiao isq
Sachal Dhillon ssd27
# Part 1
## What about your API/design is intended to be flexible?

Her design is flexible is because it uses an extensive amount of inheritance to encapsulate behaviour. The front-end is only able to see a very small public API. To that end, any behaviour that is implemented is acceptable so long as it fits the precondition of fulfilling that API.

## How is your API/design encapsulating your implementation decisions?

Her design abstracts the majority of behaviour behind a turtle class that only the back-end is able to interact with at a functional level with the front-end being limited to state-getters. This ensures that the display is blind to the implementation of the back-end

## What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Errors may arise if the number of arguements to any given expression does not match or if the command is not defined. These are generally described as syntax errors whereas other runtime errors are handled seperately on the backend. If the program cannot continue to operate, errors are propogated to the front-end to handle error display.

## Why do you think your API/design is good (also define what your measure of good is)?

'Good' is defined by the ability to impact a large portion of the program with only a few lines of code. To that end, her code makes sure to make as few assumptions as possible about the structure of the program. This allows for significant dynamism in terms of adding new behaviour. Constraining the size of the endpoints too ensures that developers are forced to keep their code consistent.

# Part 2
## How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

Factories are used to create new expressions. The open-closed principle is used to make sure that encapsulation is thoroughly followed. Polymorphism could help more in order to control duplicate behavior.

## What feature/design problem are you most excited to work on?

She is most excited to work on the parser, she thinks that it will provide an interesting opportunity to learn new design patters related to validating parsed code and then ensuring that functionality of the code is correct.

## What feature/design problem are you most worried about working on?

She is most worried about handling the several types of errors that could pop up in the program. Because there is such a wide array of possible user-errors, a lot of work will need to go into designing around and accomodating them.

## Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

* A user tries to define a new variable and use it in a new expression
* An error occurs during parsing an expression entered by a user
* The user requests the list of pre-defined variables
* The current visibilty of the turtle needs to be accessed
* The user needs to perform some mathematical operations between several types of input
