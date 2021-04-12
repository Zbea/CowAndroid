package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Xieyi
import com.hazz.cow.mvp.model.SignRecord
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class ProtocolPresenter(view: IContractView.XieyiView) : BasePresenter<IContractView.XieyiView>(view) {

    fun protocol(target:String) {



        val login = RetrofitManager.service.xieyi(target)

        doRequest(login, object : Callback<Xieyi>(view) {
            override fun failed(tBaseResult: BaseResult<Xieyi>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Xieyi>) {
                view.xieyi(tBaseResult.data!!)
            }

        }, true)

    }


}