package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.MillEarningsDetails
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class MillEarningsDetailsPresenter(view: IContractView.EarningsDetailsView) : BasePresenter<IContractView.EarningsDetailsView>(view) {


    fun getLists(orderId:String) {

        val login = RetrofitManager.service.getEarningsDetails(orderId)

        doRequest(login, object : Callback<List<MillEarningsDetails>>(view) {
            override fun failed(tBaseResult: BaseResult<List<MillEarningsDetails>>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<List<MillEarningsDetails>>) {
                tBaseResult.data?.let { view.getDetails(it)}
            }

        }, true)

    }

    fun getLists(orderId:String,start:String,end:String) {

        val login = RetrofitManager.service.getEarningsDetails(orderId,start,end)

        doRequest(login, object : Callback<List<MillEarningsDetails>>(view) {
            override fun failed(tBaseResult: BaseResult<List<MillEarningsDetails>>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<List<MillEarningsDetails>>) {
                tBaseResult.data?.let { view.getDetails(it)}
            }

        }, true)

    }





}