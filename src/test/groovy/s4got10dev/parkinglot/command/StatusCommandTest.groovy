package s4got10dev.parkinglot.command

import s4got10dev.parkinglot.domain.ParkingLot
import spock.lang.Specification

import static s4got10dev.parkinglot.TestUtils.fullParkedParkingLot
import static s4got10dev.parkinglot.TestUtils.validateAndExecuteIfValid

class StatusCommandTest extends Specification {

    def "paring lot status generated"() {
        setup:
        ParkingLot parkingLot = fullParkedParkingLot(3)
        when:
        String result = validateAndExecuteIfValid(new StatusCommand(parkingLot))
        then:
        result == '''Slot No.     Registration No.          Colour         
1            Test 1                    Black          
2            Test 2                    Black          
3            Test 3                    Black          
'''
    }

}