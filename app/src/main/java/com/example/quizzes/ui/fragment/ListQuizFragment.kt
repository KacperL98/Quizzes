package com.example.quizzes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quizzes.R
import com.example.quizzes.adapter.QuizAdapter
import com.example.quizzes.basic.Const
import com.example.quizzes.databinding.FragmentListQuizBinding
import com.example.quizzes.extension.gone
import com.example.quizzes.extension.show
import com.example.quizzes.viewmodel.ListQuizViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListQuizFragment : Fragment() {
    private val viewModel: ListQuizViewModel by viewModels()
    private var _binding: FragmentListQuizBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        QuizAdapter {
            findNavController().navigate(
                R.id.action_listQuizFragment_to_detailsQuizFragment, bundleOf(
                    Const.RESULT_LIST to it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQuizList()
        binding.listOfQuizRv.adapter = adapter
        setObservers()
    }

    private fun setObservers() {
        viewModel.observeViewStatus.observe(viewLifecycleOwner, Observer { it ->
            when (it) {
                ListQuizViewModel.ViewStatus.Error -> {
                    binding.imgError.show()
                }
                ListQuizViewModel.ViewStatus.Loading -> {
                    binding.progressBar.show()
                }
                is ListQuizViewModel.ViewStatus.Success -> {
                    it.quizzes?.let {
                        adapter.submitList(it)
                        binding.progressBar.gone()
                        binding.imgError.gone()
                        binding.infoTextConnection.gone()
                    }
                }
            }
        })
    }
}

