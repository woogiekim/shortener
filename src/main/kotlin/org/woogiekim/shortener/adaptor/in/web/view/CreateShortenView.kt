package org.woogiekim.shortener.adaptor.`in`.web.view

import org.springframework.web.util.UriComponentsBuilder
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl.Companion.createOriginUrl

data class CreateShortenReq(val originUrl: String) {

    fun createOriginUrl(): OriginUrl {
        return createOriginUrl(UriComponentsBuilder.fromHttpUrl(this.originUrl).build())
    }
}