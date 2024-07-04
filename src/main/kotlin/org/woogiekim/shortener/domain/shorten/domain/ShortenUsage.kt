package org.woogiekim.shortener.domain.shorten.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import org.woogiekim.shortener.support.BaseEntity
import java.time.OffsetDateTime

/**
 * 짧은 주소 사용 내역
 */
@Entity
class ShortenUsage private constructor(
    /** 짧은 주소 **/
    @OneToOne(fetch = FetchType.LAZY)
    val shorten: Shorten,

    /** 접근 횟수 **/
    var accessCount: Int = 0,

    /** 생성 일시 **/
    val createdAt: OffsetDateTime = OffsetDateTime.now()
) : BaseEntity() {

    fun access() {
        this.accessCount++
    }

    companion object {
        internal fun createShortenUsage(shorten: Shorten): ShortenUsage {
            return ShortenUsage(shorten)
        }
    }
}
