package com.udacity.shoestore.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe


class DetailFragment : Fragment() {
    private lateinit var newShoe: Shoe
    private lateinit var binding: FragmentDetailBinding
    val shoeListViewModel by activityViewModels<MainActivityViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )

        binding.shoeSaver = this
        binding.shoeViewModel = shoeListViewModel
        binding.lifecycleOwner = this


//        shoeListViewModel =
//            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)


        return binding.root
    }

    fun validation(): Boolean {
        if (binding.shoeNameEt.text.isEmpty()) {
            return false
        }
        if (binding.sizeEt.text.isEmpty()) {
            return false
        }
        if (binding.companyEt.text.isEmpty()) {
            return false
        }
        if (binding.descriptionEt.text.isEmpty()) {
            return false
        }
        return true
    }

    fun saveData() {
        if (validation()) {

            var shoeName = shoeListViewModel.shoeName.value.toString()
            var shoeCompany = shoeListViewModel.shoeCompany.value.toString()
            var shoeSize = shoeListViewModel.shoeSize.value.toString().toDouble()
            var shoeDescription = shoeListViewModel.shoeDescription.value.toString()

            newShoe = Shoe(shoeName, shoeSize, shoeCompany, shoeDescription)
            shoeListViewModel.addNewShoe(newShoe)
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment())
        } else {
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    fun cancel() {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment())
    }

}