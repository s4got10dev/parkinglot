package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.Car
import s4got10dev.parkinglot.domain.Empty
import s4got10dev.parkinglot.domain.ParkingLot
import s4got10dev.parkinglot.domain.Slot

//region Status Command
class StatusCommand(override val parkingLot: ParkingLot) : ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult = Valid

    override fun execute(): String {
        val builder = StringBuilder()
        builder.append(STATUS_TEMPLATE.format("Slot No.", "Registration No.", "Colour"))
        parkingLot.occupiedSlots.values.map { builder.append(slotStatus(it)) }
        return builder.toString()
    }

    private fun slotStatus(slot: Slot): String {
        return when (val car = slot.car) {
            is Empty -> ""
            is Car -> STATUS_TEMPLATE.format(slot.number, car.registrationNum, car.color)
        }
    }

    companion object {
        const val STATUS_TEMPLATE = "%-12s %-25s %-15s\n"
    }
}
//endregion

//region Registration Numbers For Color Command
class RegistrationNumForColorCommand(override val parkingLot: ParkingLot, private val color: String) :
    ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult {
        return if (color.isBlank()) {
            Invalid("Please provide color")
        } else {
            Valid
        }
    }

    override fun execute(): String {
        return slotsWithCarColor(parkingLot, color).joinToString { it.car.registrationNum }.notFoundIsEmpty()
    }

}
//endregion

//region Slot Numbers For Color Command
class SlotNumForColorCommand(override val parkingLot: ParkingLot, private val color: String) :
    ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult {
        return if (color.isBlank()) {
            Invalid("Please provide color")
        } else {
            Valid
        }
    }

    override fun execute(): String {
        return slotsWithCarColor(parkingLot, color).map { it.number }.joinToString().notFoundIsEmpty()
    }

}
//endregion

//region Slot Number For Registration Number Command
class SlotNumForRegNumCommand(override val parkingLot: ParkingLot, private val regNum: String) :
    ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult {
        return if (regNum.isBlank()) {
            Invalid("Please provide car registration number")
        } else {
            Valid
        }
    }

    override fun execute(): String {
        return parkingLot.occupiedSlots.values.find { it.car.registrationNum == regNum }.number()
    }

}
//endregion

private fun slotsWithCarColor(parkingLot: ParkingLot, color: String) =
    parkingLot.occupiedSlots.values.filter { it.car.color == color }

private fun String.notFoundIsEmpty() = when {
    this.isBlank() -> "Not found"
    else -> this
}

private fun Slot?.number() = when {
    this == null -> "Not found"
    else -> this.number.toString()
}