package org.woogiekim.shortener.domain.shorten.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.woogiekim.shortener.domain.shorten.BaseUseCaseTest
import org.woogiekim.shortener.domain.shorten.domain.ShortenRepository
import org.woogiekim.shortener.domain.shorten.domain.createShorten

class ShortenAccessorTest @Autowired constructor(
    private val shortenAccessor: ShortenAccessor,
    private val shortenRepository: ShortenRepository
) : BaseUseCaseTest() {

    @Test
    fun `짧은 주소 접근`() {
        val shorten = shortenRepository.save(createShorten())

        assertThat(shorten.usage!!.accessCount).isZero

        val sut = shortenAccessor.access(ShortenAccessorCommand(shorten.code))

        assertThat(shorten.usage!!.accessCount).isEqualTo(1)
        assertThat(sut.toString()).isEqualTo(shorten.originUrl.value)
    }
}
