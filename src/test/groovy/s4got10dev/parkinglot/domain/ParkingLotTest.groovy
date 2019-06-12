package s4got10dev.parkinglot.domain

import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.fullParkedParkingLot

class ParkingLotTest extends Specification {

    def "should park a car in parking lot"() {
        setup:
        ParkingLot parkingLot = new ParkingLot(1)
        Integer free = parkingLot.freeSlots.size()
        Integer occupied = parkingLot.occupiedSlots.size()
        when:
        ParkCarState result = parkingLot.parkCar$parking(new Car("test", "Black"))
        then:
        result in CarSlotOccupied
        parkingLot.occupiedSlots.size() == occupied + 1
        parkingLot.freeSlots.size() == free - 1
    }

    def "couldn't park a car in full parking lot"() {
        setup:
        ParkingLot parkingLot = fullParkedParkingLot(1)
        Integer free = parkingLot.freeSlots.size()
        Integer occupied = parkingLot.occupiedSlots.size()
        when:
        ParkCarState result = parkingLot.parkCar$parking(new Car("test", "Black"))
        then:
        result in LackOfFreeCarSlot
        parkingLot.occupiedSlots.size() == occupied
        parkingLot.freeSlots.size() == free
    }

    def "should register car leave"() {
        setup:
        ParkingLot parkingLot = fullParkedParkingLot(1)
        Integer free = parkingLot.freeSlots.size()
        Integer occupied = parkingLot.occupiedSlots.size()
        when:
        LeaveCarState result = parkingLot.carLeave$parking(1)
        then:
        result in CarSlotReleased
        parkingLot.occupiedSlots.size() == occupied - 1
        parkingLot.freeSlots.size() == free + 1
    }

    def "couldn't register car leave from empty slot"() {
        setup:
        ParkingLot parkingLot = new ParkingLot(1)
        Integer free = parkingLot.freeSlots.size()
        Integer occupied = parkingLot.occupiedSlots.size()
        when:
        LeaveCarState result = parkingLot.carLeave$parking(1)
        then:
        result in CarSlotIsEmpty
        parkingLot.occupiedSlots.size() == occupied
        parkingLot.freeSlots.size() == free
    }

}
