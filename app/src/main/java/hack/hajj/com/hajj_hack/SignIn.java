package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hanin_5p on 03/08/18.
 */


public class SignIn extends AppCompatActivity  {
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
        context = getApplicationContext();
        firebaseAuth = FirebaseAuth.getInstance();
        // getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseUser = firebaseAuth.getCurrentUser();
        try {
            if (!firebaseUser.getUid().isEmpty()) {

                databaseReference.child("Users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String type = dataSnapshot.getValue().toString();
                        if (type.equals("Paramedic")) {
                            Paramedics();
                        }
                        if (type.equals("hajje")) {
                            hajje();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }catch (Exception e){

        }


        Email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewSignup.setClickable(true);

    }

    private void hajje() {

        startActivity(new Intent(getApplicationContext(), HajjActivity.class));
        finish();
    }

    private void Paramedics() {

        startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
        finish();

    }

    public void signinButton(View v) {

        String email = Email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (email!=null && pass !=null && !email.equals("")&& ! pass.equals("")) {
            firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if the task is successfull
                            if (task.isSuccessful()) {

                                //start the profile activity
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                databaseReference.child("Users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        try {
                                            String type = dataSnapshot.getValue().toString();
                                            if (type.equals("Paramedic")) {
                                                Paramedics();
                                            }
                                            if (type.equals("hajj")) {
                                                hajje();
                                            }
                                        } catch (Exception e) {
                                            Toast.makeText(SignIn.this, " error in sign in", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            } else {

                            }

                        }
                    });
        }
        else{

            Toast.makeText(SignIn.this, "أدخل البريد و كلمة المرور ", Toast.LENGTH_LONG).show();
        }

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
