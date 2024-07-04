package org.woogiekim.shortener.adaptor.`in`.web

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.woogiekim.shortener.adaptor.`in`.web.view.CreateShortenReq
import org.woogiekim.shortener.adaptor.`in`.web.view.CreateShortenRes
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreator
import org.woogiekim.shortener.domain.shorten.application.usecase.ShortenCreatorCommand
import org.woogiekim.shortener.domain.shorten.domain.component.CodeGenerator

@RestController
@RequestMapping("/api/shorten")
class ShortenApi(
    private val shortenCreator: ShortenCreator,
    private val codeGenerator: CodeGenerator
) {

    @PostMapping
    fun create(
        request: HttpServletRequest,
        @RequestBody req: CreateShortenReq
    ): CreateShortenRes {
        val shorten =
            shortenCreator.create(ShortenCreatorCommand(codeGenerator, req.createOriginUrl()))

        return CreateShortenRes(shorten, request)
    }
}
