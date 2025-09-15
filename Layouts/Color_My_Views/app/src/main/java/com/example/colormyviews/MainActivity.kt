package com.example.colormyviews

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var button_text_one: View;
    private lateinit var button_text_two: View;
    private lateinit var button_text_three: View;
    private lateinit var button_text_four: View;
    private lateinit var button_text_five: View;

    private lateinit var red_button: Button;

    private lateinit var yellow_button: Button;

    private lateinit var green_button: Button;

    private lateinit var constraint_layout: ConstraintLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button_text_one = findViewById<View>(R.id.textView);
        button_text_two = findViewById<View>(R.id.textView2);
        button_text_three = findViewById<View>(R.id.textView3);
        button_text_four = findViewById<View>(R.id.textView4);
        button_text_five = findViewById<View>(R.id.textView5);
        constraint_layout = findViewById<ConstraintLayout>(R.id.main);
        red_button = findViewById<Button>(R.id.red_button);
        yellow_button = findViewById<Button>(R.id.yellow_button);
        green_button = findViewById<Button>(R.id.green_button);

        setListeners();
    }

    private fun setListeners(): Unit {
        val views: List<View> = listOf<View>(
            button_text_one,
            button_text_two,
            button_text_three,
            button_text_four,
            button_text_five,
            constraint_layout,
            red_button,
            yellow_button,
            green_button
        );

        for(view in views){
            view.setOnClickListener { makeItColored(it) };
        }
    }

    private fun makeItColored(view:View):Unit{
        when(view.id){
            R.id.textView -> view.setBackgroundColor(Color.DKGRAY)
            R.id.textView2 -> view.setBackgroundColor(Color.GRAY)

            R.id.textView3 -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.textView4 -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.textView5 -> view.setBackgroundResource(android.R.color.holo_green_light)

            R.id.red_button -> button_text_three.setBackgroundColor(Color.RED)
            R.id.yellow_button -> button_text_four.setBackgroundResource(R.color.yellow)
            R.id.green_button -> button_text_five.setBackgroundResource(R.color.green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}