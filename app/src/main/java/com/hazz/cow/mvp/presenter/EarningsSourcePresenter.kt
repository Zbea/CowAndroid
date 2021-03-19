package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.EarningsSource
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class EarningsSourcePresenter(view: IContractView.EarningsSourceView) : BasePresenter<IContractView.EarningsSourceView>(view) {


    fun getFilLists() {



        val login = RetrofitManager.service.filEarningsList()

        doRequest(login, object : Callback<EarningsSource>(view) {
            override fun failed(tBaseResult: BaseResult<EarningsSource>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<EarningsSource>) {
                tBaseResult.data?.let { view.getList(it) }
            }

        }, true)

    }

    fun getUSDTLists() {

        val login = RetrofitManager.service.usdtEarningsList()

        doRequest(login, object : Callback<EarningsSource>(view) {
            override fun failed(tBaseResult: BaseResult<EarningsSource>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<EarningsSource>) {
                tBaseResult.data?.let { view.getList(it) }
            }

        }, true)

    }

}