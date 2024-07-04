package org.woogiekim.shortener.domain.shorten.application.usecase

import org.woogiekim.shortener.domain.shorten.domain.OriginUrl
import org.woogiekim.shortener.domain.shorten.domain.Shorten
import org.woogiekim.shortener.domain.shorten.domain.component.CodeGenerator

interface ShortenCreator {

    fun create(command: ShortenCreatorCommand): Shorten
}

data class ShortenCreatorCommand(
    val codeGenerator: CodeGenerator,
    val originUrl: OriginUrl
)
