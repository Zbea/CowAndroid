package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Mill
import com.hazz.cow.mvp.model.MillEarningsList
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class MillPresenter(view: IContractView.kuangjiView) : BasePresenter<IContractView.kuangjiView>(view) {

    fun kuangji() {



        val login = RetrofitManager.service.getMill()

        doRequest(login, object : Callback<Mill>(view) {
            override fun failed(tBaseResult: BaseResult<Mill>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Mill>) {
                tBaseResult.data?.let { view.getMill(it) }
            }

        }, false)

    }



    fun mingxi(start:String,end:String) {



        val login = RetrofitManager.service.getMillEarnings(start,end)

        doRequest(login, object : Callback<MillEarningsList>(view) {
            override fun failed(tBaseResult: BaseResult<MillEarningsList>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<MillEarningsList>) {
                view.getEarningsList(tBaseResult.data!!)
            }

        }, true)

    }

}