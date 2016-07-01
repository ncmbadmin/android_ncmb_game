# 【Swift問題集】『オンラインランキング機能を作ってみよう！「連打ゲーム」』

![RendaGame](/readme-img/RendaGame.png)

## コンテンツ概要

* [ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)の機能『データストア』を学習するための問題集です
 * [ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)の利用登録（無料）が必要です。
* 問題用プロジェクトにはオンラインランキング機能が実装されていない状態の「連打ゲーム」です
 * 既に実装済みの[ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)を利用するための準備（SDK導入など）方法の詳細は[こちら](http://mb.cloud.nifty.com/doc/current/introduction/quickstart_android.html)をご覧ください。

## 問題について

* 問題は２問あります
* ２問クリアすると「連打ゲーム」にオンラインランキング機能を実装したアプリが完成します
* 問題を取り組む上で必要な開発環境は以下です
 * AndroidStudio ver.1.4 以上

## 問題に取り組む前の準備

### プロジェクトのダウンロード

▼問題用プロジェクト▼

[__「連打ゲーム (Taping Game)」__](https://github.com/ncmbadmin/android_ncmb_game/archive/master.zip)

1. 上記リンクをクリックしてzipファイルをローカルに保存します
1. zipファイルを解凍して、AndroidStudioを開き,「Open an existing AndroidStudio project」を選択し、解凍したプロジェクトを開きます。
1. プロジェクトをビルドし、「連打ゲーム」で遊んでみましょう

#### 「連打ゲーム」の操作方法

1. 「START」ボタンをタップします
2. 「3」,「2」,「1」とカウントダウンし、「スタート！」からタイムアップ！の10秒間「◎」の部分がタップできるようになります
3. 10秒間の間に何回タップできるかを競う単純なゲームです
4. 10秒経つと名前を入力するアラートが表示されますので、入力し「OK」をクリックします
5. 画面に名前とスコアが表示されます

※__注意__：問題に取り組む前の状態では「SHOW RANKING」ボタンをタップしてもランキングは表示されません

### アプリの新規作成とAPIキーの設定

![mBaaS](/readme-img/mBaaS.png)

*  [ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)にログインしアプリの新規作成を行います
 * アプリ名はわかりやすいものにしましょう。例）「renda」
* アプリが作成されるとAPIキーが２種類（アプリケーションキーとクライアントキー）発行されます
 * 次で使用します。

![AndroidStudio](/readme-img/icon_androidstudio.png)

* `MainActivity.java`の`onCreate`を編集します
* 先程[ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)のダッシュボード上で確認したAPIキーを貼り付けます

![問題0-1](/readme-img/0-1.png)

* それぞれ`YOUR_APPLICATION_KEY`と`YOUR_CLIENT_KEY`の部分を書き換えます
 * このとき、ダブルクォーテーション（`"`）を消さないように注意してください！

## __【問題１】__：名前とスコアの保存をしてみよう！
`MainActivity.java`を開きます。下図の__`saveScore`__メソッドを編集し、引数の__`name`__（アラートで入力した名前）と__`score`__（連打ゲームでタップした回数）の値をmBaaSに保存する処理をコーディングしてください

![問題1-1](/readme-img/1-1.png)

* データストアに保存先クラスを作成します
 * クラス名は「`GameScore`」としてください
* `name`を保存するフィールドを「`name`」、`score`を保存するフィールドを「`score`」として保存してください

### ヒント

* [ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)のキュメントのドキュメントページをご活用ください
 * [データストア（Android）：基本的な使い方](http://mb.cloud.nifty.com/doc/current/datastore/basic_usage_android.html)

### コーディング後の作業
問題１のコーディングが完了したら、下記の作業を行います

__【作業1-1】__それぞれ該当する箇所に以下の処理を追記して、実行時にアラートを表示できるようにします

* 保存に失敗した場合の処理を行う箇所に追記

```java
// 保存に失敗した場合の処理
new AlertDialog.Builder(MainActivity.this)
              .setTitle("保存失敗")
              .setMessage("保存に失敗しました。エラーコード: " + e.getMessage())
              .setPositiveButton("OK", null)
              .show();
```

* 保存に成功した場合の処理を行う箇所に追記

```java
//保存が成功した場合の処理
new AlertDialog.Builder(MainActivity.this)
            .setTitle("保存成功")
            .setMessage("保存に成功しました。")
            .setPositiveButton("OK", null)
            .show();
```

__【作業1-2】__エミュレーターで実行、「Start」ボタンを押してゲームを遊びます

* 名前を入力し、「OK」がクリックされると【問題１】で作成した`saveScore`メソッドが呼ばれ、データが保存されます
* このとき下記のいずれかのログが出力されます

 * 「`保存に成功しました。`」の場合は保存成功です
 * 「`保存に失敗しました。エラーコード : ************`」の場合は保存失敗です

※エラーコードが出た場合は[こちら](http://mb.cloud.nifty.com/doc/current/rest/common/error.html#REST%20API%E3%81%AE%E3%82%A8%E3%83%A9%E3%83%BC%E3%82%B3%E3%83%BC%E3%83%89%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)で確認できます

### 答え合わせ

▼答えはこちら▼

[__【問題１】解答__](https://github.com/ncmbadmin/android_ncmb_game/blob/AnswerProject/Answer1.md)


## __【問題２】__：ランキングを表示しよう！
`RankingActivity.java`を開きます。下図の`checkRanking`メソッドを編集し、データストアの`GameScore`クラスに保存した`name`と`score`のデータを`score`の降順（スコアの高い順）で検索・取得する処理をコーディングしてください

![問題2-1](/readme-img/2-1.png)

* 検索データ件数は５件とします

### ヒント

* [ニフティクラウドmobile backend](http://mb.cloud.nifty.com/)のキュメントのドキュメントページをご活用ください
 * [データストア（Android）：基本的な使い方](http://mb.cloud.nifty.com/doc/current/datastore/basic_usage_android.html)
 *  [データストア（Android）：ランキングを作る](http://mb.cloud.nifty.com/doc/current/datastore/ranking_android.html)

### コーディング後の作業
問題２のコーディングが完了したら、下記の作業を行います

__【作業2-1】__該当する箇所に以下の処理を追記して、実行時にAndroidStudio上にログを表示できるようにします

* 検索に失敗した場合の処理を行う箇所に追記

```java
// 検索に失敗した場合の処理
Log.e("NCMB", "検索に失敗しました。エラーコード:" + e.getMessage());
```

* 検索に成功した場合の処理を行う箇所に追記

```java
// 検索に成功した場合の処理
Log.i("NCMB", "検索に成功しました。");
```

__【作業2-2】__エミュレーターで実行し、「ランキングを見る」ボタンをタップします
* 画面起動後、`checkRanking`メソッドが呼ばれ、【問題１】で保存されたデータが検索・取得されます
* このとき下記のいずれかのログが出力されます

 * 「`検索に成功しました。`」が表示された場合は検索成功です
 * 「`検索に失敗しました。エラーコード:************`」が表示された場合は検索失敗です

※エラーコードが出た場合は[こちら](http://mb.cloud.nifty.com/doc/current/rest/common/error.html#REST%20API%E3%81%AE%E3%82%A8%E3%83%A9%E3%83%BC%E3%82%B3%E3%83%BC%E3%83%89%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)で確認できます

* 検索の状態（成功・失敗）に関係なく、「SHOW RANKING」ボタンをタップしても、まだランキングは表示されません

__【作業2-3】__検索に成功したら、該当する箇所に以下の処理を追記して、取得した値から必要なデータを取り出し、ランキング画面へ反映させます

* 検索に成功した場合の処理を行う箇所に追記

```java
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
            adapter.add(name + " さん : " + score.toString() + " (point)");
        }

        lv.setAdapter(adapter);
```

__【作業2-4】__エミュレーターで実行、「ランキングを見る」ボタンを押します

* 先ほどのスコアが表示されれば完成です！おめでとうございます★

### 答え合わせ

▼答えはこちら▼

[__【問題２】解答__](https://github.com/ncmbadmin/android_ncmb_game/blob/AnswerProject/Answer2.md)

## 参考

* 問題の回答を実装した完全なプロジェクトをご用意しています

▼完成版プロジェクト▼

[__「【完成版】連打ゲーム」__](https://github.com/ncmbadmin/android_ncmb_game/archive/AnswerProject.zip)

* APIキーを設定してご利用ください
