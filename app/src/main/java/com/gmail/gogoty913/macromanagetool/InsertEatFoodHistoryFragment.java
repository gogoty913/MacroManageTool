package com.gmail.gogoty913.macromanagetool;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.gogoty913.macromanagetool.database.AppDatabase;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAppOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InsertEatFoodHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertEatFoodHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyAppOnFragmentInteractionListener mListener;

    public InsertEatFoodHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertEatFoodHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertEatFoodHistoryFragment newInstance(String param1, String param2) {
        InsertEatFoodHistoryFragment fragment = new InsertEatFoodHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View resultView = inflater.inflate(R.layout.fragment_eat_food_history, container, false);
        ButterKnife.bind(this,resultView);
        resultView.findViewById(R.id.insertFoodAndEatButton).setVisibility(View.GONE);
        resultView.findViewById(R.id.insertFoodButton).setVisibility(View.GONE);

        String barcodeId = mParam1;
        if(barcodeId !=null && !barcodeId.isEmpty()){
            selectFoodInfo(resultView,barcodeId);
        }


        return resultView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyAppOnFragmentInteractionListener) {
            mListener = (MyAppOnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.searchBarcodeButton)
    void clickSearchBarcodeButton() {
        Log.d("INFO", "buttonClick");
        onButtonPressed(Uri.EMPTY);
    }

    @OnClick(R.id.insertEatButton)
    void clickInsertEatButton(){

        EatFoodsHistory eatFoodsHistory = new EatFoodsHistory();
        eatFoodsHistory.userId = AppRepository.getInstance(getContext()).getUserInfo().userId;
        eatFoodsHistory.eatDay = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(new Date());
        eatFoodsHistory.eatTime = new SimpleDateFormat("HHmm", Locale.JAPAN).format(new Date());
        TextView foodBarcodeIdView = (TextView) this.getView().findViewById(R.id.barcodeId);
        eatFoodsHistory.eatFoodId = foodBarcodeIdView.getText().toString();
        TextView eatValuePercentView = (TextView) this.getView().findViewById(R.id.inputEatValuePercent);
        TextView eatValueGramView = (TextView) this.getView().findViewById(R.id.inputEatValueGram);
        if(!eatValuePercentView.getText().toString().isEmpty()) {
            eatFoodsHistory.eatValuePercent = Integer.parseInt(eatValuePercentView.getText().toString());
        }else {
            eatFoodsHistory.eatValueGram = Integer.parseInt(eatValueGramView.getText().toString());
        }
        AppDatabase db = AppRepository.getInstance(getContext()).getDb();

        final AsyncTask<EatFoodsHistory, Void, Void> asyncTask = new AppAsyncTask<EatFoodsHistory, Void, Void>(this.getActivity()) {
            @Override
            protected Void doInBackground(EatFoodsHistory... eatFoodsHistorys) {
                db.eatFoodsHistoryDao().insertEatFoodsHistory(eatFoodsHistorys[0]);
                return null;
            }
        };

        asyncTask.execute(eatFoodsHistory);
    }

    private void selectFoodInfo(View view,String barcodeId){
        TextView barcodeIdView = (TextView) view.findViewById(R.id.barcodeId);
        barcodeIdView.setText(barcodeId);

        AppDatabase db = AppRepository.getInstance(getContext()).getDb();
        final AsyncTask<String, Void, FoodInfo> asyncTask = new AppAsyncTask<String, Void, FoodInfo>(this.getActivity()) {
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
        }.execute(barcodeId);
    }
}
