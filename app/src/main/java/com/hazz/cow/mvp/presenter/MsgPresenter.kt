package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Msg
import com.hazz.cow.net.*


class MsgPresenter(view: IContractView.MsgView) : BasePresenter<IContractView.MsgView>(view) {


    fun getMsg() {


        val login = RetrofitManager.service.getMsg()

        doRequest(login, object : Callback<List<Msg>>(view) {
            override fun failed(tBaseResult: BaseResult<List<Msg>>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<List<Msg>>) {
                tBaseResult.data?.let { view?.getMsg(it) }
            }

        }, false)

    }



}