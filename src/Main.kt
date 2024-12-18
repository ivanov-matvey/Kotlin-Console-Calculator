fun main() {
    while (start) {
        println("┌${centeredText("Действия", cardWidth)}┐")
        printMap(actionsMap)
        println("└${"─".repeat(cardWidth - 2)}┘")

        val action = mutableListOf<Any?>(null)
        inputMenu(action, actionsMap, "Выберите действие: ", "Действия")

        when (action[0]) {
            1 -> basicAction("Сложение", ::addition, true)
            2 -> basicAction("Вычитание", ::subtraction, true)
            3 -> basicAction("Умножение", ::multiplication, true)
            4 -> basicAction("Деление", ::division)
            5 -> basicAction("Остаток от деления", ::remainderOfDivision)
            6 -> basicAction("Степень", ::degree)
            7 -> factorial("Факториал")
            8 -> basicAction("Наибольший общий делитель", ::greatestCommonDivisor, true)
            0 -> exit()
            else -> println("Действие не найдено")
        }
    }
}
