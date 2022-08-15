package com.udacity.shoestore.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoeListViewModel


class DetailFragment : Fragment() {
private lateinit var newShoe :Shoe
private lateinit var binding: FragmentDetailBinding

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

        shoeListViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment())
        }
        binding.SaveButton.setOnClickListener {
            if (validation()){
                newShoe= Shoe(binding.shoeNameEt.text.toString() ,binding.sizeEt.text.toString().toDouble(), binding.companyEt.text.toString() , binding.descriptionEt.text.toString())
                shoeListViewModel.addNewShoe(newShoe)
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment())
            }else{
                Toast.makeText(requireContext() , "Please fill all fields." , Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
    fun validation() : Boolean{
        if (binding.shoeNameEt.text.isEmpty()){
            return false
        }
        if (binding.sizeEt.text.isEmpty()){
            return false
        }
        if (binding.companyEt.text.isEmpty()){
            return false
        }
        if (binding.descriptionEt.text.isEmpty()){
            return false
        }
        return true
    }

}