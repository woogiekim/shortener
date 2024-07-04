package org.woogiekim.shortener.domain.shorten.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.web.util.UriComponentsBuilder
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl.Companion.MAXIMUM_ORIGIN_URL_LENGTH
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl.Companion.createOriginUrl

class OriginUrlTest {

    @Test
    fun `원본 주소 생성 성공`() {
        val httpUrl = "http://origin.com/"
        val origin = UriComponentsBuilder.fromHttpUrl(httpUrl)
            .path("a".repeat(MAXIMUM_ORIGIN_URL_LENGTH - httpUrl.length))
            .build()

        val sut = createOriginUrl(origin)

        assertThat(sut.value).isEqualTo(origin.toUriString())
    }

    @EmptySource
    @ValueSource(strings = [
        "origin", ".com", "origin.com"
    ])
    @ParameterizedTest
    fun `원본 주소 생성 실패 - 유효하지 않은 원본 주소`(invalidOriginUrl: String) {
        assertThatIllegalArgumentException().isThrownBy { createOriginUrl(invalidOriginUrl) }
    }

    @Test
    fun `원본 주소 생성 실패 - 유효하지 않은 원본 주소 길이`() {
        val httpUrl = "http://origin.com/"
        val invalidOriginUrl = UriComponentsBuilder.fromHttpUrl(httpUrl)
            .path("a".repeat(MAXIMUM_ORIGIN_URL_LENGTH + 1 - httpUrl.length))
            .build()

        assertThatIllegalArgumentException().isThrownBy { createOriginUrl(invalidOriginUrl) }
    }
}

fun createOriginUrl(
    origin: String = "http://origin.com"
): OriginUrl = createOriginUrl(UriComponentsBuilder.fromHttpUrl(origin).build())
