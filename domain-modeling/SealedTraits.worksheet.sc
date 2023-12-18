// SEALED TRAITS
// are types that can accepts
// ONLY ONE of several alternative values

// EXAMPLE
sealed trait Shape
// CAN EITHER BE
case class Rectangle(width: Double, height: Double) extends Shape
// OR
case class Circle(radius: Double) extends Shape
// BUT IT CANNOT BE BOTH AT THE SAME TIME...

// NOTA BENE
// traits are ABSTRACT TYPES
// i.e they do NOT introduces a constructor (as opposed to a case class)
// and consequently, the only way to create a value of that type
// is to use the constructor of one of the classes that extend that type
// example
// we cannot do this
// val someShape = Shape()
// rather, we must instead do this
val window: Shape = Rectangle(2, 2)

// since a trait has no attribute / field / member
// to be able to actually do something with a value of type Shape
// we must "recover"  its concrete type using PATTERN MATCHING
// example

def computeArea(shape: Shape): Double =
    // PATTERN MATCHING
    shape match
    // MATCH EXPRESSION as above
    // allow to define ALTERNATIVE branchs of computation
    // according to the CONCRETE class of a sealed trait
        case Rectangle(width, height) => width * height
        case Circle(radius)           => 3.14 * radius * radius
    // NOTA
    // a 'nice' feature is that
    // the compiler checks for the exhaustivity of the cases.
    // this is made possible by the fact that a sealed trait
    // have a fixed set of possible values


computeArea(window)
computeArea(Circle(3))

val comment = window match
                case Rectangle(width, height) => "This is indeed a window"
                // here we use the wildcard pattern `_` to match "everything else"
                case _                        => "This is some other shape"
comment

// LET'S MODEL THE ACTIONS THAT A USER CAN PERFORM ON A MESSAGING SYSTEM
// - subscribe to a channel
// - unsubscribe to a channel
// - send a message

case class Channel(name: String)

sealed trait UserAction
case class Subscribe(channel: Channel) extends  UserAction
case class UnSubscribe(channel: Channel) extends  UserAction
case class SendMessage(channel: Channel, message: String) extends  UserAction

Subscribe(Channel("effective-scala"))