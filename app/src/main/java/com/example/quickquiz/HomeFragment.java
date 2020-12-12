package com.example.quickquiz;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quickquiz.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    int categoryValue = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);
        super.onViewCreated(view, savedInstanceState);
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass the argument of title during navigate
                Bundle bundle = new Bundle();
                bundle.putString("qTitle",getTitle(categoryValue));
                navController.navigate(R.id.action_homeFragment_to_q1Fragment,bundle);
            }
        });

        binding.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.option1);

            }
        });
        binding.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.option2);
            }
        });
        binding.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.option3);
            }
        });
    }

    private void selectedResource(TextView option) {
        option.setBackgroundResource(R.drawable.active_category_back);
        option.setTextColor(Color.WHITE);

        if (option == binding.option1) {
            categoryValue = 1;
            binding.option2.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option2.setTextColor(Color.BLACK);

            binding.option3.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option3.setTextColor(Color.BLACK);
        } else if (option == binding.option2) {
            categoryValue = 2;
            binding.option1.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option1.setTextColor(Color.BLACK);

            binding.option3.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option3.setTextColor(Color.BLACK);
        } else {
            categoryValue = 3;
            binding.option1.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option1.setTextColor(Color.BLACK);

            binding.option2.setBackgroundResource(R.drawable.inactive_category_back);
            binding.option2.setTextColor(Color.BLACK);
        }
    }

    String getTitle(int category){
        String value = "";
        switch (category){
            case 1:
                value = "Java";
                break;
            case 2:
                value = "OOP";
                break;
            case 3:
                value = "C";
                break;
        }
        return value;
    }
}