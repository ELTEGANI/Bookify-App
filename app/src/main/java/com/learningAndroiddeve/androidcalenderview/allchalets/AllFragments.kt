package com.learningAndroiddeve.androidcalenderview.allchalets

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.adapter.ChaletAdapter
import com.learningAndroiddeve.androidcalenderview.adapter.OnClickListener
import com.learningAndroiddeve.androidcalenderview.databinding.AllChaletsFragmentBinding
import kotlinx.android.synthetic.main.filter_bottom_sheets.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


class AllFragments : Fragment() {

    private lateinit var allFragmentsViewModel: AllFragmentsViewModel
    private lateinit var allChaletsFragmentBinding: AllChaletsFragmentBinding
    private lateinit var chaletAdapter: ChaletAdapter
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

        chaletAdapter = ChaletAdapter(OnClickListener {
            allFragmentsViewModel.displaySelectedChalet(it)
        })

        allChaletsFragmentBinding.chaletsList.itemAnimator = DefaultItemAnimator()
        allChaletsFragmentBinding.chaletsList.adapter = chaletAdapter
        subscribeUi(chaletAdapter)



        allFragmentsViewModel.navigateToSelectedChalet.observe(viewLifecycleOwner, Observer {
            if (it != null){
                val direction = AllFragmentsDirections.actionAllFragmentsToReservationFragment(it)
                findNavController().navigate(direction)
                allFragmentsViewModel.displaySelectedChaletCompleted()
            }
        })

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


    private fun subscribeUi(adapter: ChaletAdapter) {
        allFragmentsViewModel.allChalets.observe(viewLifecycleOwner) { allChalets ->
            adapter.addList(allChalets)
        }
    }

    @ExperimentalCoroutinesApi
    private fun filterDialog() {
        val dialogBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            R.layout.filter_bottom_sheets,
            null,
            false
        )
        val dialog = context?.let {
            BottomSheetDialog(it)
        }
        dialog?.setContentView(dialogBinding.root.rootView)
            dialogBinding.root.rootView.editTextText_price_from.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                (allChaletsFragmentBinding.chaletsList.adapter as ChaletAdapter).filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                (allChaletsFragmentBinding.chaletsList.adapter as ChaletAdapter).filter(s.toString())
            }
        })

        dialogBinding.root.rootView.editTextText_date_from.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                (allChaletsFragmentBinding.chaletsList.adapter as ChaletAdapter).filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                (allChaletsFragmentBinding.chaletsList.adapter as ChaletAdapter).filter(s.toString())
            }
        })

        dialog?.show()
       }


}