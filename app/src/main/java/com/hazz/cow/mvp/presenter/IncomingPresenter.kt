package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.InComing
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class IncomingPresenter(view: IContractView.ShouyiView) : BasePresenter<IContractView.ShouyiView>(view) {

    fun shouyi(boolean: Boolean) {



        val login = RetrofitManager.service.inComing()

        doRequest(login, object : Callback<InComing>(view) {
            override fun failed(tBaseResult: BaseResult<InComing>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<InComing>) {
                view.inComing(tBaseResult.data!!)
            }

        }, boolean)

    }

}