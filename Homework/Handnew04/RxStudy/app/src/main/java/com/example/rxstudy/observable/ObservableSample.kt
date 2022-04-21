package com.example.rxstudy.observable

import io.reactivex.Observable

class ObservableSample {
   fun foo() {
      val sample1 = Observable
         .just("54321")
         .subscribe { data: String ->
            print(data)
         }

      val sample2 = Observable
         .just("121212")
         .subscribe({
            print(it)
         }, { e ->
            e.printStackTrace()
         }
         )

      val sample3 = Observable
         .just(23)
         .subscribe({ data: Int ->
            print(data)
         }, { e ->
            e.printStackTrace()
         }, {
            print("완료")
         })

   }
}