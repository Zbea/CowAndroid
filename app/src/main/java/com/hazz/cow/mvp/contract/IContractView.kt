package com.hazz.cow.mvp.contract

import com.hazz.cow.mvp.model.*
import com.hazz.cow.net.BaseView


/**
 * Description:
 * Date：2019/4/9-14:03
 * Author: cwh
 */
interface IContractView {

    interface IConfigView: BaseView {

        fun getConfig(item: Config)

    }

    interface LoginView: BaseView {

        fun loginSuccess(msg: UserInfo)
        fun sendSms(msg:String)
        fun registerSucceed(msg:String)
    }

    interface CoinView: BaseView {

        fun getCoin(msg:List<Coin>)
        fun getFriends(msg: Friends)
    }

    interface MsgView: BaseView {

        fun getMsg(msg:List<Msg>)

    }
    interface NodeView: BaseView {

        fun getNode(msg: Node)
        fun getAccount(msg: Account)
        fun setHeader(msg: UploadModel)
        fun getCertification(data: Certification)
    }

    interface TibiView: BaseView {

        fun tibiSucceed(msg:String)
        fun tibiRecord(msg: ExtractRecord)
    }

    interface ShouyiView: BaseView {

        fun inComing(msg: InComing)
    }
    interface kuangjiView: BaseView {

        fun getMill(msg: Mill)
        fun getEarningsList(msg: MillEarningsList)
    }

    interface EarningsDetailsView: BaseView {

        fun getDetails(msg:List<MillEarningsDetails>)
    }
    interface AssetFilDetailsView: BaseView {

        fun getDetails(msg:List<AssetDetails>)
        fun getClusterDetails(msg:AssetClusterEarningsDetails)
    }

    interface HomeView: BaseView {

        fun getHome(msg:Home)
        fun zuyongSucceed(msg:String)
    }

    /**
     *
     */
    interface IAssetClusterView: BaseView {

        fun getCluster(msg:AssetCluster)
        fun getList(item:AssetClusterEarningsDetails)
        fun onSuccess()
        fun getExtractList(item: ExtractRecord)
    }

    interface ChargeView: BaseView {

        fun getAddress(msg: Charge)

        fun chargeRecord(msg: ChargeRecord)
    }


    interface AssetView: BaseView {

        fun myAsset(msg: MyAsset)
    }

    interface EarningsSourceView: BaseView {

        fun getList(msg: EarningsSource)
    }

    interface XieyiView: BaseView {

        fun xieyi(msg:Xieyi)
        fun getSignRecord(msg: SignRecord)
    }



    //认证
    interface ICertificationView: BaseView {
        fun sendSms(msg:String)
        fun commit()
    }

    //
    interface ICertificationInfoView: BaseView {

        fun getCertification(certification : Certification)

    }


    interface IContractManagerView: BaseView {

        fun getContracts(datas:List<Contract>)
        fun setSign(data: Contract)

    }

    interface IInvestmentView: BaseView {

        fun getLists(item:Investment)
        fun getListsCompleted(item:Investment)
        fun onSuccess()
    }

    interface IInvestmentProductView: BaseView {

        fun getLists(items:List<InvestmentProduct>)
        fun onSuccess()

    }

    interface IClusterView: BaseView {

        fun getLists(item:Cluster)
        fun onSuccess()
    }

}