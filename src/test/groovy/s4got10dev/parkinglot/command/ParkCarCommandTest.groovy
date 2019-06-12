package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValidTimes


class ParkCarCommandTest extends Specification {

    def "parking car command validate and execute valid input"() {
        setup:
        ParkingLot parkingLot = new ParkingLot(1)
        when:
        String result = validateAndExecuteIfValidTimes(new ParkCarCommand(parkingLot, regNum, color), numOfCars)
        then:
        expected == result
        where:
        numOfCars | regNum | color   | expected
        1         | ""     | ""      | "Registration number can not be empty"
        1         | "AA123"| ""      | "Color can not be empty"
        1         | "AA123"| "Black" | "Allocated slot number: 1"
        2         | "AA123"| "Black" | "Sorry, parking lot is full"
    }

}