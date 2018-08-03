package hack.hajj.com.hajj_hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity implements
        View.OnClickListener {

    private EditText editTextEmailForResetPassword;
    private Button buttonEmailForResetPassword;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reset_password);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmailForResetPassword = (EditText) findViewById(R.id.editTextEmailForResetPassword);
        buttonEmailForResetPassword = (Button) findViewById(R.id.buttonEmailForResetPassword);

        buttonEmailForResetPassword.setOnClickListener(this);


    }

    public void resetPassword(){

        String email = editTextEmailForResetPassword.getText().toString().trim();

        //reset password by user email
        /*
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPassword.this, "تم إرسال رسالة إعادة تعيين كلمة المرور على بريدك", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPassword.this, "تأكد من بريدك الإلكتروني", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        */
    }


    @Override
    public void onClick(View v) {
        if (v==buttonEmailForResetPassword){
            //resetPassword();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

    }
}
