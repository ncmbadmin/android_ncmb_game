package mbaas.com.nifty.androidncmbgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_page);
        checkRanking();
    }

    protected void checkRanking() {

        // **********【問題２】ランキングを表示しよう！**********

        //HighScoreクラスを検索するクエリを作成
        NCMBQuery<NCMBObject> query = new NCMBQuery<NCMBObject>("GameScore");

        //Scoreフィールドの降順でデータを取得
        query.addOrderByDescending("score");

        //検索件数を5件に設定
        query.setLimit(5);

        //データストアでの検索を行う
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> objects, NCMBException e) {
                if (e != null) {
                    //エラー時の処理
                    Log.e("NCMB", e.getMessage());
                } else {
                    //成功時の処理
                    Log.i("NCMB", "Get successful");
                    //ListViewオブジェクトの取得
                    ListView lv = (ListView)findViewById(R.id.lstRanking);
                    // ループカウンタ
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RankingActivity.this, android.R.layout.simple_list_item_1);

                    for (int i = 0, n = objects.size(); i < n; i++) {
                        NCMBObject o = objects.get(i);
                        Log.i("NCMB", o.getString("name"));
                        // 処理
                        String name = o.getString("name");
                        Integer score = o.getInt("score");
                        adapter.add(name + " : " + score.toString());
                    }

                    lv.setAdapter(adapter);
                }
            }
        });

        // **************************************************

    }

    public void btnBackAction(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
