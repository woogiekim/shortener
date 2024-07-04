package org.woogiekim.shortener.adaptor.`in`.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenRedirector
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenRedirectorCommand

@RestController
class RedirectApi(
    private val shortenRedirector: ShortenRedirector
) {

    @GetMapping("/{code}")
    fun redirect(@PathVariable code: String): ResponseEntity<Any> {
        val location = shortenRedirector.redirect(ShortenRedirectorCommand(code))

        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(location)
            .build()
    }
}