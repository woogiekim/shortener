package org.woogiekim.shortener.domain.shorten.application.usecase

import java.net.URI

interface ShortenAccessor {

    fun access(command: ShortenAccessorCommand): URI
}

data class ShortenAccessorCommand(val code: String)
