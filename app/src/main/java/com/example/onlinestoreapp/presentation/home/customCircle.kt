package com.example.onlinestoreapp.presentation.home

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min
import kotlin.random.Random

class WheelOfFortuneView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private val numSegments = 2

    // Number of segments in the wheel
    private val segmentAngles = FloatArray(numSegments)
    private var currentAngle = 0f // Current angle of the wheel
    private var targetAngle = 0f // Target angle for spinning
    private var isSpinning = false // Flag to indicate if spinning is in progress
    private var spinAnimator: ValueAnimator? = null
    private var onSegmentChangeListener: OnSegmentChangeListener? = null
    private val trianglePath = Path()
    init {
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val centerX = width / 2
        val centerY = height / 2
        val radius = min(width, height) / 2 * 0.9f

        // Draw segments
        val angle = 360f / numSegments
        for (i in 0 until numSegments) {
            paint.color = colors[i % colors.size]
            rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            canvas.drawArc(rectF, i * angle + currentAngle, angle, true, paint)
            segmentAngles[i] = i * angle + currentAngle
        }
        val triangleHeight = 50f
        val triangleBase = 100f
        trianglePath.reset()
        trianglePath.moveTo(centerX - triangleBase / 2, centerY + radius + triangleHeight)
        trianglePath.lineTo(centerX + triangleBase / 2, centerY + radius + triangleHeight)
        trianglePath.lineTo(centerX, centerY + radius)
        trianglePath.lineTo(centerX - triangleBase / 2, centerY + radius + triangleHeight)
        trianglePath.close()
        paint.color = Color.BLACK
        canvas.drawPath(trianglePath, paint)
    }

    // Start spinning animation
    fun spin() {
        if (!isSpinning) {
            val random = Random.nextInt(0,3)
            targetAngle = (Math.random() * 360).toFloat()  *  random// Spin randomly up to 5 times
            spinAnimator?.cancel()
            spinAnimator = ValueAnimator.ofFloat(0f, targetAngle).apply {
                duration = random * 500L // Duration of spinning animation in milliseconds
                addUpdateListener { valueAnimator ->
                    currentAngle = valueAnimator.animatedValue as Float
                    invalidate()
                }
                addListener(object : android.animation.Animator.AnimatorListener {
                    override fun onAnimationStart(animation: android.animation.Animator) {}
                    override fun onAnimationEnd(animation: android.animation.Animator) {
                        isSpinning = false
                        val segmentIndex = ((currentAngle + 360) % 360 / (360f / numSegments)).toInt()
                        onSegmentChangeListener?.onSegmentChange(segmentIndex)
                    }
                    override fun onAnimationCancel(animation: android.animation.Animator) {}
                    override fun onAnimationRepeat(animation: android.animation.Animator) {}
                })
                start()
            }
            isSpinning = true
        }
    }

    fun setOnSegmentChangeListener(listener: OnSegmentChangeListener) {
        this.onSegmentChangeListener = listener
    }

    interface OnSegmentChangeListener {
        fun onSegmentChange(segmentIndex: Int)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isSpinning = false
        spinAnimator?.cancel()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        currentAngle = 0f
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        post {
            spin() // Auto-spin when attached to window
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            post {
                spin() // Auto-spin when visibility changes to VISIBLE
            }
        }
    }
}