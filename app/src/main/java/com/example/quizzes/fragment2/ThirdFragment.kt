package com.example.quizzes.fragment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizzes.R
import com.example.quizzes.basic.Const
import com.example.quizzes.databinding.FragmentDetailsBinding
import com.example.quizzes.databinding.FragmentThirdBinding
import com.example.quizzes.model3.Answer
import com.example.quizzes.model3.Question
import com.example.quizzes.viewmodel.QuizDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private val result by lazy { arguments?.getInt("result") as Int }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.aScoreTextViewScore.text = getString(R.string.result, result.toString())

        val calback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_thirdFragment_to_nav_comics)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(calback)
    }
    }
