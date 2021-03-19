package com.hazz.cow.ui.activity.home

import android.content.Intent
import android.os.Handler
import android.text.Html
import androidx.appcompat.widget.Toolbar
import com.hazz.cow.Constants
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Home
import com.hazz.cow.mvp.model.Certification
import com.hazz.cow.mvp.model.MyAsset
import com.hazz.cow.mvp.presenter.AssetPresenter
import com.hazz.cow.mvp.presenter.CertificationInfoPresenter
import com.hazz.cow.mvp.presenter.HomePresenter
import com.hazz.cow.ui.activity.mine.MineExchangePwdActivity
import com.hazz.cow.ui.activity.RuleActivity
import com.hazz.cow.ui.activity.asset.ChargeActivity
import com.hazz.cow.ui.activity.mine.ContractDetailsActivity
import com.hazz.cow.utils.*
import com.hazz.cow.widget.SafeCheckDialog
import kotlinx.android.synthetic.main.activity_charge.mToolBar
import kotlinx.android.synthetic.main.activity_home_rent_new.*
import kotlinx.android.synthetic.main.activity_home_rent_new.cb


class HomeRentNewActivity : BaseActivity(), IContractView.HomeView, IContractView.AssetView, IContractView.ICertificationInfoView {

    private var mHomePresenter: HomePresenter = HomePresenter(this)
    private var coin = "USDT"
    private var id = ""
    private var price = "0"
    private var usable = "0"
    private var num=1
    private var storage="0"
    private var total="0"
    private var mCertificationInfoPresenter: CertificationInfoPresenter = CertificationInfoPresenter(this)
    private var mAssetPresenter: AssetPresenter = AssetPresenter(this)
    private var mPasswordDialog: SafeCheckDialog? = null
    private var produce: Home.ProductsBean? = null

    override fun getCertification(certification: Certification) {
        if (certification.status == 1) {
            et_name.setText(certification.name)
            et_phone.setText(SPUtil.getString("mobile"))
            et_address.setText(certification.address)
        }
    }

    override fun myAsset(msg: MyAsset) {
        if (msg != null) {
            val assets = msg.assets
            for (coin in assets) {
                if (coin.coin == "USDT") {
                    usable = coin.balance
                    tv_yue.text = "账户余额：$usable"+ "USDT"
                }
            }

        }
    }

    override fun getHome(msg: Home) {
    }

    override fun zuyongSucceed(msg: String) {
        SToast.showText("租用成功，请及时为合同签名")
        startActivity(Intent(this, ContractDetailsActivity::class.java).putExtra("contract_code", msg)
                .putExtra("contract_sign", "0"))
        if (mDialog != null) mDialog?.dismiss()
        finish()
    }


    override fun layoutId(): Int {
        return R.layout.activity_home_rent_new
    }

    override fun initData() {
        mAssetPresenter.myAsset(true)
        mCertificationInfoPresenter.getCertification()
    }


    override fun initView() {

        produce = intent.getSerializableExtra("produce") as Home.ProductsBean?

        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setTitle(produce?.name.toString())
                .setOnLeftIconClickListener { finish() }

        coin = produce?.coin.toString()
        id = produce?.id.toString()
        price=produce?.price.toString()
        storage= produce?.storage.toString()

        tv_service.text = produce?.fee.toString()
        tv_package.text = produce?.seal.toString()
        tv_amount.text = price + "USDT"
        produce?.pic?.let { GlideEngine.createGlideEngine().loadImage(this, Constants.URL_INVITE + it, iv_product) }

        if (produce?.desc!=null)
            tv_info.text = Html.fromHtml(produce?.desc)

        tv_sub.setOnClickListener {
            if (num>1)
            {
                num -= 1
            }
            setChange()
        }

        tv_add.setOnClickListener {
            num+=1
            setChange()
        }

        setChange()

    }

    private fun setChange(){
        et_num.text = BigDecimalUtil.mul(storage,num.toString(),0)
        total=BigDecimalUtil.mul(price,BigDecimalUtil.mul(storage,num.toString(),8),8)
        tv_money.text =total
    }

    override fun start() {
        tv_xieyi.setOnClickListener {
            startActivity(Intent(this, RuleActivity::class.java))
        }

        mTvLogin.setOnClickListener {
            if (cb.isChecked) {

                if (et_num.text.toString().toDouble() > usable.toDouble()) {
                    SToast.showTextLong("账户余额不足，请前往充值")
                    Handler().postDelayed(Runnable {
                        startActivity(Intent(this, ChargeActivity::class.java))
                    }, 500)
                    return@setOnClickListener
                }


                if (et_name.text.toString().isNullOrEmpty()) {
                    SToast.showText("请输入真实姓名")
                    return@setOnClickListener
                }
                if (et_phone.text.toString().isNullOrEmpty()) {
                    SToast.showText("请输入手机号码")
                    return@setOnClickListener
                }
                if (et_address.text.toString().isNullOrEmpty()) {
                    SToast.showText("请输入收货地址")
                    return@setOnClickListener
                }

                if (mPasswordDialog == null) {
                    mPasswordDialog = SafeCheckDialog(this)
                            .setCancelListener { }
                            .setForgetListener {
                                startActivity(Intent(this, MineExchangePwdActivity::class.java))
                            }
                            .setConfirmListener { _, password ->
                                mHomePresenter.zuyong(coin, id, password, total, et_name.text.toString(), et_phone.text.toString(), et_address.text.toString())
                            }.setCancelListener {

                            }
                }
                mPasswordDialog?.show()

            } else {
                SToast.showText("请阅读租用服务协议")
            }

        }
    }


}
