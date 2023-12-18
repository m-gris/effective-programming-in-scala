// ENUMARATIONS
// are a convenient construct
// for modeling a type that has
// one of several possible SINGLE VALUE
// i.e, not classes of values (multiple)

enum PrimaryColor:
    case Red, Green, Blue

PrimaryColor.Red
PrimaryColor.Green
PrimaryColor.Blue

// PATTERN MATCHING on enums
// is done with LITERAL MATCHING

def isNotColorBliendFriendly(color: PrimaryColor): Boolean =
    color match
        case PrimaryColor.Red   => true
        case PrimaryColor.Green => true
        case PrimaryColor.Blue  => false


// TO ACCESS ALL THE POSSIBLE VALUES OF AN ENUM
// simply use
PrimaryColor.values

// NOTA BENE
// ENUMS are syntactic sugar on top of traits & case classes
// example
sealed trait PrimaryColorV2 // DEFINES A TYPE with a fixed number of possible values
object PrimaryColorV2 // the COMPANION OBJECT of the type
    // whose purpose is to DEFINE THE VALUES of type PrimaryColorV2
    case object Red extends PrimaryColorV2
    case object Green extends PrimaryColorV2
    case object Blue extends PrimaryColorV2
// THEREFORE
// WE CAN SAY THAT
// AN ENUMERATION DEFINES
// A TYPE
// & ITS COMPANION OBJECT
//          which in turns defines the possible values taken by that type
