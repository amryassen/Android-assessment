package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MainActivity.Companion.arrow_drawing
import com.example.myapplication.MainActivity.Companion.circle_drawing
import com.example.myapplication.MainActivity.Companion.paint
import com.example.myapplication.MainActivity.Companion.path
import com.example.myapplication.MainActivity.Companion.pen_drawing
import com.example.myapplication.MainActivity.Companion.rect_drawing


class Paint : View {
    companion object {
        var pathlist = ArrayList<android.graphics.Path>()
        var pathlist2 = ArrayList<android.graphics.Path>()
        var circlePoints = ArrayList<Point>()
        var drawrect = ArrayList<Boolean>()
        var colorlist = ArrayList<Int>()
        var colorlist2 = ArrayList<Int>()
        var currentcolor = Color.BLACK
    }

    var mX: Float = 0.0f
    var mY: Float = 0.0f
    var params: ViewGroup.LayoutParams? = null
    var beginX: Float? = null
    var beginY: Float? = null
    var endX: Float? = null
    var endY: Float? = null
    var drawRectangle = false
    private val mCanvas: Canvas? = null

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        paint.isAntiAlias = true
        paint.color = currentcolor
        paint.strokeWidth = 8f
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mX = x
                mY = y
                if (pen_drawing || arrow_drawing) {
                    path.moveTo(x, y)
                }
                beginX = x
                beginY = y
                endX = x
                endY = y
                invalidate();
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                mX = x
                mY = y
                if (pen_drawing) {
                    path.lineTo(x, y)
                }
                endX = x
                endY = y
                invalidate();
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (circle_drawing) {
                    path.addOval(beginX!!, beginY!!, endX!!, endY!!, Path.Direction.CW)
                }
                if (rect_drawing) {
                    path.addRect(beginX!!, beginY!!, endX!!, endY!!, Path.Direction.CW)
                }
                if (arrow_drawing) {
                    path.lineTo(mX, mY)
                    val deltaX: Float = endX!! - beginX!!
                    val deltaY: Float = endY!! - beginY!!
                    val frac = 0.1.toFloat()

                    val point1: Float = beginX!! + ((1 - frac) * deltaX + frac * deltaY)
                    val point2: Float = beginY!! + ((1 - frac) * deltaY - frac * deltaX)

                    val point3: Float = endX!!
                    val point4: Float = endY!!

                    val point5: Float = beginX!! + ((1 - frac) * deltaX - frac * deltaY)
                    val point6: Float = beginY!! + ((1 - frac) * deltaY + frac * deltaX)

                    path.moveTo(point1, point2)
                    path.lineTo(point3, point4)
                    path.lineTo(point5, point6)
                    path.lineTo(point1, point2)
                }
                colorlist.add(currentcolor)
                pathlist.add(path)
                endX = event.getX();
                endY = event.getY();
                invalidate();
            }
            else -> return false
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {
        for (i in pathlist.indices) {
            Log.d("here", i.toString())
            paint.setColor(colorlist[i])
            canvas.drawPath(pathlist[i], paint)
        }
    }
}