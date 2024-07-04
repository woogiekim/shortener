package org.woogiekim.shortener.domain.shorten.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.woogiekim.shortener.domain.shorten.domain.component.CodeGenerator
import org.woogiekim.shortener.domain.shorten.domain.Shorten.Companion.MAXIMUM_CODE_LENGTH
import org.woogiekim.shortener.domain.shorten.domain.Shorten.Companion.createShorten

class ShortenTest {

    @Test
    fun `짧은 주소 생성 성공`() {
        val sut = createShorten(codeGenerator("abcd1234"), createOriginUrl("http://test.com"))

        assertThat(sut.code).isEqualTo("abcd1234")
        assertThat(sut.originUrl.value).isEqualTo("http://test.com")
        assertThat(sut.usage).isNotNull
        assertThat(sut.usage!!.shorten).isSameAs(sut)
    }

    @ValueSource(ints = [MAXIMUM_CODE_LENGTH + 1, MAXIMUM_CODE_LENGTH - 1])
    @ParameterizedTest
    fun `짧은 주소 생성 실패 - 유효하지 않은 코드 길이`(codeLength: Int) {
        val invalidCode = "1".repeat(codeLength)

        assertThatIllegalArgumentException().isThrownBy { createShorten(codeGenerator(invalidCode), createOriginUrl()) }
    }
}

fun codeGenerator(code: String = "abcd1234") = object : CodeGenerator {
    override fun generate(): String = code
}

fun createShorten(
    code: String = "abcd1234",
    originUrl: String = "http://origin.com"
): Shorten {
    return createShorten(codeGenerator(code), createOriginUrl(originUrl))
}
