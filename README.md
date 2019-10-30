# MidtermExamExample
結合Example1~Example6完成一個結合以下幾種功能的APP
1.	計算BMI，並保存本筆資料以及上一筆資料(Shared Preference)。
2.	根據調整BMI顯示調整後之體重。
3.	對本APP進行評價。

# 功能講解
1. 進入APP後，MainActivity由四個元件組成 :
	<ul>
   	 <li><p><code>EditText</code>分別輸入身高跟體重。
   	 <li><p><code>Button</code>送出結果並跳至Activity2，如果使用者輸入BMI結果大於30系統跳出<code>AlertDialog</code>警告視窗，該視窗分別三個<code>Button</code>:Web(跳至瀏覽器)、Tel(撥打電話)、繼續(進入Activity2)。
  	 <li><p><code>TextView</code>保存紀錄兩筆紀錄。
   	 <li><p><code>SeekBar</code>顯示本APP的評價。
  	</ul>
 

2.	Activity2主要由三個元件組成 :
      <ul>
        <li><p><code>SeekBar</code>可以拖移更改BMI值的大小(+-15%)。
        <li><p><code>TextView</code>分別顯示<code>SeekBar</code>拖移過程所更改BMI後的體重計算結果，以及SeekBar增減後的BMI值。
        <li><p><code>Button</code>分別可以跳回首頁、進入評價頁以及結束應用程式。
      </ul>

3.  Activity3主要由兩個元件組成 :
       <ul>
       	<li><p><code>RatingBar</code>可對APP進行評價。
          <li><p><code>Button</code>分別跳回首頁以及回Activity2
       </ul>

# 此範例須注意
1.  一個<code>SharedPreference</code>只能儲存一筆資料。
2.  儲存、讀取<code>SharedPreference</code>須放在對應的生命週期裡。

```Java
@Override
    public void onPause() {
        super.onPause();
        //save Preferences
    }
```

```Java
 @Override
    public void onResume() {
        super.onResume();
        //read Preferences
    }
```
3.  可以使用 ```ratingBar.setVisibility(View.VISIBLE);``` 隱藏剛開啟應用程式未評分的Rating bar。(設Flag判斷是否已經評分過)。
