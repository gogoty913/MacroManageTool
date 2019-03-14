package com.gmail.gogoty913.macromanagetool;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AlertDialog;

public abstract class AppAsyncTask<T, S, U> extends AsyncTask<T, S, U> {

    WeakReference<Activity> activityWeakReference;
    private Dialog appDBDialog;

    public AppAsyncTask(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        //バックグラウンド処理開始前にUIスレッドで実行される。
        //ダイアログの生成などを行う。
        appDBDialog = new AlertDialog.Builder(activityWeakReference.get()).setView(R.layout.dialog_progress_db_load).create();
        appDBDialog.show();

    }

    @Override
    protected void onPostExecute(U result) {
        //doInBackgroundが終了するとUIスレッドで実行される。
        //ダイアログの消去などを行う。
        //doInBackgroundの結果を画面表示に反映させる処理もここに記述。

        if (appDBDialog != null && appDBDialog.isShowing()) {
            appDBDialog.dismiss();
        }

    }
}
