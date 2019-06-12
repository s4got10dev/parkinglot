package s4got10dev.parkinglot.command


import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.parkingLotForTesting
import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValid

class RegistrationNumForColorCommandTest extends Specification {

    def "car registration numbers for specified car color found"() {
        setup:
        ParkingLot parkingLot = parkingLotForTesting()
        when:
        String result = validateAndExecuteIfValid(new RegistrationNumForColorCommand(parkingLot, color))
        then:
        expected == result
        where:
        color    | expected
        "Black"  | "KX-1242-B, AH-8234-B, HR-1336-B"
        "Yellow" | "GS-5472-Y, BS-2346-Y"
        "White"  | "SR-3212-W"
        "Blue"   | "Not found"
        ""       | "Please provide color"
    }

}