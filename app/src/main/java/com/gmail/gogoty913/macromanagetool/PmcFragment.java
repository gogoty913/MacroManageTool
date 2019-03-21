package com.gmail.gogoty913.macromanagetool;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.gogoty913.macromanagetool.database.AppDatabase;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodInfoHistory;
import com.gmail.gogoty913.macromanagetool.entity.EatFoodsHistory;
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.entity.UserInfo;
import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;
import com.gmail.gogoty913.macromanagetool.logic.PmcLogic;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAppOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PmcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PmcFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyAppOnFragmentInteractionListener mListener;

    public PmcFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PmcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PmcFragment newInstance(String param1, String param2) {
        PmcFragment fragment = new PmcFragment();
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

        View resultView = inflater.inflate(R.layout.fragment_pmc, container, false);
        ButterKnife.bind(this,resultView);

        AppDatabase db = AppRepository.getInstance(getContext()).getDb();
        final AsyncTask<Void, Void, List<EatFoodInfoHistory>> selectEatFoodHistoryAsyncTask = new AppAsyncTask<Void, Void, List<EatFoodInfoHistory>>(this.getActivity()) {

            @Override
            protected List<EatFoodInfoHistory> doInBackground(Void... objects) {

                if(AppRepository.getInstance(getContext()).getUserInfo() == null){
                    List<UserInfo> userInfoList = db.userInfoDao().selectAll();
                    if(userInfoList == null || userInfoList.isEmpty()){
                        return null;
                    }

                    AppRepository.getInstance(getContext()).setUserInfo(userInfoList.get(0));
                }

                List<EatFoodInfoHistory> resultList = db.eatFoodsHistoryDao().selectEatFoodsHistoryByDayWithFoodsInfo(new SimpleDateFormat("yyyyMMdd").format(new Date()));
                if(resultList == null || resultList.isEmpty()){
                    return null;
                }
                return resultList;
            }

            @Override
            protected void onPostExecute(List<EatFoodInfoHistory> resultList) {
                super.onPostExecute(resultList);
                if (AppRepository.getInstance(getContext()).getUserInfo() != null) {
                    Activity activity = activityWeakReference.get();
                    if (activity != null) {

                        UserInfo userInfo =AppRepository.getInstance(getContext()).getUserInfo();
                        TextView calorieView = (TextView) activity.findViewById(R.id.aimCalorie);
                        calorieView.setText(Float.toString(userInfo.allCalorie));
                        TextView carbohydratesView = (TextView) activity.findViewById(R.id.aimCarbohydrates);
                        carbohydratesView.setText(Float.toString(userInfo.aimCarbohydrate));
                        TextView proteinView = (TextView) activity.findViewById(R.id.aimProtein);
                        proteinView.setText(Float.toString(userInfo.aimProtein));
                        TextView lipidView = (TextView) activity.findViewById(R.id.aimLipid);
                        lipidView.setText(Float.toString(userInfo.aimLipid));

                        if (resultList != null && !resultList.isEmpty()) {
                            float intakeCalorie = (float)PmcLogic.intakeCalorie(resultList);
                            float intakeCarbon = (float)PmcLogic.intakeCarbon(resultList);
                            float intakeProtein = (float)PmcLogic.intakeProtein(resultList);
                            float intakeLipid = (float)PmcLogic.intakeLipid(resultList);

                            Log.d("Debug","カロリー："+intakeCalorie
                                    +", 炭水化物："+intakeCarbon+", 蛋白質："+intakeProtein
                                    +", 脂質："+intakeLipid);

                            TextView intakeCalorieView = (TextView) activity.findViewById(R.id.intakeCalorie);
                            intakeCalorieView.setText(Float.toString(userInfo.allCalorie - intakeCalorie));
                            TextView intakeCarbohydratesView = (TextView) activity.findViewById(R.id.intakeCarbohydrates);
                            intakeCarbohydratesView.setText(Float.toString(userInfo.aimCarbohydrate-intakeCarbon));
                            TextView intakeProteinView = (TextView) activity.findViewById(R.id.intakeProtein);
                            intakeProteinView.setText(Float.toString(userInfo.aimProtein-intakeProtein));
                            TextView intakeLipidView = (TextView) activity.findViewById(R.id.intakeLipid);
                            intakeLipidView.setText(Float.toString(userInfo.aimLipid-intakeLipid));

                        }
                    }else {
                        Log.e("ERROR", "Activityが取得できません");
                    }
                }else{
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.fragmentMainDisplay, InputUserInfoFragment.newInstance("", ""));
                    fragmentTransaction.commit();
                }
            }

        }.execute();

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
                    + " must implement MyAppOnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}

