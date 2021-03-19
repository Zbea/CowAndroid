package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Investment
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class InvestmentPresenter(view: IContractView.IInvestmentView) : BasePresenter<IContractView.IInvestmentView>(view) {


    fun getLists(pageNum:String) {

        val login = RetrofitManager.service.getInvestments(pageNum,"0")

        doRequest(login, object : Callback<Investment>(view) {
            override fun failed(tBaseResult: BaseResult<Investment>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Investment>) {
                tBaseResult.data?.let { view.getLists(it)}
            }

        }, true)

    }

    fun getListsCompleted(pageNum:String) {

        val login = RetrofitManager.service.getInvestments(pageNum,"1")

        doRequest(login, object : Callback<Investment>(view) {
            override fun failed(tBaseResult: BaseResult<Investment>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Investment>) {
                tBaseResult.data?.let { view.getListsCompleted(it)}
            }

        }, false)

    }

    fun outInvestment(id:String,passWord:String) {

        val login = RetrofitManager.service.outInvestment(id,passWord)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.onSuccess()
            }

        }, true)

    }


}