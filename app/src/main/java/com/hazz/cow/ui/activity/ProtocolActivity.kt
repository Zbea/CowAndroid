package com.hazz.cow.ui.activity

import android.text.Html
import androidx.appcompat.widget.Toolbar
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Xieyi
import com.hazz.cow.mvp.model.SignRecord
import com.hazz.cow.mvp.presenter.ProtocolPresenter
import com.hazz.cow.utils.ToolBarCustom
import kotlinx.android.synthetic.main.activity_rule.*


class ProtocolActivity : BaseActivity(), IContractView.XieyiView {
    override fun getSignRecord(msg: SignRecord) {

    }

    override fun xieyi(msg: Xieyi) {
        var desc=msg.desc?.replace("关于我们：","      ")
        tv_rule.text=Html.fromHtml(desc)
    }

    override fun initData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_rule
    }

    private var mProtocolPresenter:ProtocolPresenter= ProtocolPresenter(this)
    private var type=0
    override fun initView() {
        val onLeftIconClickListener = ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setOnLeftIconClickListener {  finish() }
         type= intent.getIntExtra("type",0)

        if(type==0){
            onLeftIconClickListener.setTitle("用户注册协议")
            mProtocolPresenter.protocol("用户协议")
        }else{
            onLeftIconClickListener.setTitle("关于我们")
            mProtocolPresenter.protocol("关于我们")
        }

    }
    override fun start() {

    }


}
