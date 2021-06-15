package com.example.quizzes.fragment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quizzes.R
import com.example.quizzes.basic.Const.Companion.RESULT_COMIC2
import com.example.quizzes.databinding.FragmentDetailsBinding
import com.example.quizzes.model3.Answer
import com.example.quizzes.model3.Question
import com.example.quizzes.viewmodel.QuizDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: QuizDetailsViewModel by viewModels()
    private var currentIndex = 0
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var answer: Answer?=null
    private  var correctPoints = 0

    private val quizId: Long by lazy { arguments?.getLong(RESULT_COMIC2) as Long}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        viewModel.getDetailsQuiz(quizId)
    }

    private fun initView() {


        viewModel.observeResults.observe(viewLifecycleOwner, Observer{
            viewModel.updateData(quizId, it)
            binding.progressBar.max = it.questions.size
            binding.progressBar.progress = currentIndex

                checkAnswer(
                    it.questions[currentIndex].answers.getOrNull(0),
                    binding.aQuestionButtonP1
                , it.questions)
                checkAnswer(
                    it.questions[currentIndex].answers.getOrNull(1),
                    binding.aQuestionButtonP2
                    , it.questions)
                checkAnswer(
                    it.questions[currentIndex].answers.getOrNull(2),
                    binding.aQuestionButtonP3
                    , it.questions)
                checkAnswer(
                    it.questions[currentIndex].answers.getOrNull(3),
                    binding.aQuestionButtonP4
                    , it.questions)

                binding.titleComics.text = it?.title
                binding.aQuestionTextViewQuestion.text = it.questions[currentIndex].text

                binding.aQuestionButtonP1.text =
                    it.questions[currentIndex].answers.getOrNull(0)?.text
                binding.aQuestionButtonP2.text =
                    it.questions[currentIndex].answers.getOrNull(1)?.text
                binding.aQuestionButtonP3.text =
                    it.questions[currentIndex].answers.getOrNull(2)?.text
                binding.aQuestionButtonP4.text =
                    it.questions[currentIndex].answers.getOrNull(3)?.text

            Timber.d("XDXD2 ${correctPoints}")
        })

}

    private fun checkAnswer(answer: Answer?, button: Button, question: List <Question>) {

        if (answer == null) {
            button.visibility = View.GONE
        } else {
            button.visibility = View.VISIBLE

        }
            button.setOnClickListener {

                if (currentIndex != question.size-1){
                    currentIndex++
                }
                else {
                    val result = (correctPoints * 100) / question.size

                    findNavController().navigate(R.id.action_detailsFragment_to_thirdFragment, bundleOf("result" to result))

                }

                if (answer?.isCorrect == 1) {
                    correctPoints += answer.isCorrect
                    binding.aQuestionTextViewQuestionNumber2.text = (correctPoints).toString()
                }
                viewModel.refreshQuestion()
            }
    }

}