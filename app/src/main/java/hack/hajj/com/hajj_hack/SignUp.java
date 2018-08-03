package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hanin_5p on 01/08/18.
 */

public class SignUp extends AppCompatActivity  {

    EditText emailTextView, passTextView;
    Button signUpButton;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    RadioGroup radioGroup;
    Context context;
    String UserType;
    RadioButton drRadioButton , hajjRadioButton ;
    private TextView userNametextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        context = getApplicationContext();
        firebaseAuth = FirebaseAuth.getInstance();
        // getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        drRadioButton = (RadioButton) findViewById(R.id.drRadioButton);
        hajjRadioButton = (RadioButton) findViewById(R.id.hajjRadioButton);

        radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.drRadioButton:
                        UserType ="Paramedic";

                        break;
                    case R.id.hajjRadioButton:
                        UserType ="hajj";
                        break;
                }
            }
        });



        userNametextView =(TextView) findViewById(R.id.userNametextView);
        emailTextView = (EditText) findViewById(R.id.emailEditText);
        passTextView = (EditText) findViewById(R.id.passEditText);

        signUpButton = (Button) findViewById(R.id.signUpButton);

    }


    private void registerUser(final View v) {

        //getting email and password from edit texts
        String email = emailTextView.getText().toString().trim();
        String password = passTextView.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "أدخل البريد الإلكتروني", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "أدخل كلمة المرور", Toast.LENGTH_LONG).show();
            return;
        }

        //sign up user in Firebase Authentication

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(SignUp.this, "البريد أو كلمة المرور غير صحيحة" + UserType, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void SignUpButton(View v) {
        //getting email and password from edit texts
        String email = emailTextView.getText().toString().trim();
        String password = passTextView.getText().toString().trim();
        String name = userNametextView.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "أدخل البريد الإلكتروني", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "أدخل كلمة المرور", Toast.LENGTH_LONG).show();
            return;
        }

        //sign up user in Firebase Authentication


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //checking if success
                if (task.isSuccessful()) {
                    continueSignup();
                    //startActivity(new Intent(getApplicationContext(), ClientActivity.class));
                    finish();
                    Toast.makeText(SignUp.this, " !!! DONE !!! ", Toast.LENGTH_LONG).show();
                } else {
                    //display some message here
                    task.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "Registration Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
    private void continueSignup(){

        // getting the current logged in user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String name = userNametextView.getText().toString().trim();


        //saving data to firebase database
        databaseReference.child("Users").child(user.getUid()).setValue("Client");
        databaseReference.child("Client").child(user.getUid()).child("name").setValue(name);


    }

    private void regUser() {
       if( databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(UserType).isSuccessful()) {
           if (UserType.equals("Paramedic")) {
               startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
           }
           if (UserType.equals("hajj")) {
               startActivity(new Intent(getApplicationContext(), HajjActivity.class));
           }
       }

    }


}
