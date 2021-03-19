package com.hazz.cow.ui.activity.home.investment

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.cow.R
import com.hazz.cow.base.BaseActivity
import com.hazz.cow.mvp.contract.IContractView
import com.hazz.cow.mvp.model.InvestmentProduct
import com.hazz.cow.mvp.presenter.InvestmentProductPresenter
import com.hazz.cow.ui.adapter.InvestmentProductAdapter
import com.hazz.cow.utils.ToolBarCustom
import com.hazz.cow.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.activity_list.mToolBar
import kotlinx.android.synthetic.main.activity_list.rc_list

/**
 * @Created by Zbea
 * @fileName InvestmentProductListActivity
 * @date 2020/11/25 10:20
 * @email xiaofeng9212@126.com
 * @describe 投资产品列表
 **/
class InvestmentProductListActivity : BaseActivity(), IContractView.IInvestmentProductView {

    private var mInvestmentProductPresenter=InvestmentProductPresenter(this)
    private var mAdapter:InvestmentProductAdapter?=null

    override fun getLists(items: List<InvestmentProduct>) {
        mAdapter?.setNewData(items)
    }

    override fun onSuccess() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_list_wihte
    }

    override fun initData() {

        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setTitle("增值服务产品")
                .setOnLeftIconClickListener {finish() }

    }

    override fun initView() {
        rc_list.layoutManager = LinearLayoutManager(this)//创建布局管理
        mAdapter = InvestmentProductAdapter(R.layout.item_investment_product, null)
        rc_list.adapter = mAdapter
        mAdapter?.bindToRecyclerView(rc_list)
        mAdapter?.setEmptyView(R.layout.fragment_empty)
        val leftRightOffset = DensityUtil.dp2px(15f)
        rc_list.addItemDecoration(RewardItemDeco(0, 0, 0, leftRightOffset, 0))
    }

    override fun start() {
        mInvestmentProductPresenter.getLists()
    }


}