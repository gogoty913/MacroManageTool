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
import com.gmail.gogoty913.macromanagetool.entity.FoodInfo;
import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;
import com.gmail.gogoty913.macromanagetool.repository.AppRepository;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAppOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputFoodElementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFoodElementsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View fragmentView;

    private MyAppOnFragmentInteractionListener mListener;

    public InputFoodElementsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFoodElementsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFoodElementsFragment newInstance(String param1, String param2) {
        InputFoodElementsFragment fragment = new InputFoodElementsFragment();
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
        View resultView = inflater.inflate(R.layout.fragment_input_food_elements, container, false);
        ButterKnife.bind(this, resultView);
        fragmentView = resultView;
        resultView.findViewById(R.id.insertEatButton).setVisibility(View.GONE);
        return resultView;
    }

    @OnClick(R.id.searchBarcodeButton)
    void clickSearchBarcodeButton() {
        Log.d("INFO", "buttonClick");
        onButtonPressed(Uri.EMPTY);
    }

    @OnClick(R.id.insertFoodButton)
    void clickInsertFoodButton() {

        FoodInfo foodInfo = new FoodInfo();
        TextView foodBarcodeIdView = (TextView) this.getView().findViewById(R.id.barcodeId);
        foodInfo.barcodeId = foodBarcodeIdView.getText().toString();
        TextView foodNameView = (TextView) this.getView().findViewById(R.id.foodName);
        foodInfo.foodName = foodNameView.getText().toString();
        TextView foodCalorieView = (TextView) this.getView().findViewById(R.id.calorie);
        foodInfo.calorie = Double.valueOf(foodCalorieView.getText().toString());
        TextView foodLipidView = (TextView) this.getView().findViewById(R.id.lipid);
        foodInfo.lipid = Double.valueOf(foodLipidView.getText().toString());
        TextView foodProteinView = (TextView) this.getView().findViewById(R.id.protein);
        foodInfo.protein = Double.valueOf(foodProteinView.getText().toString());
        TextView foodCarbohydrateView = (TextView) this.getView().findViewById(R.id.carbohydrate);
        foodInfo.carbohydrate = Double.valueOf(foodCarbohydrateView.getText().toString());
        TextView foodAllCapacityView = (TextView) this.getView().findViewById(R.id.allCapacity);
        foodInfo.allCapacity = Integer.valueOf(foodAllCapacityView.getText().toString());
        TextView foodDisplayCapacityView = (TextView) this.getView().findViewById(R.id.displayCapacity);
        foodInfo.displayCapacity = Integer.valueOf(foodDisplayCapacityView.getText().toString());

        Log.i("INFO", foodInfo.foodName);

        AppDatabase db = AppRepository.getInstance(getContext()).getDb();

        final AsyncTask<FoodInfo, Void, Void> asyncTask = new AppAsyncTask<FoodInfo, Void, Void>(this.getActivity()) {
            @Override
            protected Void doInBackground(FoodInfo... foodInfos) {
                if(db.foodInfoDao().selectFoodInfo(foodInfos[0].barcodeId) == null) {
                    db.foodInfoDao().InsertFoodsInfo(foodInfos);
                }
                return null;
            }
        };

        asyncTask.execute(foodInfo);

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