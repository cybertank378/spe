package com.spe.spetest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.spe.spetest.data.entities.Chars
import com.spe.spetest.databinding.CharsDetailFragmentBinding
import com.spe.spetest.utils.Resource
import com.spe.spetest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 2:03 PM.
 * ===================================================
 * Package              : com.spe.spetest.ui.detail.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */

@AndroidEntryPoint
class CharsDetailFragment : Fragment() {

    private var binding: CharsDetailFragmentBinding by autoCleared()
    private val viewModel: CharsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(chars: Chars) {
        binding.name.text = chars.name
        binding.species.text = chars.species
        binding.status.text = chars.status
        binding.gender.text = chars.gender
        Glide.with(binding.root)
            .load(chars.image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}