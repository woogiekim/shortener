package org.woogiekim.shortener.support

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.domain.AbstractAggregateRoot

@MappedSuperclass
abstract class BaseAggregateRoot<A : BaseAggregateRoot<A>>(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) : AbstractAggregateRoot<A>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseAggregateRoot<*>) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
