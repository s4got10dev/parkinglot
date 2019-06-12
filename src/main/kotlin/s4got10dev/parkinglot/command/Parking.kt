package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.*

//region Park Car Command
class ParkCarCommand(override val parkingLot: ParkingLot, private val regNum: String, private val color: String) :
    ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult {
        return when {
            regNum.isBlank() -> Invalid("Registration number can not be empty")
            color.isBlank() -> Invalid("Color can not be empty")
            else -> Valid
        }
    }

    override fun execute(): String {
        return when (val status = parkingLot.parkCar(Car(regNum, color))) {
            is LackOfFreeCarSlot -> "Sorry, parking lot is full"
            is CarSlotOccupied -> "Allocated slot number: ${status.slotNum}"
        }
    }
}

//endregion

//region Car Leave Command
class CarLeaveCommand(override val parkingLot: ParkingLot, private val slotNum: Int) : ParkingLotCommand(parkingLot) {

    override fun validate(): ValidationResult {
        return when (slotNum) {
            in (1..parkingLot.size) -> Valid
            else -> Invalid("Slot number should be between 1 and ${parkingLot.size}")
        }
    }

    override fun execute(): String {
        return when (val status = parkingLot.carLeave(slotNum)) {
            is CarSlotIsEmpty -> "Cannot release empty parking slot"
            is CarSlotReleased -> "Slot number ${status.slotNum} is free"
        }
    }
}
//endregion
