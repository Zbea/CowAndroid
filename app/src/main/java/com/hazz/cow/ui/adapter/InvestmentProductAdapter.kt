package com.hazz.cow.ui.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.cow.R
import com.hazz.cow.mvp.model.InvestmentProduct
import com.hazz.cow.ui.activity.home.investment.InvestmentProductBuyActivity
import com.hazz.cow.utils.BigDecimalUtil

class InvestmentProductAdapter(layoutResId: Int, data: List<InvestmentProduct>?) : BaseQuickAdapter<InvestmentProduct, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: InvestmentProduct) {

        helper.setText(R.id.tv_title, item.name+"(${item.round}天)")
        helper.setText(R.id.tv_fee, BigDecimalUtil.mul(item.rate,"100"))

        helper.getView<TextView>(R.id.tv_buy).setOnClickListener {
            mContext.startActivity(Intent(mContext,InvestmentProductBuyActivity::class.java).putExtra("investment",item))
        }



    }

}
