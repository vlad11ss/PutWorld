package com.example.putworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {

    private EditText LogIn_email,LogIn_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //Проверка, вошел ли пользователь в систему
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            goNext();

        LogIn_email = findViewById(R.id.LogIn_email);
        LogIn_password = findViewById(R.id.LogIn_password);
        findViewById(R.id.LogIn_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //проверка поля на пустоту, а также пароль чтобы он был не короче 6 символов:

                if(LogIn_email.getText().toString().isEmpty()||
                    LogIn_email.getText().toString().isEmpty()){
                    Toast.makeText(LogInActivity.this,
                            "Не все поля заполнены",
                                Toast.LENGTH_SHORT).show();
                    return;
                }
                if(LogIn_password.getText().toString().length() < 6){
                    Toast.makeText(LogInActivity.this,
                            "Пароль должен состовлять не менее 6 символов",
                                Toast.LENGTH_SHORT).show();
                    return;
                }
                //попытка войти в бд

                mAuth.signInWithEmailAndPassword(LogIn_email.getText().toString(),
                        LogIn_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogInActivity.this, "Готово!",Toast.LENGTH_SHORT).show();
                            goNext();
                        }else {
                            Toast.makeText(LogInActivity.this, "Такого пользователя не сущесвует",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        // инициализация текста как переход на экран SignUp, если нету аккаунта
        TextView text_SignUp = (TextView)findViewById(R.id.text_SignUp);
        text_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,SignUpActivity.class));
                finish();
            }
        });

    }
    public void goNext(){
        startActivity(new Intent(LogInActivity.this,HomeActivity.class));
        finish();
    }
}