package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.parkingLotForTesting
import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValid


class SlotNumForRegNumCommandTest extends Specification {

    def "slot number for specified car registration found"() {
        setup:
        ParkingLot parkingLot = parkingLotForTesting()
        when:
        String result = validateAndExecuteIfValid(new SlotNumForRegNumCommand(parkingLot, regNum))
        then:
        expected == result
        where:
        regNum         | expected
        "KX-1242-B"    | "1"
        "BS-2346-Y"    | "4"
        "NotAvailable" | "Not found"
        ""             | "Please provide car registration number"
    }
}