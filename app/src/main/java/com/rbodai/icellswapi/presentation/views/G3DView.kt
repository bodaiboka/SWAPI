package com.rbodai.icellswapi.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class G3DView(context: Context) : View(context) {

    init {
        loadModel(path = context.getExternalFilesDir(null)?.path!!)
    }

    external fun openModel(path: String): Long
    external fun getmodelinfo(model: Long): String?
    external fun getmodelfaces(model: Long): Int
    external fun loadModel(model: Long)
    external fun initmatrices()
    external fun transRY(theta: Float, tx: Float, ty: Float, s: Float)
    external fun getpoly(model: Long, face: Int): FloatArray?
    external fun getpolylines(model: Long, face: Int): FloatArray?

    var model: Long = 0L
    var paint: Paint? = null
    var theta = 0f
    var centerX: Float = 0f
    var centerY: Float = 0f

    fun loadModel(path: String) {
        if (model == 0L) {
            model = openModel(path + "/tie.obj")
            loadModel(model)
        }
        initmatrices()
        paint = Paint()
        paint!!.color = Color.argb(255, 0, 144, 206)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w.toFloat() / 2
        centerY = h.toFloat() / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.argb(0,0, 0, 0))
        val n = getmodelfaces(model)
        for (i in 0 until n) {
            val vertices = getpolylines(model, i)
            canvas.drawLines(vertices!!, paint!!)
        }
        transRY(theta, centerX, centerY, 90f)
        theta += 0.5f
        invalidate()
    }

}