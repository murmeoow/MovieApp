package com.example.movieapp.ui.showsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentTvShowsBinding
import com.example.movieapp.ui.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    private val viewModel: TvShowsViewModel by viewModels()
    private lateinit var binding: FragmentTvShowsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTvShowsBinding.inflate(inflater,container,  false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TvShowsAdapter { showId ->
            viewModel.onShowClicked(showId)
        }
        binding.recyclerView.adapter = adapter

        viewModel.getTvShows()

        viewModel.allShows.observe(viewLifecycleOwner, { response ->
            Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_SHORT).show()
            if (response.isSuccessful) {
                adapter.submitList(response.body())
            }else{
                Toast.makeText(requireContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.showClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(TvShowsFragmentDirections.actionTvShowsFragmentToTvShowAboutFragment(it))
        })

        return binding.root
    }


}