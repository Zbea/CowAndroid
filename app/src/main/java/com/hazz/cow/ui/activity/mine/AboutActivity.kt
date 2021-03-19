package com.hazz.cow.ui.activity.mine

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.utils.ToolBarCustom
import com.hazz.cow.utils.Utils
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_rule.mToolBar


class AboutActivity : BaseActivity() {

    override fun initData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_about
    }


    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setTitle("关于我们")
                .setOnLeftIconClickListener { finish() }

    }
    override fun start() {
        tv_version.text="牛牛矿场V"+Utils.getVersionName(this)
        tv_versions.text="V"+Utils.getVersionName(this)
        rl_version.setOnClickListener {
            Beta.checkAppUpgrade()
        }
        rl_info.setOnClickListener {
            startActivity(Intent(this,CompanyInfoActivity::class.java))
        }
    }


}
