package com.example.worldacademy.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.example.worldacademy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLanguageFragment extends AppCompatDialogFragment {


    View view;
    RadioGroup mRadioGroup;
    RadioButton mEnglishRadioButton, mArabicRadioButton;

    public ChangeLanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_language, container, false);

        mRadioGroup = view.findViewById(R.id.radio_group);
        mEnglishRadioButton = view.findViewById(R.id.english_btn);
        mArabicRadioButton = view.findViewById(R.id.arabic_btn);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.english_btn:
                        mEnglishRadioButton.setTextColor(getActivity().getResources().getColor(R.color.basic_color));
                        mArabicRadioButton.setTextColor(getActivity().getResources().getColor(R.color.mainTxtsColor));

                        break;

                    case R.id.arabic_btn:
                        mArabicRadioButton.setTextColor(getActivity().getResources().getColor(R.color.basic_color));
                        mEnglishRadioButton.setTextColor(getActivity().getResources().getColor(R.color.mainTxtsColor));

                        break;
                }
            }
        });

        return view;
    }


}
