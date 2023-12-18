// Amongts the many data structures in COLLECTIONS
// We will explore
// Lists (immutable sequences)
// ArrayBuffers (mutable sequences)
// Map (immutable Dictionnary)

// immutable collections are available by default
// mutable ones must be manually imported from
// scala.collection.mutable

// IMPORT in SCALA vs Python

// Python
// from scala.collection import mutable
// in Scala
import scala.collection.mutable

// EMPTY COLLECTIONS
List.empty[Int]
mutable.ArrayBuffer.empty[Double]
Map.empty[String, Boolean]

// NOTA
// Lists are LINEAR sequences
// Arrays are INDEXED sequences
// ACCESSING ELEMENTS ARE THEREFORE
// List(n) => O(n)
// Array(n) => O(1)

// Those constructors are VARARGS (variadic ???)
// they accept an arbitrary number of arguments
List(1, 2, 3)
mutable.ArrayBuffer("first", "second", "third", "...", "nth")
Map(
    "name" ->  "John", // nota  this key value pair is a TUPLE
    "surname" -> "Doe"
    )

"a" -> "tuple"
// TUPLES are somewhat the 'opposite' of LISTS
// LISTS == > VARIABLE LENGTH  & HOMOGENEOUS
// TUPLES ==> FIXED LENGTH & HETEROGENEOUS
"1" -> 2
// NOTA
1 -> 2 == (1, 2)
// BUT
// 1 -> 2 -> 3 is NOT equivalent to  (1, 2, 3)
// 1 -> 2 -> 3  is actually ((1, 2), 3)

// PATTERN MATCHING ON TUPLES
(42, "What is the meaning of Life is?") match
    case (answer, question) => s"${question} The answer is: ${answer}"
// equivalent to
42 -> "What is the meaning of Life is?" match
    case answer -> question => s"${question} The answer is: ${answer}"
// NOTA
val (x, y) = (10, 20)
x
y
val pair = ("first", "second")
pair(0)
pair(1)


// PREPENDING is done with +:
1 +: List(2, 3)
// APPENDING is done with :+
mutable.ArrayBuffer(1, 2) :+ 3

// MAPS having no order updates are simply done with +
Map("true" -> true) + ("false" -> false)
// NOTA
// NOT   Map("true" -> true) + "false" -> false      PARENS are needed !!!



// QUERYING COLLECTIONS
val nums = List(1, 2, 3, 4, 5, 6)
nums.size
nums.length
nums.empty
nums.nonEmpty
nums.contains(2)
val infos = Map("name" -> "John", "surname" -> "Doe")
// nota .contains on Maps queries the maps Keys not their values
infos.contains("name")
infos.contains("John")

// .filter() returns ALL the elements that match the predicate
nums.filter(x => x > 2)
// .find() returns the 1st element that matches a predicate
nums.find(x => x % 2 == 0)
// Note find retursn an OPTION, a special kind of collection that hols 0 or 1 element
// Some() represents the case when there is a value
// the None case represents when there are no value
nums.find(x => x == 1000)

// other querying methods
nums.filterNot(x => x > 10)

val isEven =
    (x: Int) => x % 2 == 0

nums.findLast(x => isEven(x))

// ANY
List(1, 1, 1, 2, 1, 1).exists(x => isEven(x))
// ALL
List(0, 2, 4, 6).forall(x => isEven(x))


// TRANSFORMING COLLECTIONS

// MAP
val inc =
    (x: Int) => x + 1

nums.map(inc)

nums.map(isEven)

val aMap = Map("first"-> 1,
               "second" -> 2
               ).map(
                 (key, value) => key -> (value * 2)
                    )

// FLATMAP
// use cases:
// changing the number of element in the output
nums.map(x => List())
nums.flatMap(x => List())
nums.flatMap(x => List(x, x))

case class Contact(name: String, phoneNumbers: List[String])

val jane = Contact("Jane", List("+10001", "+1002"))
val john = Contact("John", List("+10003", "+1004"))

val contacts: List[Contact] = List(jane, john)

// This is not what we want: it return a list of list of phone numbers
val allPhoneNumbers =
    contacts.map(
        contact => contact.phoneNumbers
        )

// to get a list of phone numbers
val allPhoneNumbersV2 =
    contacts.flatMap(
        contact => contact.phoneNumbers
        )

// FOLDLEFT (reduce) - nota it has multiple PARAMETER LISTS
// Allows to transform a collection into anything
// SUM
nums.foldLeft(0)((acc, x) => acc + x)
// PRODUCT
nums.foldLeft(1)((acc, x) => acc * x)
// REVERSING THE LIST
nums.foldLeft(List.empty[Int])(
    (acc, x) => x :: acc)

// True if LAST is EVEN (or list is empty)
List().foldLeft(true)((acc, x) => isEven(x))
List(2).foldLeft(true)((acc, x) => isEven(x))
List(1, 2).foldLeft(true)((acc, x) => isEven(x))
List(1, 2, 3).foldLeft(true)((acc, x) => isEven(x))

// It is called foldLEFT because
// it processes the list in the reverse order to which it is constructed
// reminder
List(1, 2, 3)
// actually "means"
1 :: 2 :: 3 :: Nil
// which means it is contracture from RIGHT => LEFT
// first
Nil
// Then
3 :: Nil
// Then
2 :: (3 :: Nil)
// Then
1 :: (2 :: (3 :: Nil))
// foldLeft does the opposite
// Let's say we have the function
val add = (x: Int, y: Int) => x + y
List(1, 2, 3).foldLeft(0)(add)
// is actually
add(add(add(0, 1), 2), 3)

// GROUPBY
// groups the elements of a collection
// according to a partition function
nums.groupBy(isEven)

// Let's extract domain names from emails
val emails = List("m@gmail.com", "m@yahoo.com", "l@gmail.com")

val domain: String => String =
    email =>
        email.dropWhile(c => c!= '@')
             .drop(1)

domain("m@gmail.com")

emails.groupBy(domain)


// SEQUENCE METHODS
// SORTING
val users = List(("Alice", 42), ("John", 30))
users.sortBy((name, age) => age)
users.sortBy((name, _) => name)

// MAPS METHODS
// most important
Map("key" -> "some value").get("key")
Map("key" -> "some value").get("missing-key")


// OPTION
// a collection that has AT MOST 1 element: None | Some()

case class ContactV2(
    name: String,
    // convention: if a field is an Option,
    // it is prefixed with maybe
    maybeEmail: Option[String],
    phoneNumbers: List[String]
)

val mary = ContactV2(
    "Mary",
    Some("mary@gmail.com"), // since email is an Option we wrap the value in Some()
    ("+1003" :: Nil)
    )

val bob = ContactV2(
    "Bob",
    None, // since email is an Option, the absence of email is represented with None
    List("+1001", "+1002"))

def usesGmail(contact: ContactV2): Boolean =
    contact.maybeEmail match
        case None           => false
        case Some(email)    => email.endsWith("@gmail.com")

usesGmail(mary)

val hasGmail: ContactV2 => Boolean =
    contact =>
        contact.maybeEmail match
            case None           => false
            case Some(email)    => email.endsWith("@gmail.com")

hasGmail(mary)


bob.maybeEmail.map(email => email.size)
bob.maybeEmail.map(email => email.size).getOrElse(0)

mary.maybeEmail.zip(List("something"))

// headOption does not raise an Exception
// if the the sequence is empty !!!
List(1).headOption
List().headOption



// CONCATENATION with ++
List(1, 2) ++ List(3)
mutable.ArrayBuffer(1, 2) ++ mutable.ArrayBuffer(3)
List(1, 2) ++ mutable.ArrayBuffer(3)
Map("Mary" -> 42) ++ Map("John" -> 30)


// MUTABLE CONCAT ++=
val first = mutable.ArrayBuffer(1, 2)
val second = mutable.ArrayBuffer(3)
first ++= second
first

// MUTABLE APPEND += / PREPEND +=:
val array = mutable.ArrayBuffer(1, 2)
array += 3
0 +=: array
// REMOVE the first element equal to the arg
array -= 1
// ADD ALL
array ++= List(3, 4, 5)
array
// REMOVE all
array --= List(2, 4, 5)
array