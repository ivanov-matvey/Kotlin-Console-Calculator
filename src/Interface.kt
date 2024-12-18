const val chars = "─ │ ┌ ┐ └ ┘ ├ ┤ ┬ ┴ ┼"
const val cardWidth = 40

fun centeredText(
    label: String,
    width: Int,
    char: Char = '─',
): String {
    val formattedLabel = " $label "
    val finalWidth = width - 2
    return formattedLabel.padStart((formattedLabel.length + finalWidth) / 2, char).padEnd(finalWidth, char)
}

fun printError(
    label: String = "",
    error: String,
    connectable: Boolean
) {
    println("""
            ┌${centeredText("Ошибка", cardWidth)}┐
            │ [!] ${error.padEnd(cardWidth - 8)} │
        """.trimIndent())
    if (connectable) {
        println("├${centeredText(label, cardWidth)}┤")
    } else {
        println("└${"─".repeat(cardWidth - 2)}┘")
    }
}

fun printBlock(
    label: String,
    text: String
) {
    println("""
            ┌${centeredText(label, cardWidth)}┐
            │ ${text.padEnd(cardWidth - 4)} │
            └${"─".repeat(cardWidth - 2)}┘
        """.trimIndent())
}

fun printMap(map: Map<Int, String>) {
    for ((key, value) in map) {
        val str = "$key. $value"
        println("│ ${str.padEnd(cardWidth - 3, ' ')}│")
    }
}