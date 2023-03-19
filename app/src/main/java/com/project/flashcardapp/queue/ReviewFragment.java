package com.project.flashcardapp.queue;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.flashcardapp.R;
import com.project.flashcardapp.databinding.FragmentReviewBinding;


public class ReviewFragment extends Fragment {

private FragmentReviewBinding binding;

private String previousText;

    public ReviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imgBtnFlipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }


        });
        binding.imgBtnFlipCard.setVisibility(View.VISIBLE);
    }

        public void flipCard() {
            if (binding.flipCardView.getAnimation() != null && binding.flipCardView.getAnimation().hasStarted()) {
                // Animation is already running, so return
                return;
            }
            final boolean isFrontVisible = binding.flipCardView.getRotationY() == 0;
            ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(binding.flipCardView, "rotationY", isFrontVisible ? 180f : 0f, isFrontVisible ? 0f : 180f);
            flipAnimator.setDuration(500);
            flipAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {
                    binding.txtQuestion.setText("");
                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {
                    if(previousText == null){
                        previousText = binding.txtQuestion.getText().toString();
                        binding.txtQuestion.setText("This is the answer");
                    }
                    else{
                        binding.txtQuestion.setText(previousText);
                        previousText = null;
                    }
                    binding.imgBtnFlipCard.setVisibility(View.INVISIBLE);
                }
                @Override
                public void onAnimationCancel(@NonNull Animator animation) {
                }
                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {
                }
            });
            flipAnimator.start();

        }


}