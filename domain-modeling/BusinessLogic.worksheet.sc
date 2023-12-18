case class BitRate(mb_per_seconds: Double)

enum InternetConnection:
    case Mobile, Fixed

case class Duration(seconds: Int)

case class VideoStream(duration: Duration,
                       bitRate: BitRate,
                       connection: InternetConnection)

val lowQualityVideo = BitRate(mb_per_seconds = 0.3)
val highQualityVideo = BitRate(mb_per_seconds = 0.6)

val coffeeBreakStream =
    VideoStream(duration=Duration(120),
            bitRate=highQualityVideo,
            connection = InternetConnection.Mobile)

val dataCenterEnergyPerMB = 0.000072
val kgOfCO2PerKWH = 0.5

def networkEnergy(network: InternetConnection) =
    network match
        case InternetConnection.Mobile  => 0.00043
        case InternetConnection.Fixed   =>  0.00088


def footprint(stream: VideoStream): Double =
    val megabyte = stream.duration.seconds * stream.bitRate.mb_per_seconds
    val energy   = dataCenterEnergyPerMB + networkEnergy(stream.connection)
    val kwH = energy * megabyte
    kwH * kgOfCO2PerKWH

footprint(coffeeBreakStream)



