package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemViewBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_list.view.*


class ShoeListFragment : Fragment() {

    private lateinit var shoeList: List<Shoe>
    lateinit var binding: FragmentShoeListBinding
    lateinit var bindingShoe: ShoeItemViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.clicker = this

        val shoeListViewModel by activityViewModels<MainActivityViewModel>()

        shoeList = shoeListViewModel.getShoeList()!!

        for (shoe in shoeList!!) {
            bindingShoe = ShoeItemViewBinding.inflate(inflater)
            bindingShoe.shoe = shoe
            binding.root.shoe_list_container.addView(bindingShoe.root)
        }

        shoeListViewModel.addedShoe.observe(viewLifecycleOwner, Observer {
            if (it) {
                shoeList = shoeListViewModel.getShoeList()!!
                shoeListViewModel.finishAdding()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }


    fun addShoe() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment())
    }

}