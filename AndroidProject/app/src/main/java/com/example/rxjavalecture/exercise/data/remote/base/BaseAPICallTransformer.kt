package com.example.rxjavalecture.exercise.data.remote.base

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer


fun Completable.transformCompletableToSingleDefault(): Single<BaseAPICallResult<Unit>> =
    toSingleDefault(Unit)
        .compose(wrappingSingleAPIResultData())

fun <T> Single<T>.wrappingAPICallResult(): Single<BaseAPICallResult<T>>
    = compose(wrappingSingleAPIResultData())

fun <T> wrappingSingleAPIResultData() = SingleTransformer<T, BaseAPICallResult<T>> { single ->
    single
        .map { data -> BaseAPICallResult(result = data) }
        .onErrorReturn { BaseAPICallResult(throwable = it) }
}