package com.learningAndroiddeve.androidcalenderview.reservation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.databinding.ReservationFragmentBinding
import com.learningAndroiddeve.androidcalenderview.utiles.ReservedDaysDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.price_bottom_sheet.view.*

class ReservationFragment : Fragment() {

    private lateinit var reservationFragmentBinding:ReservationFragmentBinding
    private lateinit var reservationViewModel : ReservationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        reservationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.reservation_fragment,container,false)

        val selectedChalet = arguments?.let {
            ReservationFragmentArgs.fromBundle(it).selectedChalet
        }

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ReservationsViewModelFactory(application)
        reservationViewModel = ViewModelProvider(this,viewModelFactory).get(ReservationViewModel::class.java)

        reservationFragmentBinding.lifecycleOwner = this
        reservationFragmentBinding.viewModel = reservationViewModel

        val listOfDatesFromServer = selectedChalet?.chaletAvailableDays

        if (listOfDatesFromServer != null) {
            for (list in listOfDatesFromServer) {
                val listSpitted = list.split("-")
                val dates = CalendarDay.from(
                    listSpitted[0].toInt(),
                    listSpitted[1].toInt(),
                    listSpitted[2].toInt()
                )
                reservationFragmentBinding.calendarView.addDecorators(ReservedDaysDecorator(listOf(dates),"N/A"))
                reservationFragmentBinding.calendarView.invalidateDecorators()
            }
        }


        val images = HashMap<String,String>()
        selectedChalet?.chaletImages?.forEach { url ->
            images[""] = url
            for (name in images.keys) {
                val textSliderView = TextSliderView(context)
                textSliderView.image(images[name]).scaleType = BaseSliderView.ScaleType.Fit
                reservationFragmentBinding.slider.addSlider(textSliderView)
            }
        }

        reservationFragmentBinding.slider.setPresetTransformer(SliderLayout.Transformer.Stack)
        reservationFragmentBinding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        reservationFragmentBinding.slider.setCustomAnimation(DescriptionAnimation())
        reservationFragmentBinding.slider.setDuration(2000)

        reservationFragmentBinding.bookNowButton.setOnClickListener {
            val datesList = reservationFragmentBinding.calendarView.selectedDates.toList()
            if(datesList.isNotEmpty()){
                openPriceDialog(datesList,
                    "10",
                    selectedChalet?.chaletInsurancePrice.toString(),
                    selectedChalet?.chaletCommissionsValue.toString(),
                    selectedChalet?.chaletNormalDayPrice.toString(),
                    selectedChalet?.chaletHolidayPrice.toString()
                )
            }else{
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.select_dates),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        return reservationFragmentBinding.root
    }
    @SuppressLint("SetTextI18n")
    private fun openPriceDialog(datesList: List<CalendarDay>, chaletPercentage:String, chaletInsurance:String
                                , chaletCommision:String, chaletNormalPrice:String, chaletWeekEndPrice:String) {
        val dialogBinding = DataBindingUtil
            .inflate<ViewDataBinding>(LayoutInflater.from(context), R.layout.price_bottom_sheet, null, false)
        val dialog = context?.let { BottomSheetDialog(it) }
        dialog?.setContentView(dialogBinding.root.rootView)

        val priceDetailes = reservationViewModel.calculatePrice(datesList,chaletPercentage,chaletInsurance
        ,chaletCommision,chaletNormalPrice,chaletWeekEndPrice)


        dialogBinding.root.rootView.number_of_days_textView.text   = "${datesList.count()} ${getString(R.string.day)}"
        dialogBinding.root.rootView.normal_day_rate_textView.text  = "${priceDetailes[0]} ${getString(R.string.usd)}"
        dialogBinding.root.rootView.holiday_price_textView.text    = "${priceDetailes[1]}${getString(R.string.usd)}"
        dialogBinding.root.rootView.insurance_value_textView.text  = "${priceDetailes[2]}${getString(R.string.usd)}"
        dialogBinding.root.rootView.commission_value_textView.text = "${priceDetailes[3]} ${getString(R.string.usd)}"
        dialogBinding.root.rootView.discount_value_textView.text   = "${priceDetailes[4]}%"
        dialogBinding.root.rootView.total_summation_textView.text  = "${priceDetailes[5]} ${getString(R.string.usd)}"

        dialogBinding.root.rootView.confirmation_button.setOnClickListener {
           Toast.makeText(context,"Hi Welcome To The Chalet :)",Toast.LENGTH_LONG).show()
        }
        dialog?.show()
    }
}