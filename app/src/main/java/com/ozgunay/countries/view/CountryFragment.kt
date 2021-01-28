package com.ozgunay.countries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ozgunay.countries.R
import com.ozgunay.countries.databinding.FragmentCountryBinding
import com.ozgunay.countries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel

    private var countryUuid = 0

    private lateinit var dataBinding: FragmentCountryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country

                /*
                countryName.text=country.countryName
                countryRegion.text=country.countryRegion
                countryCapital.text=country.countryCapital
                countryCurrency.text=country.countryCurrency
                countryLanguage.text=country.countryLanguage
                context?.let {
                    countryImage.downloadFromUrl(country.url, placeholderProgressBar(it))
                }*/


            }
        })
    }


}