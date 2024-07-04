package org.woogiekim.shortener.domain.shorten.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.woogiekim.shortener.domain.shorten.domain.Shorten.Companion.MAXIMUM_CODE_LENGTH
import org.woogiekim.shortener.domain.shorten.domain.component.CodeGenerator
import org.woogiekim.shortener.domain.shorten.domain.createOriginUrl

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ShortenCreatorTest @Autowired constructor(
    private val shortenCreator: ShortenCreator,
    private val codeGenerator: CodeGenerator
) {

    @Test
    fun `짧은 주소 생성`() {
        val originUrl = createOriginUrl()
        val command = ShortenCreatorCommand(codeGenerator, originUrl)

        val sut = shortenCreator.create(command)

        assertThat(sut.code).hasSize(MAXIMUM_CODE_LENGTH)
        assertThat(sut.originUrl).isEqualTo(originUrl)
    }
}
