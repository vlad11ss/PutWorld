package com.example.putworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText SignUp_passwordTwo,SignUp_password,SignUp_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        SignUp_email = findViewById(R.id.SignUp_email);
        SignUp_password = findViewById(R.id.SignUp_password);
        SignUp_passwordTwo = findViewById(R.id.SignUp_passwordTwo);
        findViewById(R.id.SignUp_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //проверка поля на пустоту, а также пароль чтобы он был не короче 6 символов:
                if(SignUp_email.getText().toString().isEmpty()||
                    SignUp_password.getText().toString().isEmpty()||
                    SignUp_passwordTwo.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Не все поля заполнены", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(SignUp_password.getText().toString().length() < 6){
                    Toast.makeText(SignUpActivity.this,
                            "Пароль должен состовлять не менее 6 символов",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!SignUp_passwordTwo.getText().toString().equals(SignUp_password.getText().toString())){
                    Toast.makeText(SignUpActivity.this,
                            "Пароли должны совподать",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //регистрация в БД
                mAuth.createUserWithEmailAndPassword(SignUp_email.getText().toString(),SignUp_password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "Готово!",Toast.LENGTH_SHORT).show();
                                    goNext();
                                }else {
                                    Toast.makeText(SignUpActivity.this, "Такой пользователь уже сущесвует",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
    public void goNext(){
        startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
        finish();
    }
}