package com.gmail.gogoty913.macromanagetool;

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
import com.gmail.gogoty913.macromanagetool.entity.UserInfo;
import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;
import com.gmail.gogoty913.macromanagetool.logic.PmcLogic;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAppOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputUserInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputUserInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyAppOnFragmentInteractionListener mListener;

    public InputUserInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputUserInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputUserInfoFragment newInstance(String param1, String param2) {
        InputUserInfoFragment fragment = new InputUserInfoFragment();
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
        View resultView = inflater.inflate(R.layout.fragment_input_user_info, container, false);
        ButterKnife.bind(this,resultView);
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

    @OnClick(R.id.selectUserButton)
    void clickSelectUserButton(){
        AppDatabase db = AppRepository.getInstance(getContext()).getDb();
        final AsyncTask<Void, Void, UserInfo> asyncTask = new AppAsyncTask<Void, Void, UserInfo>(this.getActivity()) {
            @Override
            protected UserInfo doInBackground(Void... userInfos) {

                if(AppRepository.getInstance(getContext()).getUserInfo() != null){
                    return AppRepository.getInstance(getContext()).getUserInfo();
                }

                return db.userInfoDao().selectAll().get(0);
            }

            @Override
            protected void onPostExecute(UserInfo result) {
                super.onPostExecute(result);
                AppRepository.getInstance(getContext()).setUserInfo(result);


            }
        };
    }

    @OnClick(R.id.updateUserButton)
    void clickUpdateUserButton(){
        UserInfo userInfo = setUserInfo(this.getView());

        Log.i("INFO", userInfo.nickname);
        AppDatabase db = AppRepository.getInstance(getContext()).getDb();
        final AsyncTask<UserInfo, Void, Void> asyncTask = new AppAsyncTask<UserInfo, Void, Void>(this.getActivity()) {
            @Override
            protected Void doInBackground(UserInfo... userInfos) {
                //ダサい
                db.userInfoDao().deleteUserInfo(userInfos[0]);
                db.userInfoDao().insertUserInfo(userInfos[0]);
                AppRepository.getInstance(getContext()).setUserInfo(userInfos[0]);
                return null;
            }
        };
        asyncTask.execute(userInfo);
    }

    @OnClick(R.id.insertUserButton)
    void clickInsertUserButton(){
        UserInfo userInfo = setUserInfo(this.getView());

        Log.i("INFO", userInfo.nickname);
        AppDatabase db = AppRepository.getInstance(getContext()).getDb();
        final AsyncTask<UserInfo, Void, Void> asyncTask = new AppAsyncTask<UserInfo, Void, Void>(this.getActivity()) {
            @Override
            protected Void doInBackground(UserInfo... userInfos) {
                //ダサい
                db.userInfoDao().insertUserInfo(userInfos[0]);
                AppRepository.getInstance(getContext()).setUserInfo(userInfos[0]);
                return null;
            }
        };
        asyncTask.execute(userInfo);
    }

    private UserInfo setUserInfo(View view){
        UserInfo userInfo = new UserInfo();

        TextView nickNameTextView = (TextView)view.findViewById(R.id.nickName);
        userInfo.nickname = nickNameTextView.getText().toString();
        TextView genderStyleTextView = (TextView)view.findViewById(R.id.genderStyle);
        userInfo.style = Integer.parseInt(genderStyleTextView.getText().toString());
        TextView ageTextView = (TextView)view.findViewById(R.id.age);
        userInfo.age = Integer.parseInt(ageTextView.getText().toString());
        TextView weightTextView = (TextView)view.findViewById(R.id.weight);
        userInfo.weight = Integer.parseInt(weightTextView.getText().toString());
        TextView heightTextView = (TextView)view.findViewById(R.id.height);
        userInfo.height = Integer.parseInt(heightTextView.getText().toString());
        TextView activityValueTextView = (TextView)view.findViewById(R.id.activityValue);
        userInfo.activeMode = Integer.parseInt(activityValueTextView.getText().toString());
        TextView aimTextView = (TextView)view.findViewById(R.id.aim);
        userInfo.future = Integer.parseInt(aimTextView.getText().toString());
        PmcLogic.setAimPMC(userInfo);

        return userInfo;
    }
}
