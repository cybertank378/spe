package com.spe.spetest.ui.char

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spe.spetest.R
import com.spe.spetest.databinding.CharsFragmentBinding
import com.spe.spetest.utils.Resource
import com.spe.spetest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:38 PM.
 * ===================================================
 * Package              : com.spe.spetest.ui.char.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */

@AndroidEntryPoint
class CharsFragment : Fragment(), CharsAdapter.CharsItemListener {

    private var binding: CharsFragmentBinding by autoCleared()
    private val viewModel: CharsViewModel by viewModels()
    private lateinit var adapter: CharsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CharsAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.chars.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_charsFragment_to_charsDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}