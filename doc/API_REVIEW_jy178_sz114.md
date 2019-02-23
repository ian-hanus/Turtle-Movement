# API Review

## Part 1

#### Michael
1) 
    * MVC
    * Able to add new commands and models
    * Limited interaction between frontend and backend
        * Frontend only looks at model
2) 
    * Model owns all of the info
        * Model is either updated or accessed
3) 
    * Illegal commands
4) 
    * Flexible, easy to add to 

#### Jonathan
One
- expression class (add new subclasses to make new commands) 
- override evaluate when making a new subclass
Two
- `Expression` contains logic to process commands
- `Parser` will make the `Expression` objects and `Expression` objects does not see the parsed strings
Three
- Illegal commands
- Bad syntax
- Wrong types for inputs
Four
- Good `Expression` hierarchy
- Enables different combinations of commands
- Execution is separate from reading
- Front-end is separate

### Part 2

#### Michael
1) dasf
    * MVC pattern
    * Ease of creating new commands 
    * Strategy design pattern
2) asdf
    * Working on parsing, making commands
3) sadf
    * Loops, user-defined commands

#### Jonathan
One
- MVC (different views subject to a main view)
- Backend has controller that keeps track of logic/instructions
Two
- Making `Expression` hierarchy and getting it to work that is portable. So we can make new commands easily
Three
- User defined commands
- Consistent error catching
- Potential for needing more complex turtle animations

### Use cases
One
```fd sum 10 sum 10 sum 10 sum 20 20```
Two
```repeat 4 [
  fd 100
  rt 90
  ]
```
Three
```
to triangle [ ]
[ 
  repeat 3 [
    fd 50 
    rt 120
  ]
]

to circle [ ]
[
  repeat 360 [
    fd 1 
    rt 1
  ]
]

to square [ ]
[
  repeat 4 [
    fd 100
    rt 90
  ]
  fd 100
]
```
Four
```
to star [ ] 
[ 
  repeat 5 
  [ 
    fd 50
    rt 144
  ]
]

star
```

Five
```
fd sum 1 random 100
rt random 360
fd product random 10 sum 1 random 10
lt random 360
```