package ru.alfacampus.homeworkproject.featureSplashScreen.presentation.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.featureSplashScreen.databinding.CustomViewScreenBinding
import ru.alfacampus.homeworkproject.featureSplashScreen.presentation.customviews.TrianglesView
import ru.alfacampus.homeworkproject.featureSplashScreen.presentation.helpers.ShaderType
import ru.alfacampus.homeworkproject.navigation.NavigationFlow
import ru.alfacampus.homeworkproject.navigation.ToFlowNavigatable


class CustomViewScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CustomViewScreenBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        val animator1 = ObjectAnimator.ofFloat(binding.trianglesView, View.ALPHA, 0f, 1f).apply {
            duration = LONG_DURATION
            interpolator = AccelerateDecelerateInterpolator()
        }
        val animator2 = ObjectAnimator.ofFloat(binding.trianglesView, View.SCALE_X, 1f, 3f).apply {
            duration = SHORT_DURATION
            interpolator = AccelerateDecelerateInterpolator()
        }
        val animator3 = ObjectAnimator.ofFloat(binding.trianglesView, View.SCALE_Y, 1f, 3f).apply {
            duration = SHORT_DURATION
            interpolator = AccelerateDecelerateInterpolator()
            doOnEnd {
                (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.CharactersFlow)
            }
        }
        val animator4 = ObjectAnimator.ofFloat(binding.trianglesView, View.SCALE_X, 5f, 1f).apply {
            duration = MIDDLE_DURATION
            interpolator = AccelerateDecelerateInterpolator()
        }
        val animator5 = ObjectAnimator.ofFloat(binding.trianglesView, View.SCALE_Y, 5f, 1f).apply {
            duration = MIDDLE_DURATION
            interpolator = AccelerateDecelerateInterpolator()
        }

        val animatorSet = AnimatorSet()
        val animatorSet2 = AnimatorSet()

        animatorSet.play(animator4).with(animator5)
        animatorSet.start()

        binding.trianglesView.setOnClickListener {
            (it as TrianglesView).setShaderType(ShaderType.MarvelCharacters)

            animatorSet2.play(animator3).with(animator2).after(animator1)
            animatorSet2.start()
        }
    }.root

    companion object {
        const val SHORT_DURATION: Long = 800
        const val MIDDLE_DURATION: Long = 1000
        const val LONG_DURATION: Long = 1500

    }
}
