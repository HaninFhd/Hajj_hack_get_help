package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity  {
    Context context;

    DatabaseReference databaseReference;
    EditText Email, password;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;
    TextView ForgetPassword ,textViewSignup;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        Email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewSignup.setClickable(true);
        signIn =(Button) findViewById(R.id.buttonSignIn) ;

        String email = Email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (email!=null && pass !=null && !email.equals("")&& ! pass.equals("")) {


        }
        else{

            Toast.makeText(MainActivity.this, "أدخل البريد و كلمة المرور ", Toast.LENGTH_LONG).show();
        }

    }

    public void signinButton(View v) {
        Toast.makeText(MainActivity.this, "sign in", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
    }
    public void newuserButton(View v) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void ForgetPasswordButton(View v) {
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }
}
