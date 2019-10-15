package com.mmm.retail.helper

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxEvent {
    private val event = PublishSubject.create<Any>()
    fun send(obj: Any) {
        event.onNext(obj)
    }

    fun toObservable(): Observable<Any> {
        return event
    }
}
