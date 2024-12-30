package fr.umontpellier.etu.meformer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;

public class LoginActivityGood extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, guestButton;
    private TextView registerLink; // Ajout du lien vers l'inscription
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialiser Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink); // Référence au TextView
        guestButton = findViewById(R.id.guestButton);  // Nouveau bouton pour l'accès en tant qu'invité

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivityGood.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });

        // Ajout de l'écouteur de clic pour le lien
        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivityGood.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Gestion du clic pour l'accès en tant qu'invité
        guestButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivityGood.this, HomeActivity.class);
            startActivity(intent);
            finish();  // Empêcher de revenir à l'écran de connexion
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivityGood.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Rediriger vers HomeActivity
                        Intent intent = new Intent(LoginActivityGood.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Empêcher de revenir à la page de connexion
                    } else {
                        Toast.makeText(LoginActivityGood.this, "Login failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
