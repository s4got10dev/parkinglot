package s4got10dev.parkinglot.domain

import java.util.*

data class Slot(val number: Int, var car: SlotCar) {
    fun isFree() = car is Empty
}

open class ParkingLot constructor(val size: Int) {

    private val freeSlots : PriorityQueue<Slot> = initSlots(size)
    val occupiedSlots: TreeMap<Int, Slot> = TreeMap()

    private fun initSlots(size: Int): PriorityQueue<Slot> {
        val slots = PriorityQueue(size, compareBy(Slot::isFree, Slot::number))
        (1..size).mapTo(slots) { Slot(it, Empty) }
        return slots
    }

    internal fun parkCar(car: Car): ParkCarState {
        if (freeSlots.isEmpty()) {
            return LackOfFreeCarSlot
        }
        val slot = freeSlots.remove()
        slot.car = car
        occupiedSlots[slot.number] = slot
        return CarSlotOccupied(slot.number)
    }

    internal fun carLeave(slotNum: Int): LeaveCarState {
        if (!occupiedSlots.containsKey(slotNum)) {
            return CarSlotIsEmpty
        }
        val slot = occupiedSlots.remove(slotNum) ?: Slot(slotNum, Empty)
        slot.car = Empty
        freeSlots.add(slot)
        return CarSlotReleased(slot.number)
    }
}

object NotInitializedParkingLot : ParkingLot(1)