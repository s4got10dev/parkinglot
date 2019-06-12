package s4got10dev.parkinglot.domain


sealed class SlotCar(open val registrationNum: String, open val color: String)
object Empty : SlotCar("", "")
class Car(override val registrationNum: String, override val color: String) : SlotCar(registrationNum, color)

sealed class ParkCarState
class CarSlotOccupied(val slotNum: Int) : ParkCarState()
object LackOfFreeCarSlot : ParkCarState()

sealed class LeaveCarState
class CarSlotReleased(val slotNum: Int) : LeaveCarState()
object CarSlotIsEmpty : LeaveCarState()
