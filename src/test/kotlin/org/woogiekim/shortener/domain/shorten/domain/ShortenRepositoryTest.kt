package org.woogiekim.shortener.domain.shorten.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShortenRepositoryTest @Autowired constructor(
    private val shortenRepository: ShortenRepository
) {

    @Test
    fun `짧은 주소 사용 내역 조회`() {
        val shorten = shortenRepository.save(createShorten())

        val sut = shortenRepository.findShortenUsage(shorten.id!!)

        assertThat(sut.shorten).isSameAs(shorten)
    }
}
