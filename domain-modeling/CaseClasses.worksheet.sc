// To start, we will model the concept of a rectangle

// CASE CLASSES
// define IMMUTABLE data type
// that allows to aggregate several concepts together.

case class Rectangle(width: Double, height: Double)
// the above definition introduces BOTH
// - a TYPE that has 2 MEMBERS (width & height)
// - a CONSTRUCTOR that has 2 PARAMETERS (width & height)


// we can now construct a value of that type
val window = Rectangle(2, 2)
// and access the case class fields / members via dot notation
val window_area = window.height * window.width

// but since computing the area of a rectangle is a common operation
// we can add it ass a method of our Rectangle class

case class RectangleWithArea(width: Double, height: Double):
    val area = width * height
    // the above is a form of syntactic sugar,
    // equivalent to
    // def area = this.width * this.height

RectangleWithArea(2, 3).area


case class Square(side: Double):
    val area = this.side * this.side

Square(3).area


case class Circle(radius: Double):
    val area = 3.14 * radius * radius

Circle(radius=1).area


// NOTA BENE
// case classes are IMMUTABLE
// example ``` window.height = 3 ``
// raises a "Reassignment error"
// ==> to "update", we create a copy with the desired changes
val newWindow = window.copy(width = 0.5)