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
    external fun transRY(theta: Float)
    external fun getpoly(model: Long, face: Int): FloatArray?
    external fun getpolylines(model: Long, face: Int): FloatArray?

    var model: Long = 0L
    var paint: Paint? = null
    var theta = 0f

    fun loadModel(path: String) {
        if (model == 0L) {
            model = openModel(path + "/tie.obj")
            loadModel(model)
        }
        initmatrices()
        paint = Paint()
        paint!!.color = Color.argb(255, 255, 255, 255)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.rgb(0, 0, 0))
        val n = getmodelfaces(model)
        for (i in 0 until n) {
            //val info = getmodelinfo(model)
            val vertices = getpolylines(model, i)
            canvas.drawLines(vertices!!, paint!!)
        }
        transRY(theta)
        theta += 1f
        invalidate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

}