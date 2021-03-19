package com.hazz.cow.ui.activity.mine

import android.Manifest
import android.os.Handler
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.Contract
import com.hazz.cow.mvp.presenter.ContractManagerPresenter
import com.hazz.cow.utils.ImageUtils
import com.hazz.cow.utils.SToast
import kotlinx.android.synthetic.main.activity_contract_signing.*
import org.greenrobot.eventbus.EventBus
import java.io.File

/**
 * @Created by Zbea
 * @fileName ContractSigningActivity
 * @date 2020/8/17 17:21
 * @email xiaofeng9212@126.com
 * @describe 电子签名
 **/
class ContractSigningActivity : BaseActivity(), IContractView.IContractManagerView {

    private var fileName=""
    private var code=""
    private val mContractManagerPresenter= ContractManagerPresenter(this)

    override fun setSign(data: Contract) {
        EventBus.getDefault().post(data)
        if(mDialog!=null) {
            mDialog?.dismiss();
        }
        finish()
    }
    override fun getContracts(datas: List<Contract>) {
    }
    override fun layoutId(): Int {
        return R.layout.activity_contract_signing
    }

    override fun initData() {
    }

    override fun initView() {
        code=intent.getStringExtra("contract_code")
        btn_clear.setOnClickListener {
            signature_pad.clear()
        }

        btn_save.setOnClickListener {
            if (signature_pad.isEmpty)
            {
                SToast.showText("尚未签名")
                return@setOnClickListener
            }
            permissionsnew?.request(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            )?.subscribe { permission ->
                if (permission!!) {
                    fileName="signing"+System.currentTimeMillis()
                    ImageUtils.saveBmpGallery(this, signature_pad.transparentSignatureBitmap, fileName)
                    Handler().postDelayed(Runnable {
//                        Log.i("sj", File(Environment.getExternalStorageDirectory(), "/eye/image/$fileName.png").toString())
//                        Log.i("sj", ImageUtlis.getFilePath(this,"/eye/")+"$fileName.png")
                        mContractManagerPresenter.setSign(code, File(ImageUtils.getFilePath(this,"/eye/"), "$fileName.png"))
                    }, 500)


                } else {
                    showMissingPermissionDialog()
                }
            }
        }

    }

    override fun start() {
    }



}