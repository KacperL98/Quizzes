package com.example.quizzes.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.quizzes.R
import com.example.quizzes.adapter.AnswerAdapter
import com.example.quizzes.basic.Const.Companion.RESULT_LIST
import com.example.quizzes.databinding.FragmentDetailsQuizBinding
import com.example.quizzes.extension.gone
import com.example.quizzes.extension.show
import com.example.quizzes.model.Answer
import com.example.quizzes.model.Question
import com.example.quizzes.model.QuizDetailsDataModel
import com.example.quizzes.viewmodel.QuizDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.checkDuration
import timber.log.Timber

@AndroidEntryPoint
class DetailsQuizFragment : Fragment() {

    private val viewModel: QuizDetailsViewModel by viewModels()
    private var currentIndex = 0
    private var _binding: FragmentDetailsQuizBinding? = null
    private val binding get() = _binding!!
    private var correctPoints = 0
    private var amountQuestions = 0
    private val quizId: Long by lazy { arguments?.getLong(RESULT_LIST) as Long }

    private val adapter: AnswerAdapter by lazy {
        AnswerAdapter { answer ->
            addPoint(answer)
            if (currentIndex != amountQuestions - 1) {
                currentIndex++
            } else {
                openScoreFragment()
            }
            viewModel.refreshQuestion()
        }
    }

    private fun addPoint(answer: Answer) {
        if (answer.isCorrect != 0) {
            correctPoints += answer.isCorrect
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.rvAnswers.adapter = adapter
        viewModel.getDetailsQuiz(quizId)
    }

    private fun setObservers() {
        viewModel.observeResults.observe(viewLifecycleOwner) {
            if (it.questions.isNotEmpty()) {
                setView(it)
            } else {
                showError()
            }
        }
    }

    private fun setView(quizDetailsData: QuizDetailsDataModel) {
        with(binding) {
            amountQuestions = quizDetailsData.questions.size
            adapter.submitList(quizDetailsData.questions[currentIndex].answers)
            progressBarQuiz.max = amountQuestions
            progressBarQuiz.progress = currentIndex
            titleQuizDetails.text = quizDetailsData.title
            questionsQuiz.text = quizDetailsData.questions[currentIndex].text
            amountQuestionsQuiz.text = quizDetailsData.questions.size.toString()

            val currentQuestion = quizDetailsData.questions[currentIndex]
            val imgUrl = currentQuestion.image.url.replace(
                "https://filerepo.grupawp.pl/",
                "http://i.wpimg.pl/" +
                        "${currentQuestion.image.width}x${currentQuestion.image.height}/filerepo.grupawp.pl/"
            )
            Glide.with(requireContext())
                .load(imgUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.img_title)
                .into(imgToDetailsQuiz)
        }
        if (currentIndex < quizDetailsData.questions.size && currentIndex >= 0) {
            binding.amountQuestionsQuiz.text = "${currentIndex}/${quizDetailsData.questions.size}"
        }
    }

    private fun openScoreFragment() {
        val result = (correctPoints * 100) / amountQuestions
        findNavController().navigate(
            R.id.action_detailsQuizFragment_to_scoreQuizFragment,
            bundleOf("result" to result)
        )
    }

    private fun showError() {
        binding.groupData.gone()
        binding.imageViewLottie.show()
    }
}