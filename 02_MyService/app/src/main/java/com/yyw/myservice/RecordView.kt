package com.yyw.myservice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

/**
 *@project CloudStudio
 *@auther chenzhuang
 *@date 2020/3/17 14:53
 *@describe
 */
class RecordView : View {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mColors = intArrayOf(
        R.color.common_yellow2_color,
        R.color.common_orange2_color,
        R.color.common_purple10_color,
        R.color.common_blue14_color,
        R.color.common_cyan2_color
    )
    private val mRectList: MutableList<RectF> = ArrayList(5)
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private val mRectWidth: Float = 20F
    private var mRectHeight: Float = 80F
    private val mRectInterval: Float = 20F
//    private val mRoundWidth: Float = 30F
    private val mRoundWidth: Float = 22F
//    private val mRoundHeight: Float = 40F
    private val mRoundHeight: Float = 35F
//    private val mRoundInterval: Float = 10F
    private val mRoundInterval: Float = 5F
    private var mIndex: Int = 0
    private var mFactor: Float = 0.2F
    private var mIsLoading: Boolean = false

    private var mAsc = true

    private var mHandler: MyHandler = MyHandler(this)

    companion object {
        private class MyHandler(view: View) : Handler() {
            private var mWeakReference: WeakReference<View>? = null

            init {
                mWeakReference = WeakReference(view)
            }

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mWeakReference?.get()?.invalidate()
                sendEmptyMessageDelayed(0, 200)
//                sendEmptyMessageDelayed(0, 130)
            }
        }
    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mPaint.style = Paint.Style.FILL
        for (color in mColors) {
            mRectList.add(RectF())
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!mIsLoading) {

            //  绘制5个长柱
            //  间隔 ---1 2 3 4 5---
            //  ---12---
            var left = (mWidth - mRectWidth * 5 - mRectInterval * 4) / 2F
            val top = mHeight / 2F
            var h: Float
            for ((pos, rect) in mRectList.withIndex()) {
//                mPaint.color = UIUtils.getColor(mColors[pos])
                mPaint.color = ContextCompat.getColor(context, mColors[pos])
                h = mRectHeight * (0.3F + 0.7F * mFactor * Math.random().toFloat())
                rect.left = left
                rect.top = top - h / 2F
                rect.right = rect.left + mRectWidth
                rect.bottom = rect.top + h
                left += mRectWidth + mRectInterval
                canvas?.drawRoundRect(rect, 20F, 20F, mPaint)
            }
        } else {
            //  绘制5个圆球
            //  ---1 2 3 4 5---
            //  21234
            //  32123
            //  43212
            //  54321
            var left = (mWidth - mRoundWidth * 5 - mRoundInterval * 4) / 2F
            val top = mHeight / 2F
            var w: Float
            var h: Float
            for ((pos, rect) in mRectList.withIndex()) {
//                mPaint.color = UIUtils.getColor(mColors[pos])
                mPaint.color = ContextCompat.getColor(context, mColors[pos])
                // 0     1   2    3    4
                // 0.5  0.6 0.7  0.8  0.9
                w = mRoundWidth * (0.5F + Math.abs(pos - mIndex) / 10F)
                h = mRoundHeight * (0.5F + Math.abs(pos - mIndex) / 10F)
                rect.left = left + (mRoundWidth - w) / 2F
                rect.top = top - h / 2F
                rect.right = rect.left + w
                rect.bottom = rect.top + h
                canvas?.drawRoundRect(rect, 40F, 40F, mPaint)
                left += mRoundWidth + mRoundInterval
            }
            if (mAsc) {
                if (mIndex < 5) {
                    mIndex++
                } else {
                    mAsc = false
                    mIndex--
                }
            } else {
                if (mIndex > 0) {
                    mIndex--
                } else {
                    mAsc = true
                    mIndex++
                }
            }
//            if (++mIndex >= 5) {
//                mIndex = 0
//            }
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility != VISIBLE) {
            setAnimator(false)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        setAnimator(false)
    }

    fun setLoadingState(isLoading: Boolean) {
        this.mIsLoading = isLoading
    }

    fun startAnim() {
        setAnimator(true)
    }

    fun setCurveFactor(value: Float) {
        this.mFactor = value
    }

    private fun setAnimator(isSet: Boolean) {
        if (!isSet) {
            mHandler.removeCallbacksAndMessages(null)
            return
        }
        mHandler.removeMessages(0)
        mHandler.sendEmptyMessageDelayed(0, 80)
    }

}