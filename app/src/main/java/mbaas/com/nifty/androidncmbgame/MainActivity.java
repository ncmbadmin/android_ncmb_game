package mbaas.com.nifty.androidncmbgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;

public class MainActivity extends AppCompatActivity {
    //カウントタイム設定
    final int COUNT_TIME = 10;
    final Handler myHandler = new Handler();
    Timer myTimer = new Timer();
    TextView tv;
    TextView sv;
    ImageView iv;
    //タイマー用
    int countTimer = -4;
    //タッブスコア
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.txtTimer);
        iv = (ImageView) findViewById(R.id.imageView);
        sv = (TextView) findViewById(R.id.txtScore);

        /********* mBaaS初期化 ****************/
        NCMB.initialize(this.getApplicationContext(), "YOUR_APPLICATION_KEY", "YOUR_CLIENT_KEY");
    }

    /********
     * START ボタンを処理する実装
     ***********/
    public void doStartBtn(View view) {
        //１秒１回タイマーを実行
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                UpdateGUI();
            }
        }, 0, 1000);
    }

    /********
     * HIT ボタンを処理する実装
     ***********/
    public void doHit(View view) {
        if ((countTimer > 0) && (countTimer <= COUNT_TIME)) {
            score++;
            sv.setText("Score: " + String.valueOf(score));
        }
    }

    /********
     * RANKINGを見るボタンを処理する実装
     ***********/
    public void btnRankingAction(View view) {
        Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(intent);
    }

    /********
     * mBaaSデータを保存
     ***********/
    public void saveScore(String name, int score) {

        // **********【問題１】名前とスコアを保存しよう！**********

        //保存するインスタンスを作成
        NCMBObject obj = new NCMBObject("GameScore");

        //値を設定
        obj.put("name", name);
        obj.put("score", score);

        //保存を実施
        obj.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {
                    //保存が失敗した場合の処理
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("登録失敗")
                            .setMessage("エラー :" + e.getMessage())
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    //保存が成功した場合の処理
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("登録成功")
                            .setMessage("保存成功しました")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });
        // **************************************************

    }

    private void UpdateGUI() {
        countTimer++;
        if (countTimer <= COUNT_TIME) {
            myHandler.post(myRunnable);
        } else {
            myTimer.cancel();
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    //名前を入力するためのテキストを準備
                    final EditText input = new EditText(MainActivity.this);
                    //アラートを表示
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("スコア登録")
                            .setView(input)
                            .setMessage("名前を入力してください")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String name;
                                    //入力から名前を取得
                                    name = input.getText().toString();
                                    saveScore(name, score);
                                    //画面を初期化
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                }
            });

        }
    }

    final Runnable myRunnable = new Runnable() {
        public void run() {
            if (countTimer > 0) {
                tv.setText(String.valueOf(countTimer));
            } else {
                if (countTimer < 0) {
                    tv.setText("スタート準備 " + String.valueOf(countTimer + 4));
                } else if (countTimer == 0) {
                    tv.setText("スタート");
                }
            }
        }
    };
}
