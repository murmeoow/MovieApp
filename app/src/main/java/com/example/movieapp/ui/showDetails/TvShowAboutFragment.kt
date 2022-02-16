package com.example.movieapp.ui.showDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentTvShowAboutBinding
import com.example.movieapp.ui.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowAboutFragment : Fragment() {

    private lateinit var binding : FragmentTvShowAboutBinding
    private val viewModel: TvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTvShowAboutBinding.inflate(inflater,container, false)

        val args = TvShowAboutFragmentArgs.fromBundle(requireArguments())

        viewModel.getShowDetails(args.id)
        viewModel.showDetails.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful){
                binding.imageView.load(response.body()?.image?.original){
                    crossfade(true)
                    crossfade(1000)
                }
                binding.tvShowName.text = response.body()?.name
                binding.tvLanguage.text = response.body()?.language
                binding.tvGenre.text = response.body()?.genres?.joinToString()
                binding.tvRating.text = response.body()?.rating?.average.toString()
                binding.tvSummary.text = response.body()?.summary
            }else{
                Toast.makeText(requireContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

}