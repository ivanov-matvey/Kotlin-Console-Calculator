import kotlin.math.pow


var start = true

val actionsMap = mapOf(
    1 to "Сложение",
    2 to "Вычитание",
    3 to "Умножение",
    4 to "Деление",
    5 to "Остаток от деления",
    6 to "Возведение в степень",
    7 to "Факториал числа",
    8 to "Наибольший общий делитель",
    0 to "Выход"
)

val numberOfDigitsMap = mapOf(
    1 to "Два числа",
    2 to "Множество чисел"
)

fun inputInt(label: String): Int {
    var num: Int? = null
    do {
        print(label)
        try {
            num = readln().trim().toInt()
        } catch (e: IllegalArgumentException) {
            printError(error="Вы ввели не число", connectable=false)
        }
    } while (num !is Int)
    return num
}

fun inputMenu(
    variable: MutableList<Any?>,
    map: Map<Int, String>,
    inputLabel: String,
    windowLabel: String
) {
    do {
        print(inputLabel)
        try {
            variable[0] = readln().trim().toInt()
        } catch (e: IllegalArgumentException) {
            printError(windowLabel, "Вы ввели не число", true)
            printMap(map)
            println("└${"─".repeat(cardWidth - 2)}┘")
        }
        if (variable[0] != null && variable[0] !in map.keys) {
            printError(windowLabel, "Такого пункта нет", true)
            printMap(map)
            println("└${"─".repeat(cardWidth - 2)}┘")
            variable[0] = null
        }
    } while (variable[0] !is Int)
}

fun basicAction(
    label: String,
    operation: (MutableList<Int>) -> String,
    manyActions: Boolean = false
) {
    println("┌${centeredText(label, cardWidth)}┐")
    if (manyActions) {
        printMap(numberOfDigitsMap)
    }
    println("└${"─".repeat(cardWidth - 2)}┘")
    val digits: MutableList<Int> = mutableListOf()

    val numberOfDigits = mutableListOf<Any?>(null)
    if (manyActions) {
        inputMenu(numberOfDigits, numberOfDigitsMap, "Выберите действие: ", "Сложение")
    } else {
        numberOfDigits[0] = 1
    }

    if (numberOfDigits[0] == 1) {
        repeat(2) { i ->
            digits.add(inputInt("Введите ${i + 1} число: "))
        }
    } else if (numberOfDigits[0] == 2) {
        var digitsCount: Int
        do {
            digitsCount = inputInt("Введите количество чисел: ")
            if (digitsCount < 2) {
                printError(error="Кол-во должно быть больше 1", connectable=false)
            }
        } while (digitsCount < 2)
        repeat(digitsCount) { i ->
            digits.add(inputInt("Введите ${i + 1} число: "))
        }
    }
    
    val resultText = operation(digits)
    printBlock("Результат", resultText)
}

fun addition(digits: MutableList<Int>): String {
    val result = digits.sum()
    return digits.joinToString(" + ") + " = $result"
}

fun subtraction(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> acc - next }
    return digits.joinToString(" - ") + " = $result"
}

fun multiplication(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> acc * next }
    return digits.joinToString(" * ") + " = $result"
}

fun division(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> acc / next }
    return digits.joinToString(" / ") + " = $result"
}

fun remainderOfDivision(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> acc % next }
    return digits.joinToString(" % ") + " = $result"
}

fun degree(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> acc.toFloat().pow(next).toInt()}
    return digits.joinToString(" ^ ") + " = $result"
}

fun factorial(label: String) {
    println("┌${centeredText(label, cardWidth)}┐")
    println("└${"─".repeat(cardWidth - 2)}┘")

    val number = inputInt("Введите число: ")
    var result = number
    for (i in 1..<number) {
        result *= i
    }

    val resultText = "Факториал числа $number = $result"
    printBlock("Результат", resultText)
}

fun findGcd(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val temp = y
        y = x % y
        x = temp
    }
    return x
}

fun greatestCommonDivisor(digits: MutableList<Int>): String {
    val result = digits.reduce { acc, next -> findGcd(acc, next) }
    return "Наибольший общий делитель: $result"
}

fun exit() {
    printBlock("Выход", "Программа остановлена")
    start = false
}