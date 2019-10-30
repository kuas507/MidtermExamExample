# MidtermExamExample
結合Example1~Example6完成一個結合以下幾種功能的APP
1.	計算BMI，並保存本筆資料以及上一筆資料(Shared Preference)。
2.	根據調整BMI顯示調整後之體重。
3.	對本APP進行評價。

# 功能講解
1.  進入APP後，首頁可以輸入身高以及體重以及觀看兩筆過往紀錄，點擊送出後可以進入Activity2。若輸入試算後BMI過重會跳出Alert視窗，該視窗分別三個Button:Web(跳至瀏覽器)、Tel(撥打電話)、繼續(進入Activity2)。

2.  Activity2可以透過Seek bar滑動更改使用者的BMI值大小(+-15%)，滑動後可以看到目標BMI的體重。點擊下方按鈕可以分別進入Activity3(評分)、Activity1(首頁)以及結束目前Activity。

3.  進入Activity3透過Rating bar對本APP進行評價，點擊下方按鈕可以回到Activity1(首頁)、或回到Activity2(試算頁)，回到Activity1可以看到剛剛Rating bar的評分結果。

# 此範例須注意
1.  一個Shared Preference只能儲存一筆資料。
2.  儲存、讀取Shared Preference須放在對應的生命週期裡。

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
