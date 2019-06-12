package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot

enum class Command(val consoleCommand: String, val parameters: String) {
    CREATE_PARKING_LOT("create_parking_lot", "\$size_of_parking_lot"),
    PARK_CAR("park", " \$registration_number \$car_color"),
    CAR_LEAVE("leave", "\$parking_slot_number"),
    STATUS("status", ""),
    REG_NUMS_FOR_CAR_COLOUR("registration_numbers_for_cars_with_colour", "\$car_color"),
    SLOT_NUMS_FOR_CAR_COLOUR("slot_numbers_for_cars_with_colour", "\$car_color"),
    SLOT_NUM_FOR_REG_NUM("slot_number_for_registration_number", "\$registration_number")
}

//region Parking Lot Abstract Command
abstract class ParkingLotCommand(open val parkingLot: ParkingLot) {
    abstract fun validate(): ValidationResult
    abstract fun execute(): String
}
//endregion

//region Validation Result
sealed class ValidationResult

object Valid : ValidationResult()
data class Invalid(val message: String) : ValidationResult()
//endregion
