package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.MyAsset
import com.hazz.cow.net.BasePresenter
import com.hazz.cow.net.BaseResult
import com.hazz.cow.net.Callback
import com.hazz.cow.net.RetrofitManager


class AssetPresenter(view: IContractView.AssetView) : BasePresenter<IContractView.AssetView>(view) {

    fun myAsset(boolean: Boolean) {

        val login = RetrofitManager.service.myAsset()

        doRequest(login, object : Callback<MyAsset>(view) {
            override fun failed(tBaseResult: BaseResult<MyAsset>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<MyAsset>) {
                tBaseResult.data?.let { view.myAsset(it) }
            }

        }, boolean)

    }


}