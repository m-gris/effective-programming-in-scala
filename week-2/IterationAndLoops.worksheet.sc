import javax.swing.text.Position
// We will explore (some) ways of performing loops

// - Iteration on Collections
// - Imperative Loop with the While control structure
// - Functional Loop using recursion

val prod = (x: Int, y: Int) => x * y

val factorial: Int => Int =
        (n: Int) => (1 to n).foldLeft(1)(prod)

factorial(5)
// equivalent to
(1 to 5).product


// RANGE EXAMPLES
// TO includes the end
(1 to 5)
// is equivalent to
1.to(5)
// UNTIL excludes the end
(1 until 5)
// STEPS are speicified with BY
(0 to 10 by 2)
(5 until 0 by -1 )


def factorialImperative(n: Int) =
    var acc = 1
    var i = 1
    while i < n do
        i += 1
        acc *= i
    acc

factorialImperative(5)

def factorialRecurisve(n: Int): Int =
    if n < 2 then 1
    else n * factorialRecurisve(n -1)

factorialRecurisve(5)