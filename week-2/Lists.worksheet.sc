// we will motivate the use of lists
// with a simple example: an Adress Boook

case class AddressBook(
    // Collection types are PARAMETRIZED by the type of their elements
    contacts: List[Contact]
    )

case class Contact(
    name: String,
    email: String,
    phoneNumbers: List[String]
)

val jane = Contact(name="Jane Doe", email="jd@gmail.com",
// NB: an empty List() is valid for  List[String]
// but... being more explicit is better
phoneNumbers = List.empty[String]
)
val john = Contact(name="John Doe", email="jd@gmail.com", phoneNumbers = List("+10003", "+10004"))

val myAddressBook = AddressBook(List(jane, john))

myAddressBook.contacts.size
myAddressBook.contacts.contains(john)
myAddressBook.contacts.contains("john")
myAddressBook.contacts.map(
    // map to our contact list // a function literal
    // that takes a contact and return its name
    contact => contact.name
    )


// NOTA
// Scala's lists are either:
// an EMPTY LIST: Nil
// or a linked list
// represented as pair:   head :: tail
// the head is an element
// the tail is the rest of the list

List(jane, john) == jane :: john :: Nil


// Note that the INFIX CONS operator (is this proper scala linguo ???)
// is RIGHT ASSOCIATIVE (it builds the list from RIGHT TO LEFT)
jane :: john :: Nil == (jane :: (john :: Nil))
// the DOT NOTATION IS LEFT ASSOCIATIVE
jane :: john :: Nil == Nil.::(john).::(jane)


val ielle = Contact(name="Ielle", email="ielle@gmail.com", phoneNumbers = Nil)
val new_contacts = ielle :: myAddressBook.contacts


// PATTERN MATCHING ON LISTS
new_contacts match
    case Nil             => println("No Contacts")
    case contact :: tail => println(contact.name)

new_contacts match
    case first :: second :: Nil => println(second.name)
    case _ => println("More than 2 contacts")

// ACCESSING ELEMENTS IN LISTS
val fruits: List[String] = List("Apple", "Pear", "Grapes", "Raspberries")
fruits.head
fruits.tail
fruits.last

fruits.tail.head
fruits.tail.tail
fruits(0)
fruits(3)