package org.woogiekim.shortener.domain.shorten.application.service

import jakarta.transaction.Transactional
import org.springframework.dao.DuplicateKeyException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreator
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreatorCommand
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenAccessor
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenAccessorCommand
import org.woogiekim.shortener.domain.shorten.domain.Shorten
import org.woogiekim.shortener.domain.shorten.domain.Shorten.Companion.createShorten
import org.woogiekim.shortener.domain.shorten.domain.ShortenRepository
import java.net.URI

@Transactional
@Service
class ShortenService(
    private val shortenRepository: ShortenRepository
) : ShortenCreator, ShortenAccessor {

    @Retryable(retryFor = [DuplicateKeyException::class])
    override fun create(command: ShortenCreatorCommand): Shorten {
        val shorten = with(command) { createShorten(codeGenerator, originUrl) }

        return shortenRepository.save(shorten)
    }

    override fun access(command: ShortenAccessorCommand): URI {
        val shorten = shortenRepository.findShortenByCode(command.code)

        shorten.access()

        return URI.create(shorten.originUrl.value)
    }
}
