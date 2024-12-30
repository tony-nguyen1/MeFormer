package fr.umontpellier.etu.meformer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {

    private TextView userEmailTextView;

    private Button logoutButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userEmailTextView = findViewById(R.id.userEmailTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Obtenir l'utilisateur connecté
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Si l'utilisateur est connecté, afficher l'email et rendre visible le bouton de déconnexion
            String email = currentUser.getEmail();
            userEmailTextView.setText("Welcome, " + email);
            logoutButton.setVisibility(View.VISIBLE);  // Rendre le bouton visible
        } else {
            // Si aucun utilisateur n'est connecté, afficher "Anonymous"
            userEmailTextView.setText("Welcome, Anonymous");
            logoutButton.setVisibility(View.GONE);  // Garder le bouton caché
        }

        // Gestion de la déconnexion
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();  // Déconnexion de l'utilisateur
            Intent intent = new Intent(HomeActivity.this, LoginActivityGood.class);  // Rediriger vers LoginActivity
            startActivity(intent);
            finish();  // Empêcher de revenir à l'activité précédente
        });
    }
}
