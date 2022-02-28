package com.ciandt.androidviewlifecycle.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ciandt.androidviewlifecycle.R

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var labelPaint: Paint
    private lateinit var labelText: String


    init {
        init(context,attrs)
    }

    private fun init(context: Context,
                     attrs: AttributeSet?){
        Log.d(TAG, "init - ")
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView)
        labelText = typedArray.getString(R.styleable.CustomView_text).toString()
        labelPaint = Paint()
        with(labelPaint)
        {
            textSize = 60F
            color = Color.WHITE
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isSaveEnabled = true
        }
    }

    fun setText(text: String)
    {
        this.labelText = text
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        Log.d(TAG,"inside onDraw -")
        drawLabel(canvas)
    }

    override fun onAttachedToWindow() {
        Log.d(TAG,"inside onAttachedToWindow -")
        super.onAttachedToWindow()
    }

    private fun drawLabel(canvas : Canvas)
    {
        val x = paddingLeft
        val bounds = Rect()
        labelPaint.getTextBounds(labelText, 0,labelText.length,bounds)
        val y = paddingTop + bounds.height()
        canvas.drawText(labelText, x.toFloat(), y.toFloat(), labelPaint)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.d(TAG, "inside layout -")
        super.layout(l, t, r, b)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "inside onLayout - $changed left : $left")
        if (left == 0) {
            layout(50, 100, right, bottom)
        } else {
            super.onLayout(changed, left, top, right, bottom)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(TAG, "inside onMeasure -$widthMeasureSpec : $heightMeasureSpec")
        setMeasuredDimension(700, 300)
    }

    companion object {
        private const val TAG = "CustomView"
    }
}