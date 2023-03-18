package com.project.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.flashcardapp.databinding.ActivityAppMainBinding;

public class AppMainActivity extends AppCompatActivity {

    FirebaseAuth mAuth ;
    private ActivityAppMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        mAuth = FirebaseAuth.getInstance();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_navHost_fragment);
        assert navHostFragment != null;
         navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);

        binding.bottomNavigationView.setOnItemSelectedListener(item->
        {
            NavigationUI.onNavDestinationSelected(item,navController);
            return true;

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(AppMainActivity.this, AuthActivity.class));
            finish();
        }
    }
}