package com.alkv.ljw.actions;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alkv.ljw.adapter.GamePullToRefreshAdapter;
import com.alkv.ljw.gamecenter.R;
import com.alkv.ljw.model.OneNewInfo;
import com.alkv.ljw.utils.HttpUtils;
import com.alkv.ljw.utils.JsonUtils;
import com.alkv.ljw.utils.SaveToDB;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private PullToRefreshGridView game_activity_gridview;
    private Spinner game_activity_spinner;
    private String[] spinnerData = {"动作(ACT)","射击(FPS)","角色扮演(RPG)","养成(GAL)","益智(PUZ)",
            "即时战略(RTS)","策略(SLG)","体育(SPG)","模拟经营(SIM)","赛车(RAC)","冒险(AVG)","动作角色(ARPG)"};
    private List<HashMap<String,String>> listData;
    private GamePullToRefreshAdapter gamePullToRefreshAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("游戏");
        setContentView(R.layout.activity_game);
        //初始化控件
        initViews();
    }
    //初始化控件
    private void initViews(){
        game_activity_gridview = (PullToRefreshGridView) this.findViewById(R.id.game_activity_gridview);
        game_activity_spinner = (Spinner) this.findViewById(R.id.game_activity_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerData );
        game_activity_spinner.setAdapter(spinnerAdapter);
        game_activity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GameActivity.this,"选择了："+spinnerData[i],Toast.LENGTH_SHORT).show();
                switch(spinnerData[i]){
                    case "动作(ACT)":
                        new loadGames().execute("10","181","2");
                        break;
                    case "射击(FPS)":
                        new loadGames().execute("10","182","2");
                        break;
                    case "角色扮演(RPG)":
                        new loadGames().execute("10","183","2");
                        break;
                    case "养成(GAL)":
                        new loadGames().execute("10","184","2");
                        break;
                    case "益智(PUZ)":
                        new loadGames().execute("10","185","2");
                        break;
                    case "即时战略(RTS)":
                    new loadGames().execute("10","186","2");
                        break;
                    case "策略(SLG)":
                        new loadGames().execute("10","187","2");
                        break;
                    case "体育(SPG)":
                        new loadGames().execute("10","188","2");
                        break;
                    case "模拟经营(SIM)":
                        new loadGames().execute("10","189","2");
                        break;
                    case "赛车(RAC)":
                        new loadGames().execute("10","190","2");
                        break;
                    case "冒险(AVG)":
                        new loadGames().execute("10","191","2");
                        break;
                    case "动作角色(ARPG)":
                        new loadGames().execute("10","192","2");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //game_activity_gridview配置
        Log.i("kobe","开始配置game_activity_gridview");
        listData = new ArrayList<>();
        gamePullToRefreshAdapter = new GamePullToRefreshAdapter(listData,GameActivity.this);
        game_activity_gridview.setAdapter(gamePullToRefreshAdapter);
    }
    //加载游戏列表的异步任务
    class loadGames extends AsyncTask{
        List<HashMap<String,String>>listTemp = new ArrayList<>();
        @Override
        protected Object doInBackground(Object[] objects) {
            byte [] b = HttpUtils.httpGet("http://www.3dmgame.com/sitemap/api.php?row="+objects[0]+
                    "&typeid="+objects[1]+"&paging=1&page="+objects[2]);
            if(b!=null){
                List<OneNewInfo> list = JsonUtils.jsonToList(new String(b));
                if(list!=null){
                    boolean flag = SaveToDB.saveGamePicsToDB(list,GameActivity.this);
                    if(flag){
                        Log.i("kobe","异步任务加载完成");
                        for(OneNewInfo i:list){
                            HashMap<String,String> map = new HashMap<>();
                            map.put("typeid",i.getTypeid());
                            map.put("title",i.getTitle());
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
            if("ok".equals(o.toString())){
                listData.addAll(listTemp);
                gamePullToRefreshAdapter.notifyDataSetChanged();
                game_activity_gridview.onRefreshComplete();
                super.onPostExecute(o);
            }
        }
    }

}
