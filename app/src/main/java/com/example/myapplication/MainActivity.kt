package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ActionMenuView
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.myapplication.Paint.Companion.currentcolor


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    companion object {
        var path = Path()
        var paint = Paint()
        var pen_drawing: Boolean = false
        var arrow_drawing: Boolean = false
        var circle_drawing: Boolean = false
        var rect_drawing: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val color_button = findViewById<ImageButton>(R.id.colorMenu_Button)
        val arrow_button = findViewById<ImageButton>(R.id.arrow_Button)
        val pen_button = findViewById<ImageButton>(R.id.pen_Button)
        val rect_button = findViewById<ImageButton>(R.id.square_Button)
        val circle_button = findViewById<ImageButton>(R.id.circle_Button)
        arrow_button.setOnClickListener {
            arrow_drawing = true
            pen_drawing = false
            circle_drawing = false
            rect_drawing = false
            path = Path()
        }
        pen_button.setOnClickListener {
            arrow_drawing = false
            pen_drawing = true
            circle_drawing = false
            rect_drawing = false
            path = Path()


        }
        rect_button.setOnClickListener {
            arrow_drawing = false
            pen_drawing = false
            circle_drawing = false
            rect_drawing = true
            path = Path()

        }
        circle_button.setOnClickListener {
            arrow_drawing = false
            pen_drawing = false
            circle_drawing = true
            rect_drawing = false
            path = Path()

        }

        color_button.setOnClickListener {

            val popupView: View = layoutInflater.inflate(R.layout.color_layout, null);
            //  inflate(R.layout.color_layout, null)
            val popupWindow = PopupWindow(
                popupView,
                ActionMenuView.LayoutParams.WRAP_CONTENT,
                ActionMenuView.LayoutParams.WRAP_CONTENT, true
            )

            popupWindow.isOutsideTouchable = true
            popupWindow.isFocusable = true


            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.gray))

            val parent: View = it.getRootView()
            val red_color_button = popupView.findViewById<ImageButton>(R.id.red_color)
            val blue_color_button = popupView.findViewById<ImageButton>(R.id.blue_color)
            val black_color_button = popupView.findViewById<ImageButton>(R.id.black_color)
            val green_color_button = popupView.findViewById<ImageButton>(R.id.green_color)
            red_color_button.setOnClickListener {
                paint.setColor(Color.RED)
                currentcolor = Color.RED
                path = Path()

            }
            blue_color_button.setOnClickListener {
                paint.setColor(Color.BLUE)
                currentcolor = Color.BLUE
                path = Path()

            }
            black_color_button.setOnClickListener {
                paint.setColor(Color.BLACK)
                currentcolor = Color.BLACK
                path = Path()
            }
            green_color_button.setOnClickListener {
                paint.setColor(Color.GREEN)
                currentcolor = Color.GREEN
                path = Path()
            }
            popupWindow.showAsDropDown(it, -420, 40)

        }


    }

}