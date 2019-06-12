package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.fullParkedParkingLot
import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValidTimes


class CarLeaveCommandTest extends Specification {

    def "parking car command validate and execute valid input"() {
        setup:
        ParkingLot parkingLot = fullParkedParkingLot(3)
        when:
        String result = validateAndExecuteIfValidTimes(new CarLeaveCommand(parkingLot, slotNum), numOfCalls)
        then:
        expected == result
        where:
        numOfCalls | slotNum | expected
        1          | 4       | "Slot number should be between 1 and 3"
        1          | 1       | "Slot number 1 is free"
        1          | 2       | "Slot number 2 is free"
        2          | 3       | "Cannot release empty parking slot"
    }

}