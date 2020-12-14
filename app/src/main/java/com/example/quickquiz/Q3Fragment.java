package com.example.quickquiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quickquiz.databinding.FragmentQ2Binding;
import com.example.quickquiz.databinding.FragmentQ3Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Q3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Q3Fragment extends Fragment {
    private FragmentQ3Binding binding;
    private Context context;
    private CountDownTimer countDownTimer;
    private int selectedOption = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Q3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Q3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Q3Fragment newInstance(String param1, String param2) {
        Q3Fragment fragment = new Q3Fragment();
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
        context = container.getContext();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_q3, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        binding.titleTV.setText(Common.questionTitle);

        Common.currentQuestion++;
        //countdown time
        countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.timeRemainTV.setText("remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (selectedOption == 4) {
                    Common.score++;
                }
                navController.navigate(R.id.action_q3Fragment_to_resultFragment);
            }
        }.start();


        binding.runningQuestionNoTV.setText("Question: " + Common.currentQuestion + "/" + Common.totalQuestion);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOption == 4) {
                    Common.score++;
                }
                countDownTimer.cancel();
                navController.navigate(R.id.action_q3Fragment_to_resultFragment);
                Toast.makeText(context, "Selected opt: " + selectedOption, Toast.LENGTH_SHORT).show();
            }
        });
        binding.tick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.tick1);
            }
        });
        binding.tick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.tick2);
            }
        });
        binding.tick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.tick3);
            }
        });
        binding.tick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedResource(binding.tick4);
            }
        });

    }

    private void selectedResource(View tick) {
        tick.setBackgroundResource(R.drawable.option_back_active);
        if (tick == binding.tick1) {
            selectedOption = 1;
            binding.opt1TV.setTextColor(Color.WHITE);

            binding.tick2.setBackgroundResource(R.drawable.option_back);
            binding.opt2TV.setTextColor(Color.BLACK);

            binding.tick3.setBackgroundResource(R.drawable.option_back);
            binding.opt3TV.setTextColor(Color.BLACK);

            binding.tick4.setBackgroundResource(R.drawable.option_back);
            binding.opt4TV.setTextColor(Color.BLACK);
        } else if (tick == binding.tick2) {
            selectedOption = 2;
            binding.opt2TV.setTextColor(Color.WHITE);

            binding.tick1.setBackgroundResource(R.drawable.option_back);
            binding.opt1TV.setTextColor(Color.BLACK);

            binding.tick3.setBackgroundResource(R.drawable.option_back);
            binding.opt3TV.setTextColor(Color.BLACK);

            binding.tick4.setBackgroundResource(R.drawable.option_back);
            binding.opt4TV.setTextColor(Color.BLACK);
        } else if (tick == binding.tick3) {
            selectedOption = 3;
            binding.opt3TV.setTextColor(Color.WHITE);

            binding.tick1.setBackgroundResource(R.drawable.option_back);
            binding.opt1TV.setTextColor(Color.BLACK);

            binding.tick2.setBackgroundResource(R.drawable.option_back);
            binding.opt2TV.setTextColor(Color.BLACK);

            binding.tick4.setBackgroundResource(R.drawable.option_back);
            binding.opt4TV.setTextColor(Color.BLACK);
        } else {
            selectedOption = 4;
            binding.opt4TV.setTextColor(Color.WHITE);

            binding.tick1.setBackgroundResource(R.drawable.option_back);
            binding.opt1TV.setTextColor(Color.BLACK);

            binding.tick2.setBackgroundResource(R.drawable.option_back);
            binding.opt2TV.setTextColor(Color.BLACK);

            binding.tick3.setBackgroundResource(R.drawable.option_back);
            binding.opt3TV.setTextColor(Color.BLACK);
        }
    }
}