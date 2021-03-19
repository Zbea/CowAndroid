package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Config
import com.hazz.cow.net.*


class ConfigPresenter(view: IContractView.IConfigView) : BasePresenter<IContractView.IConfigView>(view) {


    fun getConfig() {


        val login = RetrofitManager.service.getConfig()

        doRequest(login, object : Callback<Config>(view) {
            override fun failed(tBaseResult: BaseResult<Config>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Config>) {
                tBaseResult.data?.let { view.getConfig(it) }
            }

        }, false)

    }



}