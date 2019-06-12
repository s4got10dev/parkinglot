package s4got10dev.parkinglot

import s4got10dev.parkinglot.command.ParkingLotCommand
import s4got10dev.parkinglot.command.Valid
import s4got10dev.parkinglot.command.ValidationResult
import s4got10dev.parkinglot.domain.Car
import s4got10dev.parkinglot.domain.ParkingLot

class TestUtils {

    static def validateAndExecuteIfValid(ParkingLotCommand command) {
        ValidationResult validationResult = command.validate()
        validationResult in Valid ? command.execute() : validationResult.message
    }

    static def validateAndExecuteIfValidTimes(ParkingLotCommand command, Integer times) {
        String result
        1.upto(times) {
            result = validateAndExecuteIfValid(command)
        }
        result
    }

    static def fullParkedParkingLot(Integer size) {
        ParkingLot parkingLot = new ParkingLot(size)
        1.upto(size) {
            parkingLot.parkCar$parking(new Car("Test ${it}", "Black"))
        }
        parkingLot
    }

    static def parkingLotForTesting() {
        ParkingLot parkingLot = new ParkingLot(9)
        parkingLot.parkCar$parking(new Car("KX-1242-B", "Black"))
        parkingLot.parkCar$parking(new Car("GS-5472-Y", "Yellow"))
        parkingLot.parkCar$parking(new Car("AH-8234-B", "Black"))
        parkingLot.parkCar$parking(new Car("BS-2346-Y", "Yellow"))
        parkingLot.parkCar$parking(new Car("EG-5651-C", "Cyan"))
        parkingLot.parkCar$parking(new Car("HW-6245-G", "Green"))
        parkingLot.parkCar$parking(new Car("HR-1336-B", "Black"))
        parkingLot.parkCar$parking(new Car("JZ-6332-R", "Red"))
        parkingLot.parkCar$parking(new Car("SR-3212-W", "White"))
        parkingLot
    }
}
