package org.woogiekim.shortener.domain.shorten.application.usecase

import java.net.URI

interface ShortenRedirector {

    fun redirect(command: ShortenRedirectorCommand): URI
}

data class ShortenRedirectorCommand(val code: String)