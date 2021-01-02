package com.learningAndroiddeve.androidcalenderview.allchalets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.adapter.ChaletAdapter
import com.learningAndroiddeve.androidcalenderview.databinding.AllChaletsFragmentBinding
import kotlinx.android.synthetic.main.filter_bottom_sheets.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AllFragments : Fragment() {

    private lateinit var allFragmentsViewModel: AllFragmentsViewModel
    private lateinit var allChaletsFragmentBinding: AllChaletsFragmentBinding

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allChaletsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.all_chalets_fragment, container, false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = AllFragmentsViewModelFactory(application)
        allFragmentsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(AllFragmentsViewModel::class.java)


        allChaletsFragmentBinding.viewModel = allFragmentsViewModel
        allChaletsFragmentBinding.lifecycleOwner = this

        (activity as AppCompatActivity).setSupportActionBar(allChaletsFragmentBinding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        val chaletAdapter = ChaletAdapter()
        allChaletsFragmentBinding.chaletsList.itemAnimator = DefaultItemAnimator()
        allChaletsFragmentBinding.chaletsList.adapter = chaletAdapter
        subscribeUi(chaletAdapter)

        return allChaletsFragmentBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    @ExperimentalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {
                filterDialog()
            }
        }
        return true
    }


    @ExperimentalCoroutinesApi
    private fun subscribeUi(adapter: ChaletAdapter) {
        allFragmentsViewModel.allChalets.observe(viewLifecycleOwner) { allChalets ->
            adapter.submitList(allChalets)
        }
    }

    private fun filterDialog() {
        val dialogBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context),
            R.layout.filter_bottom_sheets,
                null,
                false
            )
        val dialog = context?.let {
            BottomSheetDialog(it)
        }
        dialog?.setContentView(dialogBinding.root.rootView)
        dialogBinding.root.rootView.button_filter.setOnClickListener {
            val price = dialogBinding.root.rootView.editTextText_price_from.text.toString()
            val date = dialogBinding.root.rootView.editTextText_date_from.text.toString()
            if(price.isEmpty() || date.isEmpty()){
                Toast.makeText(requireContext(), "Please Enter Valid Data", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "go fliter", Toast.LENGTH_SHORT).show()
            }
        }

        dialog?.show()
       }

}