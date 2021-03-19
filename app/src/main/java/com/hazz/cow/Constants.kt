
package com.hazz.cow

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * Created by xk on 2017/11/27.
 * desc: 常量
 */
class Constants private constructor() {

    companion object {

        const val SALE_MIN=10 //最少卖多少个


        const val BUY_MIN=100 //最少买多少个
        const val REAT_MIN=20 //最少租用矿机

        const val CODE_EXCHANGE_SALE="10001"
        const val CODE_CERTIFICATION_BROAD="10003"
        const val CODE_SKIN_BROAD="10004"
        const val CODE_INVESTMENT_BUY="10005"
        const val CODE_CLUSTER_EXTRACT="10006"

        const val BUGLY_ID="9e19916e51"

        const val URL_NEW_BASE = "http://192.168.1.116:5000/"
        const val URL_BASE = "http://192.168.1.116/api/"
        const val URL_INVITE = "http://192.168.1.116/"

//        const val URL_NEW_BASE = "http://8.140.144.249:5000/"
//        const val URL_BASE = "http://8.140.144.249/api/"
//        const val URL_INVITE = "http://8.140.144.249/"

        const val URL_DOWNLOAD = "$URL_INVITE#/about"
    }


}


enum class LanguageType(var value: String) {
    /**
     * 中文 简体
     */
    LG_SIMPLIFIED_CHINESE("zh_CN"),

    /**
     * 中文 繁体
     */
    LG_TRADITIONAL_CHINESE("zh_TW"),

    /**
     * 英语
     */
    LG_ENGLISH("en_US")
}