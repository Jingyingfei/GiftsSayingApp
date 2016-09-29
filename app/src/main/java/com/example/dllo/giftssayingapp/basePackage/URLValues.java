package com.example.dllo.giftssayingapp.basepackage;

/**
 * Created by dllo on 16/5/23.
 */
public final class URLValues {
    //Editext 中文字url
    public static final String EDIT_NAME = "http://api.liwushuo.com/v2/search/hot_words_v2";
    //hometab标题url
    public static final String HOME_TITLE = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
    //home轮播图url
    public static final String HOME_CAROUSEL = "http://api.liwushuo.com/v2/banners";
    //home小方框url
    public static final String HOME_BOX = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    //home的listview url
    public static final String HOME_CELL = "http://api.liwushuo.com/v2/channels/103/items_v2?ad=2&gender=2&generation=1&limit=20&offset=0";

    //home的listview 点击进入界面的url
    public static final String HOME_DETAIL_CLICK_TAOBAO = "http://www.liwushuo.com/posts/";
    public static final String HOME_DETAIL_BEFORE = "http://api.liwushuo.com/v2/posts/";
    public static final String HOME_DETAIL_AFTER = "?show_baichuan=1";


    //榜单
    //每日推荐
    public static final String HOTSPOT_RECOMMEND = "http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=0";
    //top100
    public static final String HOTSPOT_TOP = "http://api.liwushuo.com/v2/ranks_v2/ranks/2?limit=20&offset=0";
    //独立原创榜
    public static final String HOTSPOT_ORIDINALITY = "http://api.liwushuo.com/v2/ranks_v2/ranks/3?limit=20&offset=0";
    //新星榜
    public static final String HOTSPOT_NEWSTAR = "http://api.liwushuo.com/v2/ranks_v2/ranks/4?limit=20&offset=0";

    //分类页

    // 2.攻略-栏目
    public static final String STRATEGY_PART = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
    // 攻略-品类
    public static final String STRATEGY_COLUMN = "http://api.liwushuo.com/v2/channel_groups/all";

    //3.单品
    public static final String STRATEGY_SKU = "http://api.liwushuo.com/v2/item_categories/tree";

    //4.查看更多
    public static final String STRATEGY_CHECK = "http://api.liwushuo.com/v2/collections?limit=20&offset=0";

    //5.礼物
    public static final String STRATEGY_GIFT = "http://api.liwushuo.com/v2/item_categories/tree";


}
