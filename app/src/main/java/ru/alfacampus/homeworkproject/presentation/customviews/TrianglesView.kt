package ru.alfacampus.homeworkproject.presentation.customviews

import android.content.Context
import android.graphics.*
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.presentation.helpers.ShaderType
import kotlin.math.pow
import kotlin.math.sqrt


private const val TAG = "TrianglesView"
private const val PAINT_BRUSH_STROKE_WIDTH = 1F
private const val DEFAULT_TRIANGLE_SIDE = 70F
private const val DEFAULT_MID_TRIANGLE_SIDE = 130F
private const val DEFAULT_BIG_TRIANGLE_SIDE = 180F
private const val DEFAULT_DISTANCE = 10F


class TrianglesView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.trianglesViewStyle,
    defStyleRs: Int = R.style.TrianglesViewStyle
) : View(context, attrs, defStyleAttr, defStyleRs) {

    private lateinit var trianglesShader: ShaderType

    private var triangleSideF = 0F
    private var triangleSide = 0F
    private var midTriangleSideF = 0F
    private var midTriangleSide = 0F
    private var bigTriangleSideF = 0F
    private var bigTriangleSide = 0F

    private var distanceF = 0F
    private var distance = 0F

    private var midTriangleHeight = 0F
    private var bigTriangleHeight = 0F

    private val bitmapSource = BitmapFactory.decodeResource(resources, R.drawable.font)
    private val bitmapSource1 = BitmapFactory.decodeResource(resources, R.drawable.cap_america)
    private val bitmapSource2 = BitmapFactory.decodeResource(resources, R.drawable.hulk)
    private val bitmapSource3 = BitmapFactory.decodeResource(resources, R.drawable.wolverine)
    private val bitmapSource4 = BitmapFactory.decodeResource(resources, R.drawable.thor)
    private val bitmapSource5 = BitmapFactory.decodeResource(resources, R.drawable.spider)
    private val bitmapSource6 = BitmapFactory.decodeResource(resources, R.drawable.ironman)
    private val bitmapSource7 = BitmapFactory.decodeResource(resources, R.drawable.storm)

    private val paintFilledShaderBrush: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush1: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush2: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush3: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource3, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush4: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource4, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush5: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource5, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush6: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource6, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }
    private val paintFilledShaderBrush7: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = context.toDp(PAINT_BRUSH_STROKE_WIDTH)
            shader = BitmapShader(bitmapSource7, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
    }

    private lateinit var path1: Path
    private lateinit var path2: Path
    private lateinit var path3: Path
    private lateinit var path4: Path

    init {
        attrs?.let { initAttrs(it, defStyleAttr, defStyleRs) }
    }

    private fun initAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRs: Int) {
        context.theme
            .obtainStyledAttributes(attrs, R.styleable.TrianglesView, defStyleAttr, defStyleRs)
            .apply {
                try {
                    val shader = getInt(
                        R.styleable.TrianglesView_trianglesShader,
                        ShaderType.Solid.ordinal
                    )
                    trianglesShader = ShaderType.values()[shader]

                    triangleSideF = getFloat(R.styleable.TrianglesView_smallTriangleSide, DEFAULT_TRIANGLE_SIDE)
                    midTriangleSideF = getFloat(R.styleable.TrianglesView_middleTriangleSide, DEFAULT_MID_TRIANGLE_SIDE)
                    bigTriangleSideF = getFloat(R.styleable.TrianglesView_bigTriangleSide, DEFAULT_BIG_TRIANGLE_SIDE)
                    distanceF = getFloat(R.styleable.TrianglesView_auxiliaryDistance, DEFAULT_DISTANCE)

                    triangleSide = context.toDp(triangleSideF)
                    midTriangleSide = context.toDp(midTriangleSideF)
                    bigTriangleSide = context.toDp(bigTriangleSideF)
                    distance = context.toDp(distanceF)

                    midTriangleHeight = context.toDp(getHeight(midTriangleSideF.toDouble()))
                    bigTriangleHeight = context.toDp(getHeight(bigTriangleSideF.toDouble()))

                    path1 = configurePathTriangle(triangleSideF, Path())
                    path2 = configurePathInvertedTriangle(midTriangleSideF, Path())
                    path3 = configurePathTriangle(bigTriangleSideF, Path())
                    path4 = configurePathInvertedTriangle(triangleSideF, Path())

                } finally {
                    recycle()
                }
            }
    }

    fun setShaderType(shaderType: ShaderType) {
        trianglesShader = shaderType
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure")

        val desiredWidth = 1.5F * triangleSide + 0.5F * midTriangleSide + bigTriangleSide
        val desiredHeight = midTriangleHeight + bigTriangleHeight + distance
        setMeasuredDimension(
            resolveSize(desiredWidth.toInt(), widthMeasureSpec) + paddingLeft + paddingRight,
            resolveSize(desiredHeight.toInt(), heightMeasureSpec) + paddingTop + paddingBottom
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d(TAG, "onDraw")
        super.onDraw(canvas)
        canvas ?: return

        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        canvas.translate(0F, 2 * distance)

        when (trianglesShader) {
            ShaderType.Solid -> {
                canvas.drawPath(path1, paintFilledShaderBrush)
                canvas.translate(0.5F * triangleSide, -distance)

                canvas.drawPath(path2, paintFilledShaderBrush)
                canvas.translate(0.5F * midTriangleSide - distance, -distance)

                canvas.drawPath(path3, paintFilledShaderBrush)
                canvas.translate(
                    0.75F * bigTriangleSide - 0.5F * distance,
                    0.5F * midTriangleHeight
                )

                canvas.drawPath(path4, paintFilledShaderBrush)
                canvas.translate(0.5F * triangleSide, triangleSide / 3 - distance)

                canvas.drawPath(path1, paintFilledShaderBrush)
                canvas.translate(-2 * triangleSide, 0.5F * bigTriangleHeight + 1.5F * distance)

                canvas.drawPath(path2, paintFilledShaderBrush)
                canvas.translate(-0.5F * triangleSide, distance)

                canvas.drawPath(path1, paintFilledShaderBrush)
            }
            ShaderType.MarvelCharacters -> {
                canvas.drawPath(path1, paintFilledShaderBrush1)
                canvas.translate(0.5F * triangleSide, -distance)

                canvas.drawPath(path2, paintFilledShaderBrush2)
                canvas.translate(0.5F * midTriangleSide - distance, -distance)

                canvas.drawPath(path3, paintFilledShaderBrush3)
                canvas.translate(
                    0.75F * bigTriangleSide - 0.5F * distance,
                    0.5F * midTriangleHeight
                )

                canvas.drawPath(path4, paintFilledShaderBrush4)
                canvas.translate(0.5F * triangleSide, triangleSide / 3 - distance)

                canvas.drawPath(path1, paintFilledShaderBrush5)
                canvas.translate(-2 * triangleSide, 0.5F * bigTriangleHeight + 1.5F * distance)

                canvas.drawPath(path2, paintFilledShaderBrush6)
                canvas.translate(-0.5F * triangleSide, distance)

                canvas.drawPath(path1, paintFilledShaderBrush7)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow")
    }

    override fun onSaveInstanceState(): Parcelable? {
        val state = super.onSaveInstanceState()
        return SavedState(trianglesShader, state)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        state as SavedState
        super.onRestoreInstanceState(state.superState)
        setShaderType(state.shaderType)
    }

    private fun Context.toDp(value: Float): Float {
        return resources.displayMetrics.density * value
    }

    private fun getHeight(triangleSide: Double): Float {
        return sqrt(
            (triangleSide.pow(2.0) - (triangleSide / 2.0).pow(2.0))
        ).toFloat()
    }

    private fun configurePathInvertedTriangle(triangleSide: Float, path: Path): Path {
        path.reset()
        path.lineTo(
            context.toDp((triangleSide / 2F)),
            context.toDp(getHeight(triangleSide.toDouble()))
        )
        path.lineTo(
            context.toDp(triangleSide),
            context.toDp(0F)
        )
        path.lineTo(
            context.toDp(0F),
            context.toDp(0F)
        )
        path.lineTo(
            context.toDp((triangleSide / 2F)),
            context.toDp(getHeight(triangleSide.toDouble()))
        )
        path.close()
        return path
    }

    private fun configurePathTriangle(triangleSide: Float, path: Path): Path {
        path.reset()
        path.lineTo(
            context.toDp(triangleSide / 2F),
            context.toDp(0F)
        )
        path.lineTo(
            context.toDp(triangleSide),
            context.toDp(getHeight(triangleSide.toDouble()))
        )
        path.lineTo(
            context.toDp(0F),
            context.toDp(getHeight(triangleSide.toDouble()))
        )
        path.lineTo(
            context.toDp(triangleSide / 2F),
            context.toDp(0F)
        )
        path.close()
        return path
    }

    @Parcelize
    class SavedState(
        val shaderType: ShaderType,
        @IgnoredOnParcel val source: Parcelable? = null
    ) : BaseSavedState(source)
}
