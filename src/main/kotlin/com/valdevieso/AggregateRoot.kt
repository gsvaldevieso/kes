package com.valdevieso

import java.util.*
import kotlin.reflect.KCallable

abstract class AggregateRoot {
    private var changes: List<Event> = mutableListOf<Event>()
    var version: Int = 0
        private set

    abstract fun getId(): UUID

    abstract fun apply(event: Event)

    fun reapplyEvents(events: Iterable<Event>)
    {
        events.forEach { this.apply(it) }
    }

    fun applyChange(event: Event)
    {
        this.apply(event)
    }
}