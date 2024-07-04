package org.woogiekim.shortener.domain.shorten.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ShortenRepository : JpaRepository<Shorten, Long> {

    @Query("""
        select su
        from ShortenUsage su
        where su.shorten.id = :id
    """)
    fun findShortenUsage(id: Long): ShortenUsage
}
