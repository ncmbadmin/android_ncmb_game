## 【問題１】答え合わせ

### ニフクラ mobile backend上での確認
![mBaaS](/readme-img/mBaaS.png)

* 保存されたデータを確認しましょう
 * 「データストア」をクリックすると、「`GameScore`」クラスにデータが登録されていることが確認できます。

![ans1-1](/readme-img/ans1-1.png)

* 上図はスコアが35連打で名前を「あいうえお」とした場合の例です。

### コードの答え合わせ

![Android](/readme-img/icon_androidstudio.png)

* 模範解答は以下です

```java
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
             Log.e("NCMB", "保存に失敗しました。エラー:" + e.getMessage());
         } else {
             //保存が成功した場合の処理
             Log.i("NCMB", "保存に成功しました。");
         }
     }
 });
 // **************************************************
```
