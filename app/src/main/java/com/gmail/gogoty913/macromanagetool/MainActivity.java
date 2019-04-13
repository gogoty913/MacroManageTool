package com.gmail.gogoty913.macromanagetool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.gmail.gogoty913.macromanagetool.database.AppDatabase;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyAppOnFragmentInteractionListener {

    FragmentManager fragmentManager;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = AppRepository.getInstance(getApplicationContext()).getDb();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMainDisplay, PmcFragment.newInstance("", ""));
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        new IntentIntegrator(MainActivity.this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null && !result.getContents().isEmpty()) {

            TextView barcodeIdView = (TextView) findViewById(R.id.barcodeId);
            barcodeIdView.setText(result.getContents());

            final AsyncTask<String, Void, FoodInfo> asyncTask = new AppAsyncTask<String, Void, FoodInfo>(this) {
                @Override
                protected FoodInfo doInBackground(String... objects) {
                    return db.foodInfoDao().selectFoodInfo(objects[0]);
                }

                @Override
                protected void onPostExecute(FoodInfo result) {
                    super.onPostExecute(result);
                    if (result != null) {
                        Activity activity = activityWeakReference.get();
                        if (activity != null) {

                            TextView foodNameView = (TextView) activity.findViewById(R.id.foodName);
                            foodNameView.setText(result.foodName);
                            TextView foodCalorieView = (TextView) activity.findViewById(R.id.calorie);
                            foodCalorieView.setText(String.valueOf(result.calorie));
                            TextView foodLipidView = (TextView) activity.findViewById(R.id.lipid);
                            foodLipidView.setText(String.valueOf(result.lipid));
                            TextView foodProteinView = (TextView) activity.findViewById(R.id.protein);
                            foodProteinView.setText(String.valueOf(result.protein));
                            TextView foodCarbohydrateView = (TextView) activity.findViewById(R.id.carbohydrate);
                            foodCarbohydrateView.setText(String.valueOf(result.carbohydrate));
                            TextView foodAllCapacityView = (TextView) activity.findViewById(R.id.allCapacity);
                            foodAllCapacityView.setText(String.valueOf(result.allCapacity));
                            TextView foodDisplayCapacityView = (TextView) activity.findViewById(R.id.displayCapacity);
                            foodDisplayCapacityView.setText(String.valueOf(result.displayCapacity));
                        } else {
                            Log.e("ERROR", "Activityが取得できません");
                        }
                    } else {
                        Log.e("ERROR", "バーコードIDに対応する食べ物がありません");
                    }
                }
            }.execute(result.getContents());
//            asyncTask.execute(result.getContents());

        } else {
            Log.e("ERROR", "バーコードIDが読み取れませんでした。");
        }
    }

    @OnClick({R.id.searchPmcButton})
    void searchPmcButton() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentMainDisplay, PmcFragment.newInstance("", ""));
        fragmentTransaction.commit();
    }

    @OnClick(R.id.insertEatFoodButton)
    void insertEatFoodButton(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentMainDisplay,InsertEatFoodHistoryFragment.newInstance("",""));
        fragmentTransaction.commit();
    }

    @OnClick(R.id.selectFoodButton)
    void selectFoodButton(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentMainDisplay,EatFoodHistoryListFragment.newInstance());
        fragmentTransaction.commit();
    }

    @OnClick(R.id.insertFoodButton)
    void insertFoodButton() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentMainDisplay, InputFoodElementsFragment.newInstance("", ""));
        fragmentTransaction.commit();
    }

    @OnClick(R.id.userStyleButton)
    void futureStyleButton(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentMainDisplay, InputUserInfoFragment.newInstance("", ""));
        fragmentTransaction.commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return false;
    }
}
