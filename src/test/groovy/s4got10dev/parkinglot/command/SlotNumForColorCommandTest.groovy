package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.parkingLotForTesting
import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValid


class SlotNumForColorCommandTest extends Specification {

    def "slot numbers for specified car color found"() {
        setup:
        ParkingLot parkingLot = parkingLotForTesting()
        when:
        String result = validateAndExecuteIfValid(new SlotNumForColorCommand(parkingLot, color))
        then:
        expected == result
        where:
        color    | expected
        "Black"  | "1, 3, 7"
        "Yellow" | "2, 4"
        "White"  | "9"
        "Blue"   | "Not found"
        ""       | "Please provide color"
    }

}