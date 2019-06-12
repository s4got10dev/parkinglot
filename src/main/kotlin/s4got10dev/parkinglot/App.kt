package s4got10dev.parkinglot

import Processor
import generateUsage
import proceedCommand
import s4got10dev.parkinglot.command.*
import s4got10dev.parkinglot.command.Command.*
import s4got10dev.parkinglot.domain.NotInitializedParkingLot
import s4got10dev.parkinglot.domain.ParkingLot
import java.io.File

fun main() {
    println("Welcome to Parking Lot Application!")
    println("Enter your command, help or exit:")
    val processor = Processor()
    var parkingLot: ParkingLot = NotInitializedParkingLot
    while (processor.proceed()) {
        val input = processor.nextCommand()
        println(
            when (input.substringBefore(" ")) {
                "exit" -> System.exit(0)
                "help" -> generateUsage()
                "file" -> {
                    val fileName = parseParameter(input, 1)
                    val providedFile = File(fileName)
                    if (processor.checkFile(providedFile)) {
                        "Processing commands from file: $fileName"
                    } else {
                        "Provided file: $fileName not exist"
                    }
                }
                CREATE_PARKING_LOT.consoleCommand -> {
                    val size = parseIntParameter(input, 1)
                    when {
                        size < 1 -> "Size of parking lot should be positive number"
                        else -> {
                            parkingLot = ParkingLot(size)
                            "Created a parking lot with ${parkingLot.size} slots"
                        }
                    }
                }
                PARK_CAR.consoleCommand -> {
                    proceedCommand(
                        ParkCarCommand(
                            parkingLot, parseParameter(input, 1), parseParameter(input, 2)
                        )
                    )
                }
                CAR_LEAVE.consoleCommand -> {
                    proceedCommand(
                        CarLeaveCommand(parkingLot, parseIntParameter(input, 1))
                    )
                }
                STATUS.consoleCommand -> proceedCommand(
                    StatusCommand(parkingLot)
                )
                REG_NUMS_FOR_CAR_COLOUR.consoleCommand -> {
                    proceedCommand(RegistrationNumForColorCommand(parkingLot, parseParameter(input, 1)))
                }
                SLOT_NUMS_FOR_CAR_COLOUR.consoleCommand -> {
                    proceedCommand(SlotNumForColorCommand(parkingLot, parseParameter(input, 1)))
                }
                SLOT_NUM_FOR_REG_NUM.consoleCommand -> {
                    proceedCommand(SlotNumForRegNumCommand(parkingLot, parseParameter(input, 1)))
                }
                else -> "Unknown command: $input! Use help for listing available commands"
            }
        )
    }

}
