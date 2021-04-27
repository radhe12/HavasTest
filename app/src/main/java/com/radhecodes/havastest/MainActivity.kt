package com.radhecodes.havastest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.radhecodes.havastest.databinding.ActivityMainBinding
import com.radhecodes.havastest.ui.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity(), View.OnTouchListener, View.OnDragListener {
    private val mainViewModel: MainViewModel by viewModel()
    lateinit var binding: ActivityMainBinding
    lateinit var runnable: Runnable
    private lateinit var handler: Handler
    lateinit var dm: DisplayMetrics
    var parentHeight: Int? = 0
    var dY:Float? = 0.0f
    var initialY: Float = 0.0f
    var lastAction: Int? = 0


    companion object {
        const val CARD_VIEW_TAG = "card view tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dm = resources.displayMetrics
        parentHeight = dm.heightPixels
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            try {
                binding.cardBox.rotation += 90f
                mainViewModel.getDateTime().observe(this, Observer {
                    if(it.datetime != "") {
                        binding.textView.text = getTimeString(it.datetime).toString()
                    } else {
                        Toast.makeText(this, "Error fetching time:"+ it.getErrors(), Toast.LENGTH_LONG).show()
                    }
                })
            } finally {
                handler.postDelayed(runnable, 1000)
            }
            binding.cardBox.setOnTouchListener(this)
        }

        binding.whitePanel.setOnDragListener(this)
        binding.grayLayout.setOnDragListener(this)

        startHandler()
        setContentView(binding.root)
    }

    private fun startHandler() {
        runnable.run()
    }

    private fun stopHandler() {
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopHandler()
    }

    private fun getTimeString(time: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = sdf.parse(time)
        return SimpleDateFormat("HH:mm:ss").format(date!!)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        initialY = v?.y!!
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dY = v.y.minus(event.rawY)
                lastAction = MotionEvent.ACTION_DOWN
            }
            MotionEvent.ACTION_MOVE -> {
                v.y = event.rawY + dY!!
                lastAction = MotionEvent.ACTION_MOVE
            }
            MotionEvent.ACTION_UP -> if (lastAction == MotionEvent.ACTION_DOWN) Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()

            else -> return false
        }
        return true
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event!!.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
            }
            DragEvent.ACTION_DRAG_EXITED -> {
            }
            DragEvent.ACTION_DROP -> {
                val view = event.localState as View
                val owner = view.parent as ViewGroup
                owner.removeView(view)
                val container = v as LinearLayout
                container.addView(view)
                view.visibility = View.VISIBLE
            }
            DragEvent.ACTION_DRAG_ENDED -> {
            }
            else -> {
            }
        }
        return true
    }
}