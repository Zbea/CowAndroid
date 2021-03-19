package com.hazz.cow.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.cow.Constants
import com.hazz.cow.R
import com.hazz.cow.mvp.model.Home
import com.hazz.cow.utils.GlideEngine

class HomeAdapter(layoutResId: Int, data: List<Home.ProductsBean>?) : BaseQuickAdapter<Home.ProductsBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: Home.ProductsBean) {

        helper.run {
            setText(R.id.tv_name, item.name)
            setText(R.id.tv_earnings, "每日收益："+item.daily_desc)
            setText(R.id.tv_time, "服务周期："+item.round_desc)
            setText(R.id.tv_other, "其他收益："+item.other_desc)
            GlideEngine.createGlideEngine().loadImage(mContext, Constants.URL_INVITE+item.pic,getView(R.id.iv))
        }
    }

}
