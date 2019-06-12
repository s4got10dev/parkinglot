import s4got10dev.parkinglot.command.Command
import s4got10dev.parkinglot.command.Invalid
import s4got10dev.parkinglot.command.ParkingLotCommand
import s4got10dev.parkinglot.command.Valid
import s4got10dev.parkinglot.domain.NotInitializedParkingLot
import java.io.File

class Processor {
    private val lineList : MutableList<String> = mutableListOf()
    private var lineIndex = 0

    fun checkFile(file: File?) : Boolean {
        return if (file != null && file.exists() && file.isFile) {
            file.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
            true
        } else {
            false
        }
    }

    fun proceed(): Boolean {
        return when (lineIndex) {
            0 -> true
            lineList.size -> {
                lineIndex = 0
                lineList.clear()
                true //false will lead to application exit after parsing file
            }
            else -> true
        }
    }

    fun nextCommand()= if (lineList.isEmpty()) readLine().orEmpty() else lineList[lineIndex++]
}

fun proceedCommand(command: ParkingLotCommand): String {
    if (command.parkingLot is NotInitializedParkingLot) {
        return "Create Parking lot first using: ${Command.CREATE_PARKING_LOT.consoleCommand} ${Command.CREATE_PARKING_LOT.parameters}"
    }
    return when (val validationResult = command.validate()) {
        is Invalid -> validationResult.message
        is Valid -> command.execute()
    }
}

fun generateUsage(): String {
    val builder: StringBuilder = StringBuilder()
    builder.append("Available commands:\n").append("    -help\n").append("    -exit\n").append("    -file \$path_to_file\n")
    Command.values().forEach { with(builder) { append("    -").append(it.consoleCommand).append(" ").append(it.parameters).append("\n") } }
    return builder.toString()
}