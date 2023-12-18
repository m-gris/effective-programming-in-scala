// FUNCTIONS vs. METHODS
// In Scala
// FUNCTIONS are introduced with `val`
// and
// METHODS are introduced with `def`
// Motivation: For scala to be functional,
// its functions must be 1st class citizens,
// i.e VALUES that can be passed around (input / output...)

val increment: Int => Int = //  val funcName: ParamType => OutputType =
    // below is a function literal
    x => x + 1

increment(1)

val add = // here we did not declared the type of the function
    // the compiler will infer it
    // however the parameters of our lambda must be typed !!!
    (x: Int, y: Int) => x + y

add(2, 3)

// NOTA
add(2, 3) // is syntactic sugar
// for
add.apply(2, 3)

// THE GOOD NEWS is that the compiler can convert methods to functions when needed :)
def inc(x: Int): Int = x + 1
// the compile will convert our method inc to a function
List(1, 2,3).map(inc)