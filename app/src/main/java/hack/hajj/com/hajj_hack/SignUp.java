package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }





}
