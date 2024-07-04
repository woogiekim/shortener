package org.woogiekim.shortener.domain.shorten.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.web.util.UriComponents

@Embeddable
class OriginUrl private constructor(
    @Column(name = "origin_url", length = MAXIMUM_ORIGIN_URL_LENGTH)
    val value: String
) {

    init {
        require(value.length <= MAXIMUM_ORIGIN_URL_LENGTH)
    }

    companion object {
        const val MAXIMUM_ORIGIN_URL_LENGTH = 2000

        fun createOriginUrl(origin: UriComponents): OriginUrl {
            return OriginUrl(origin.toUriString())
        }
    }
}
