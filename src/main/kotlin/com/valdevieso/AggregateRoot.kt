package com.valdevieso

import java.util.*
import kotlin.reflect.KCallable

abstract class AggregateRoot {
    private var changes: MutableList<Event> = mutableListOf<Event>()
    var version: Int = 0
        private set

    abstract fun getId(): UUID

    abstract fun apply(event: Event)

    fun replay(events: Iterable<Event>)
    {
        events.forEach {
            this.apply(it)
            this.version++
        }
    }

    fun applyChange(event: Event)
    {
        this.apply(event)
        this.changes.add(event)
        this.version++
    }
}