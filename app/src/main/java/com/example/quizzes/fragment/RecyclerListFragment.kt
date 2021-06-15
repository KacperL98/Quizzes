package com.example.quizzes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizzes.R
import com.example.quizzes.adapter.QuizzesAdapter
import com.example.quizzes.basic.Const
import com.example.quizzes.databinding.FragmentRecyclerListBinding
import com.example.quizzes.repository.QuizzesRepository
import com.example.quizzes.viewmodel.QuizzesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class RecyclerListFragment : Fragment() {
    private val viewModel: QuizzesViewModel by viewModels()
    private var _binding: FragmentRecyclerListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        QuizzesAdapter {
            findNavController().navigate(
                R.id.action_nav_comics_to_detailsFragment, bundleOf(
                    Const.RESULT_COMIC2 to it
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listOfHeroesRV.adapter = adapter
        setObservers()
    }

    private fun setObservers() {
        viewModel.getPost()
        viewModel.observeResults.observe(viewLifecycleOwner, Observer {
            if (it != null)
                adapter.submitList(it)
        })
    }

}
