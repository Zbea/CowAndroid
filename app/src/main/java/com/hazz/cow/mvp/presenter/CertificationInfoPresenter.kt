package com.hazz.cow.mvp.presenter


import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Certification
import com.hazz.cow.net.*


class CertificationInfoPresenter(view: IContractView.ICertificationInfoView) : BasePresenter<IContractView.ICertificationInfoView>(view) {


    fun getCertification() {


        val login = RetrofitManager.service.getCertification()

        doRequest(login, object : Callback<Certification>(view) {
            override fun failed(tBaseResult: BaseResult<Certification>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Certification>) {
                tBaseResult.data?.let {
                    view.getCertification(it)
                }
            }

        }, false)

    }



}