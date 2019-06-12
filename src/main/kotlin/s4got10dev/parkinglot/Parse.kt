package s4got10dev.parkinglot

sealed class ParsingResult
class Parameter(val value: String) : ParsingResult()
class ParsingError(val message: String) : ParsingResult()

fun parseParameter(input: String, parameterIndex: Int): String {
    return when {
        input.isBlank() -> ""
        else -> {
            val inputs = input.split(" ")
            when {
                inputs.size < parameterIndex + 1 -> ""
                else -> inputs[parameterIndex]
            }
        }
    }
}

fun parseIntParameter(input: String, parameterIndex: Int): Int {
    val value = parseParameter(input, parameterIndex)
    return when {
        value.isBlank() -> Int.MIN_VALUE
        else -> value.toInt()
    }
}