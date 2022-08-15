package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoeListViewModel
import kotlinx.android.synthetic.main.shoe_item_view.view.*


class ShoeListFragment : Fragment() {

    private lateinit var root: LinearLayout
    private lateinit var shoeList: List<Shoe>
//    private lateinit var shoeView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment())
        }

        shoeListViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        shoeList = shoeListViewModel.getShoeList()!!
        shoeListViewModel.addedShoe.observe(viewLifecycleOwner, Observer {
            if (it) {
                shoeList = shoeListViewModel.getShoeList()!!
                shoeListViewModel.finishAdding()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        root = view.findViewById(R.id.shoe_list_container)

        for (shoe in shoeList!!) {
            var view = layoutInflater.inflate(R.layout.shoe_item_view, null)
            view.shoe_name.text = shoe.name
            view.shoe_size.text = shoe.size.toString()
            view.shoe_company.text = shoe.company
            view.shoe_description.text = shoe.description
            if (view.parent != null) {
                (view.parent as ViewGroup).removeView(view)
            }
            root.addView(view)
        }
    }

}