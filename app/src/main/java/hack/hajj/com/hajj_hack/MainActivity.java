package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));

    }

}
