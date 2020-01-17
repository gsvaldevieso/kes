package com.valdevieso

import java.util.*

interface EventStore {
    fun save(aggregateId: UUID, events: Iterable<Event>, expectedVersion: Int)
    fun getEventsFrom(aggregateId: UUID): List<Event>
}