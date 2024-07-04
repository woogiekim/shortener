package org.woogiekim.shortener.domain.shorten.domain.component

import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import kotlin.random.Random

interface CodeGenerator {
    fun generate(): String
}

@Component
class DefaultCodeGenerator : CodeGenerator {
    override fun generate(): String {
        var code = ""

        repeat(8) {
            val drawnNumber = RANDOM.nextInt(LETTERS.size)

            code += LETTERS[drawnNumber]
        }

        return code
    }

    companion object {
        val LETTERS = arrayOf(
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'
        )
        val RANDOM = Random(OffsetDateTime.now().toEpochSecond())
    }
}
