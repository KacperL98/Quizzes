package com.example.quizzes.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.quizzes.R
import com.example.quizzes.adapter.QuizAdapter
import com.example.quizzes.basic.Const
import com.example.quizzes.databinding.FragmentScoreQuizBinding
import com.example.quizzes.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ScoreQuizFragment : Fragment() {

    private var _binding: FragmentScoreQuizBinding? = null
    private val binding get() = _binding!!
    private val result by lazy { arguments?.getInt("result") as Int }
    private val quizId by lazy { arguments?.getLong(Const.RESULT_LIST) as Long }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        binding.txtResult.text = getString(R.string.result, result.toString())
    }

    private fun onBackPressed() {
        binding.btnBackListQuiz.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSolveAgain.setOnClickListener {
            findNavController().navigate(
                R.id.action_scoreQuizFragment_to_detailsQuizFragment, bundleOf(
                    Const.RESULT_LIST to quizId
                )
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}