package com.hazz.cow.ui.activity.mine

import androidx.appcompat.widget.Toolbar
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.UserInfo
import com.hazz.cow.mvp.presenter.LoginPresenter
import com.hazz.cow.utils.*
import kotlinx.android.synthetic.main.activity_mine_login_pwd.*


class MineLoginPwdActivity : BaseActivity(), IContractView.LoginView {
    override fun loginSuccess(msg: UserInfo) {

    }

    override fun sendSms(msg: String) {

    }


    override fun registerSucceed(msg: String) {

        SToast.showText(msg)
        finish()
    }


    override fun layoutId(): Int {
        return R.layout.activity_mine_login_pwd
    }

    override fun initData() {

    }


    private var mLoginPresenter: LoginPresenter = LoginPresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setTitle(getString(R.string.modify_pwd))
                .setOnLeftIconClickListener {finish() }


    }

    override fun start() {

        tv_bt.setOnClickListener {
            if (et_new_pwd.text.toString() != et_new_pwd_again.text.toString()) {
                SToast.showText(getString(R.string.pwd_not_same))
                return@setOnClickListener
            }
            if(et_new_pwd.text.toString().length<6||et_new_pwd_again.text.toString().length<6){
                SToast.showText(getString(R.string.pwd_not_length))
                return@setOnClickListener
            }
            if (et_old_pwd.text.toString().isNullOrEmpty()||et_new_pwd.text.toString().isNullOrEmpty()||et_new_pwd_again.text.toString().isNullOrEmpty())
            {
                SToast.showText("输入内容不能为空")
                return@setOnClickListener
            }
            mLoginPresenter.resetPwd(et_old_pwd.text.toString().trim(), et_new_pwd_again.text.toString().trim())
        }
    }



}
