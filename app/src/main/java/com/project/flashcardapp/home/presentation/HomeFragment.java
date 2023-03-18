package com.project.flashcardapp.home.presentation;

import static com.project.flashcardapp.home.repo.DeckRepository.DECK;
import static com.project.flashcardapp.home.repo.DeckRepository.USERS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.flashcardapp.databinding.FragmentHomeBinding;
import com.project.flashcardapp.home.dto.DeckModel;
import com.project.flashcardapp.home.presentation.CreateDeckDialog;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private DeckAdapter deckAdapter;
    private String email;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fbCreateDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateDeckDialog creationDialog = new CreateDeckDialog();
                creationDialog.show(requireActivity().getSupportFragmentManager(),"Create Category Selection");
            }
        });
        setupRV();
    }

    private void setupRV() {
        if(mAuth.getCurrentUser()!=null)
        {
            email = mAuth.getCurrentUser().getEmail();
            CollectionReference itemRef = db.collection(USERS).document(email).collection(DECK);
            FirestoreRecyclerOptions<DeckModel> options = new FirestoreRecyclerOptions.Builder<DeckModel>()
                    .setQuery(itemRef.orderBy("name"), DeckModel.class)
                    .build();
            deckAdapter = new DeckAdapter(options);
            binding.deckRecyclerView.setAdapter(deckAdapter);
            binding.deckRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.deckRecyclerView.setHasFixedSize(true);
            deckAdapter.startListening();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(deckAdapter!=null)
            deckAdapter.stopListening();

    }
}