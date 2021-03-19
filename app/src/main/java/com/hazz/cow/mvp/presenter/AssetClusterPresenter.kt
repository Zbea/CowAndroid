package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.*
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class AssetClusterPresenter(view: IContractView.IAssetClusterView) : BasePresenter<IContractView.IAssetClusterView>(view) {

    fun getCluster(boolean: Boolean) {

        val login = RetrofitManager.service.getAssetCluster()

        doRequest(login, object : Callback<AssetCluster>(view) {
            override fun failed(tBaseResult: BaseResult<AssetCluster>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<AssetCluster>) {
                tBaseResult.data?.let { view.getCluster(it) }
            }

        }, boolean)

    }

    fun getClusterEarnings(page: String) {

        val login = RetrofitManager.service.getAssetEarningsList(page)

        doRequest(login, object : Callback<AssetClusterEarningsDetails>(view) {
            override fun failed(tBaseResult: BaseResult<AssetClusterEarningsDetails>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<AssetClusterEarningsDetails>) {
                tBaseResult.data?.let { view.getList(it)}
            }

        }, false)

    }


    fun extractCluster(map:HashMap<String,String>) {

        val login = RetrofitManager.service.extractCluster(map)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                tBaseResult.data?.let { view.onSuccess()}
            }

        }, true)

    }

    fun extractClusterRecord(){


        val login = RetrofitManager.service.extractClusterList()

        doRequest(login, object : Callback<ExtractRecord>(view) {
            override fun failed(tBaseResult: BaseResult<ExtractRecord>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<ExtractRecord>) {
                view.getExtractList(tBaseResult.data!!)
            }

        }, true)

    }


}