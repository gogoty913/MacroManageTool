package com.gmail.gogoty913.macromanagetool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.gogoty913.macromanagetool.fragment.listener.MyAppOnFragmentInteractionListener;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAppOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectFoodElementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectFoodElementsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyAppOnFragmentInteractionListener mListener;

    public SelectFoodElementsFragment() {
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
    public static SelectFoodElementsFragment newInstance(String param1, String param2) {
        SelectFoodElementsFragment fragment = new SelectFoodElementsFragment();
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
        ButterKnife.bind(this,resultView);
        return resultView;
    }

    @OnClick(R.id.searchBarcodeButton)
    void clickSearchBarcodeButton(){
        Log.d("INFO","buttonClick");
        onButtonPressed(Uri.EMPTY);
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