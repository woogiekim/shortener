package org.woogiekim.shortener.domain.shorten.component

import org.springframework.stereotype.Component
import org.woogiekim.shortener.domain.shorten.domain.Shorten
import java.time.OffsetDateTime
import kotlin.random.Random

interface CodeGenerator {
    fun generate(): String
}

@Component
class DefaultCodeGenerator : CodeGenerator {
    override fun generate(): String {
        var code = ""

        for (count in 0 until Shorten.MAXIMUM_CODE_LENGTH) {
            val drawnNumber = RANDOM.nextInt(CODE_LETTER_SIZE)

            code += Char(drawnNumber)
        }

        return code
    }

    companion object {
        const val CODE_LETTER_SIZE = 62
        val RANDOM = Random(OffsetDateTime.now().toEpochSecond())
    }
}
