package com.example.rxkotlin.com

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rxkotlin.R
import io.reactivex.*
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.w3c.dom.Text

/**
 *
 * PublishSubject / BehaviorSubject / AsyncSubject / ReplaySubject를
 * 어떨 때 사용하면 좋을 지 찾아서 공유 해 주세요.
 * Subject를 활용하여 데이터를 발행 / 구독 해 보고,
 * 어떻게 동작하는지 확인 해 주세요.
 * 수업때 보았던 예시코드( cbfbad1 )들을 활용해도 좋고,
 * 직접 만들어 보셔도 좋습니다
 */

/**
 * 1, PublishSubject
 * -> 구독 시점 이전에 발행된 데이터는 무시하고, 구독 시점 이후에 발행된 데이터를 발행
 *
 * 2, BehaviorSubject
 * -> 구독 시점 이전에 발행된 데이터 중 가장 최근에 발행된 데이터 하나만 발행,
 *    구독 시점 이후에 발행된 데이터를 발행
 *
 * 3, AsyncSubject
 * -> 데이터 발행이 완료되면, 데이터 발행 완료 직전에 발행된 데이터만 발행
 *
 * 4, ReplaySubject
 * -> 구독 시점 이전에 발행된 데이터를 모두 발행하고, 구독 시점 이후에 발행된 데이터를 발행
 */


class Week6Assignment : AppCompatActivity() {

    private lateinit var mEdText: EditText
    private lateinit var mButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_6_assignment)
        mEdText = findViewById(R.id.ed_text)
        mButton = findViewById(R.id.button)
        mButton.setOnClickListener {setRxKotlinJustReStart()}
        mPublishSubjectEvent()
        mBehaviorSubjectEvent()
        mAsyncSubjectEvent()
        mReplaySubjectEvent()

    }


    private fun setRxKotlinJustReStart() {
        mPublishSubjectEvent()
        mBehaviorSubjectEvent()
        mAsyncSubjectEvent()
        mReplaySubjectEvent()

    }

    private fun mPublishSubjectEvent() {

        val publishSubject = PublishSubject.create<String>()
        val mStartEventToPublishSubJect =
            publishSubject.subscribe {
                println(it)
        }
        println("$mStartEventToPublishSubJect")
        publishSubject.onNext("publishSubject의 출력 ")
        publishSubject.onComplete()

    }

    private fun mBehaviorSubjectEvent() {

        val behaviorSubject = BehaviorSubject.create<String>()
        val mStartEventToBehaviorSubjectEvent =
            behaviorSubject.subscribe {
                println(it)
            }
        println("$mStartEventToBehaviorSubjectEvent")
        behaviorSubject.onNext("behaviorSubject 출력 ")
        behaviorSubject.onComplete()
    }

    private fun mAsyncSubjectEvent() {

        val asyncSubject = AsyncSubject.create<String>()
        val mStartEventToAsyncSubjectEvent =
            asyncSubject.subscribe {
                println(it)
            }
        println("$mStartEventToAsyncSubjectEvent")
        asyncSubject.onNext("asyncSubject 출력 ")
        asyncSubject.onComplete()
    }

    private fun mReplaySubjectEvent() {

        val replaySubject = ReplaySubject.create<String>()
        val mStartEventToReplaySubjectEvent =
            replaySubject.subscribe {

                println(it)
            }
        println("$mStartEventToReplaySubjectEvent")
        replaySubject.onNext("ReplaySubject 의 출력 ")
        replaySubject.onComplete()
    }

}

