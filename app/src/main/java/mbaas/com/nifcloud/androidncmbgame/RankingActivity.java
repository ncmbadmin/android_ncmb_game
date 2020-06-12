package mbaas.com.nifcloud.androidncmbgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_page);
        checkRanking();
    }

    protected void checkRanking() {

        // **********【問題２】ランキングを表示しよう！**********













        // **************************************************

    }

    public void btnBackAction(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
