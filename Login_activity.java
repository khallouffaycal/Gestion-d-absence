package com.example.absence;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class Login_activity extends AppCompatActivity {
    private Button button;

    private ImageView ensias;
    private ImageView university;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        this.button = (Button) this.findViewById(R.id.button);
        this.ensias = (ImageView) this.findViewById(R.id.ensias);
        this.university = (ImageView) this.findViewById(R.id.university);
        this.email= (EditText)this.findViewById(R.id.email);
        this.password= (EditText)this.findViewById(R.id.password);
        this.button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int activity_main = R.layout.activity_main;


            }
        });

    }


}
