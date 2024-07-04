package org.woogiekim.shortener.domain.shorten.domain

import jakarta.persistence.*
import org.woogiekim.shortener.domain.shorten.domain.component.CodeGenerator
import org.woogiekim.shortener.domain.shorten.domain.ShortenUsage.Companion.createShortenUsage
import org.woogiekim.shortener.support.BaseAggregateRoot
import java.time.OffsetDateTime

/**
 * 짧은 주소
 */
@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "shorten_code_ukey", columnNames = ["code"])])
class Shorten private constructor(
    /** 짧은 주소 코드 **/
    @Column(length = MAXIMUM_CODE_LENGTH)
    val code: String,

    /** 원본 주소 **/
    @Embedded
    val originUrl: OriginUrl,

    /** 짧은 주소 사용 내역 **/
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "shorten")
    var usage: ShortenUsage? = null,

    /** 생성 일시 **/
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
) : BaseAggregateRoot<Shorten>() {
    init {
        require(code.length == MAXIMUM_CODE_LENGTH)
    }

    companion object {
        const val MAXIMUM_CODE_LENGTH = 8

        fun createShorten(codeGenerator: CodeGenerator, originUrl: OriginUrl): Shorten {
            return Shorten(codeGenerator.generate(), originUrl).apply { usage = createShortenUsage(this) }
        }
    }
}
