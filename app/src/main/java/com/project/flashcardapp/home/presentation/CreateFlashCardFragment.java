package com.project.flashcardapp.home.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.flashcardapp.R;
import com.project.flashcardapp.databinding.FragmentCreateFlashCardBinding;
import com.project.flashcardapp.home.domain.FlashCardViewModel;
import com.project.flashcardapp.home.dto.FlashCardModel;


public class CreateFlashCardFragment extends Fragment {

    FragmentCreateFlashCardBinding binding;
    private FlashCardViewModel flashCardViewModel;
    private NavController navController;
    private String question, answer;
    private static final String TAG = "CreateFlashCardFragment";

    public CreateFlashCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentCreateFlashCardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        flashCardViewModel = new ViewModelProvider(requireActivity()).get(FlashCardViewModel.class);
        binding.flashCardDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question = binding.edtQuestion.getText().toString();
                answer = binding.edtAnswer.getText().toString();
                String deckId = requireArguments().getString("DECK_ID");
                FlashCardModel flashCardModel = new FlashCardModel();
                flashCardModel.setQuestion(question);
                flashCardModel.setAnswer(answer);
                flashCardModel.setNextReviewDate("- -");
                flashCardModel.setReviewStatus("- -");
                flashCardModel.setDeckId(deckId);
                flashCardViewModel.createFlashCard(flashCardModel);
                flashCardModel.setDeckId(deckId);
                Log.d(TAG, deckId);

                Bundle bundle  = new Bundle();
                bundle.putString("DECK_ID",deckId);
                navController.navigate(R.id.action_createFlashCardFragment_to_flashCardFragment,bundle);


            }
        });

    }
}