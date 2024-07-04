package org.woogiekim.shortener.adaptor.`in`.web.view

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.util.UriComponentsBuilder
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl
import org.woogiekim.shortener.domain.shorten.domain.OriginUrl.Companion.createOriginUrl
import org.woogiekim.shortener.domain.shorten.domain.Shorten

data class CreateShortenReq(val originUrl: String) {

    fun createOriginUrl(): OriginUrl {
        return createOriginUrl(UriComponentsBuilder.fromHttpUrl(this.originUrl).build())
    }
}

class CreateShortenRes(
    shorten: Shorten,
    req: HttpServletRequest,
    val redirectUrl: String = UriComponentsBuilder.newInstance()
        .scheme(req.scheme)
        .host(req.serverName)
        .port(req.serverPort)
        .path(shorten.code).toUriString()
)
