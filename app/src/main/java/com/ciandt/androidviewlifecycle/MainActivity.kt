package com.ciandt.androidviewlifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ciandt.androidviewlifecycle.views.CustomView

class MainActivity : AppCompatActivity() {

    private lateinit var mainContainer: ViewGroup
    private lateinit var customView: CustomView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainContainer = findViewById(R.id.mainContainer)
        customView = findViewById(R.id.customView)
        customView.setText(this.application.getString(R.string.app_name))
    }

    fun updateView(view: View) {
        if (view.id === R.id.customView) {
            Log.d("CustomView", "=== REMOVE VIEW ===")
            mainContainer.removeAllViews()
        } else {
            Log.d("CustomView", "=== UPDATE VIEW ===")
            customView.setText("Child Updated : " + System.currentTimeMillis())
        }
    }
}