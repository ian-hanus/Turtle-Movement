package Model.Expressions.Interfaces;


/**
 * Marker interface indicating that the implementing class alters or queries a Turtle's state in some way. This helps inform
 * any given parser (while remaining agnostic to specific parser design) that mutable Turtle States will need to be passed in
 * in some form (as defined by the implementing class's constructor)
 */

public interface TurtleExpression {
}
