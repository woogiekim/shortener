package org.woogiekim.shortener.domain.shorten.application.service

import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreator
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreatorCommand
import org.woogiekim.shortener.domain.shorten.domain.Shorten
import org.woogiekim.shortener.domain.shorten.domain.Shorten.Companion.createShorten
import org.woogiekim.shortener.domain.shorten.domain.ShortenRepository

@Service
class ShortenService(
    private val shortenRepository: ShortenRepository
) : ShortenCreator {
    override fun create(command: ShortenCreatorCommand): Shorten {
        repeat(3) {
            try {
                val shorten = with(command) { createShorten(codeGenerator, originUrl) }

                return shortenRepository.save(shorten)
            } catch (ignore: DuplicateKeyException) {
            }
        }

        throw RuntimeException()
    }
}
