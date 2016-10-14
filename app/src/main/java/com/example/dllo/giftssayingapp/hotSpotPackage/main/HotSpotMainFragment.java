package com.example.dllo.giftssayingapp.hotspotpackage.main;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.hotspotpackage.newstar.NewStarFragment;
import com.example.dllo.giftssayingapp.hotspotpackage.originality.OriginalityFragment;
import com.example.dllo.giftssayingapp.hotspotpackage.recommend.RecommendFragment;
import com.example.dllo.giftssayingapp.hotspotpackage.top.TopFragment;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/9/19.
 * http://api.liwushuo.com/v2/items?gender=1&generation=4&limit=50&oddset=0
 */
public class HotSpotMainFragment extends BaseFragment {
    private TabLayout tb_hotspot;
    private ViewPager vp_hotspot;
    private ImageView share;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hotspot;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(context);
        tb_hotspot = bindView(R.id.tb_hotspot);
        vp_hotspot = bindView(R.id.vp_hotspot);
        share = bindView(R.id.iv_hot_spot_share);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new RecommendFragment());
        arrayList.add(new TopFragment());
        arrayList.add(new OriginalityFragment());
        arrayList.add(new NewStarFragment());
        //适配器
        HotSpotMainAdapter adapter = new HotSpotMainAdapter(getChildFragmentManager(), arrayList);
        vp_hotspot.setAdapter(adapter);
        tb_hotspot.setupWithViewPager(vp_hotspot);

        tb_hotspot.getTabAt(0).setText("每日推荐");
        tb_hotspot.getTabAt(1).setText("TOP100");
        tb_hotspot.getTabAt(2).setText("独立原创榜");
        tb_hotspot.getTabAt(3).setText("新星榜");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });


    }
    private void showShare() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 构造一个复制链接的图标
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_share_hyperlink);
        String label = "复制链接";
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager copy = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText("http://hawaii.liwushuo.com/ranks_v2/ranks/1");
                Toast.makeText(getContext(), "复制成功", Toast.LENGTH_LONG).show();
            }
        };
        oks.setCustomerLogo(bitmap, label, listener);


        // 启动分享GUI
        oks.show(context);
    }
}
