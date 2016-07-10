package com.alkv.ljw.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alkv.ljw.actions.NewsItemActivity;
import com.alkv.ljw.adapter.ArticlePullToRefreshAdapter;
import com.alkv.ljw.adapter.MyMainArticleViewPagerAdapter;
import com.alkv.ljw.customview.MyMainArticleViewPager;
import com.alkv.ljw.gamecenter.R;
import com.alkv.ljw.model.OneNewInfo;
import com.alkv.ljw.utils.GetFromDb;
import com.alkv.ljw.utils.HttpUtils;
import com.alkv.ljw.utils.JsonUtils;
import com.alkv.ljw.utils.SaveToDB;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alkv1 on 2016/7/6.
 */
public class MainArticleFragment extends Fragment{
    private  String typeid;
    private Context context;
    private MyMainArticleViewPager myMainArticleViewPager;
    private PullToRefreshListView pullToRefreshListView;
    private ArticlePullToRefreshAdapter adapter;
    String urlPart1 = "http://122.226.122.6/sitemap/api.php?row=10&typeid=";
    String urlPart2 = "&paging=1&page=";
    private List<HashMap<String,String>> listData;

    @SuppressLint("ValidFragment")
    public MainArticleFragment(String typeid, Context context){
        this.typeid = typeid;
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获得fragment中的布局
        View view = inflater.inflate(R.layout.main_article_fragment,null);
        //获得布局中的自定义viewpager
        myMainArticleViewPager = (MyMainArticleViewPager) view.findViewById(R.id.main_article_fragment_myviewpager);
        //给自定义viewpager中填充图片 轮播图
        picToViewPager();
        //获取fragment中的pulltorefreshListview
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.main_article_fragment_pulltorefresh_listview);
        //给pullToRefreshListView中填充
        fillPullToRefreshListview();

        return view;
    }

    //给自定义viewpager中填充图片 轮播图
    private void picToViewPager(){
        int [] lunbos = {R.drawable.main_lunbo_viewpager_01,R.drawable.main_lunbo_viewpager_02,
                R.drawable.main_lunbo_viewpager_03};
        //设置放图片的list集合
        List<ImageView> list = new ArrayList<>();
        for(int i = 0; i < lunbos.length ; i++ ){
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(lunbos[i]);
            list.add(imageView);
        }
        MyMainArticleViewPagerAdapter adapter = new MyMainArticleViewPagerAdapter(list);
        myMainArticleViewPager.setAdapter(adapter);
    }

    //给pullToRefreshListView中填充内容 设置滑动监听 以及选中监听
    private void fillPullToRefreshListview(){
        //设置监听
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下滑刷新
                new task().execute(typeid,"2");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉刷新
                new task().execute(typeid,"3");

            }
        });
        //获取数据
        List<OneNewInfo> newsList = new ArrayList<>();
        newsList.addAll(GetFromDb.getFromDB(context,typeid));

        listData = new ArrayList<>();

        for(OneNewInfo i:newsList){
            HashMap<String,String> map = new HashMap<>();
            map.put("title",i.getTitle());
            map.put("pubdate",i.getPubdate());
            map.put("litpic",i.getLitpic());
            map.put("arcurl",i.getArcurl());

            listData.add(map);
        }

        adapter = new ArticlePullToRefreshAdapter(listData,context);
        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, NewsItemActivity.class);
                intent.putExtra("itemUrl",listData.get(i-1).get("arcurl"));
                startActivity(intent);
            }
        });

    }

    //加载每一个页面内容的异步任务
    class task extends AsyncTask{
        List<HashMap<String,String>> listTemp ;
        @Override
        protected Object doInBackground(final Object[] objects) {
                    byte [] b = HttpUtils.httpGet(urlPart1+objects[0]+urlPart2+objects[1]);
                    if(b!=null){
                        Log.i("kobe","异步任务进入json解析");
                        List<OneNewInfo> list = JsonUtils.jsonToList(new String(b));
                        Log.i("kobe","异步任务进入json解析");
                        if(list!=null){
                            boolean flag = SaveToDB.saveListToDB(list,context);
                            if(flag){
                                Log.i("kobe","异步任务加载完成");
                                listTemp = new ArrayList<>();
                                for(OneNewInfo i:list){
                                    HashMap<String,String> map = new HashMap<>();
                                    map.put("title",i.getTitle());
                                    map.put("pubdate",i.getPubdate());
                                    map.put("litpic",i.getLitpic());
                                    map.put("arcurl",i.getArcurl());
                                    listTemp.add(map);
                                }
                            }
                        }
                    }
            return "ok";
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.i("kobe","跳转到了onPostExecute页面");
            if("ok".equals(o.toString())){
                Log.i("kobe","onPostExecute页面验证result成功");
                //更新数据
                listData.addAll(listTemp);
                //适配器刷新
                adapter.notifyDataSetChanged();
                //完成下拉
                pullToRefreshListView.onRefreshComplete();
                super.onPostExecute(o);
                Log.i("kobe","刷新页面成功、检查是否显示");
            }
        }
    }
}
