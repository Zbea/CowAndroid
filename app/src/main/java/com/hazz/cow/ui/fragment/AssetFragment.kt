package com.hazz.cow.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.RelativeLayout
import com.hazz.cow.Constants
import com.hazz.cow.R
import com.hazz.cow.base.BaseFragment
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Certification
import com.hazz.cow.mvp.model.InComing
import com.hazz.cow.mvp.model.MyAsset
import com.hazz.cow.mvp.presenter.CertificationInfoPresenter
import com.hazz.cow.mvp.presenter.AssetPresenter
import com.hazz.cow.mvp.presenter.IncomingPresenter
import com.hazz.cow.ui.activity.asset.AssetFilDetailsActivity
import com.hazz.cow.ui.activity.asset.IncomingActivity
import com.hazz.cow.ui.activity.asset.YesterdayEarningsSourceActivity
import com.hazz.cow.ui.activity.home.MsgListActivity
import com.hazz.cow.utils.*
import kotlinx.android.synthetic.main.fragment_asset.*
import kotlinx.android.synthetic.main.fragment_asset.iv_msg
import kotlinx.android.synthetic.main.fragment_asset.toolbar
import kotlinx.android.synthetic.main.fragment_asset.tv_share
import kotlinx.android.synthetic.main.fragment_asset.tv_shouyi
import kotlinx.android.synthetic.main.fragment_asset.tv_static
import kotlinx.android.synthetic.main.fragment_asset.tv_yeji
import kotlinx.android.synthetic.main.fragment_asset.tv_yesterday
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class

AssetFragment : BaseFragment(), IContractView.AssetView, IContractView.ICertificationInfoView,IContractView.ShouyiView {

    private var mCertification: Certification? = null
    private var mAssetPresenter: AssetPresenter = AssetPresenter(this)
    private val mCertificationInfoPresenter = CertificationInfoPresenter(this)
    private var myAsset: MyAsset? = null
    private var list: MutableList<MyAsset.AssetsBean>? = mutableListOf()
    private var incoming: InComing? = null
    private var mIncomingPresenter: IncomingPresenter = IncomingPresenter(this)

    override fun getCertification(certification: Certification) {
        sl_refresh?.isRefreshing = false
        mCertification = certification
        if (certification.status == 1) {
            SPUtil.putObj("certification", certification)
        }
    }

    override fun myAsset(msg: MyAsset) {
        if (mView==null ||tv_shouyi==null)return
        myAsset = msg

        if (msg.usdt_revenue != null && msg.fcoin_revenue != null) {
            tv_shouyi?.text = BigDecimalUtil.mul(msg.usdt_revenue, "1", 8) + "/" + BigDecimalUtil.mul(msg.fcoin_revenue, "1", 8)
        }
        if (msg.usdt_revenue != null && msg.fcoin_revenue == null) {
            tv_shouyi?.text = BigDecimalUtil.mul(msg.usdt_revenue, "1", 8) + "/0.00"
        }
        if (msg.usdt_revenue == null && msg.fcoin_revenue != null) {
            tv_shouyi?.text = "0.00/" + BigDecimalUtil.mul(msg.fcoin_revenue, "1", 8)
        }

        val assets = msg.assets
        list?.clear()
        for (coin in assets) {
            if (coin.coin == "FCOIN" ) {
                tv_fil_balance.text=coin.balance
                tv_fil_frozen.text=coin.frozen
                tv_fil_locked.text=coin.locked
                tv_fil_pledge.text=coin.pledge
                tv_fil_balance_25.text=coin.line25
                tv_fil_balance_75.text=coin.line75
                tv_fil_balance_achievement.text=coin.achievement
                tv_fil_balance_team.text=coin.team
            }
            if (coin.coin == "USDT" ) {
                tv_usdt_balance.text=coin.balance
                tv_usdt_frozen.text=coin.frozen
            }
        }

    }
    override fun inComing(msg: InComing) {
        incoming = msg
        if (mView==null || tv_yesterday==null)return

        tv_static.text = msg.invitation
        tv_share.text = msg.achievement
        tv_yeji.text = msg.team
        tv_yesterday.text = BigDecimalUtil.mul( msg.yesterday_usdt,"1",8) + "/" + BigDecimalUtil.mul( msg.yesterday_fcoin,"1",8)


    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_asset
    }


    @SuppressLint("WrongConstant")
    override fun initView() {

        var layoutParams: RelativeLayout.LayoutParams= toolbar.layoutParams as RelativeLayout.LayoutParams
        layoutParams.topMargin= activity?.let { DensityUtils.getStatusBarHeight(it) }!!
        toolbar.layoutParams=layoutParams

        EventBus.getDefault().register(this)
        mCertification = SPUtil.getObj("certification", Certification::class.java)

        sl_refresh=activity?.findViewById(R.id.sl_refresh_asset)
        sl_refresh?.isRefreshing = true
        sl_refresh?.setColorSchemeResources(R.color.color_main)
        sl_refresh?.setOnRefreshListener {
            lazyLoad()
        }

        iv_msg.setOnClickListener {
            startActivity(Intent(activity, MsgListActivity::class.java))
        }
        rl_earnings.setOnClickListener {
            startActivity(Intent(activity, IncomingActivity::class.java))
        }

        ll_yesterday.setOnClickListener {
            startActivity(Intent(activity, YesterdayEarningsSourceActivity::class.java))
        }

        ll_fil.setOnClickListener {
            startActivity(Intent(activity, AssetFilDetailsActivity::class.java))
        }

    }

    override fun lazyLoad() {

        mAssetPresenter.myAsset(false)
        mIncomingPresenter.shouyi(false)
        mCertificationInfoPresenter.getCertification()


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String) {
        if (event == Constants.CODE_CERTIFICATION_BROAD) {
            mCertificationInfoPresenter.getCertification()
        }
        if (event == Constants.CODE_INVESTMENT_BUY) {
            mAssetPresenter.myAsset(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (mCertification?.status==0)
            {
                mCertificationInfoPresenter.getCertification()
            }
        }
    }




}
