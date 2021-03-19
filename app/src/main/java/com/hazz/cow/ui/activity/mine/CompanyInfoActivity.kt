package com.hazz.cow.ui.activity.mine

import android.text.Html
import androidx.appcompat.widget.Toolbar
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Xieyi
import com.hazz.cow.mvp.model.SignRecord
import com.hazz.cow.mvp.presenter.ProtocolPresenter
import com.hazz.cow.utils.ToolBarCustom
import kotlinx.android.synthetic.main.activity_company_info.*


class CompanyInfoActivity : BaseActivity(), IContractView.XieyiView {
    override fun getSignRecord(msg: SignRecord) {

    }

    override fun xieyi(msg: Xieyi) {
        var desc=msg.desc?.replace("关于我们：","      ")
        tv_rule.text=Html.fromHtml(desc)
    }

    override fun initData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_company_info
    }

    private var mProtocolPresenter:ProtocolPresenter= ProtocolPresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setTitle("公司简介")
                .setOnLeftIconClickListener {  finish() }

        mProtocolPresenter.protocol("关于我们")

    }
    override fun start() {

    }


}
