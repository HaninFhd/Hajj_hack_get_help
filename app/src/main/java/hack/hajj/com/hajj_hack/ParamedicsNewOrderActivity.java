package hack.hajj.com.hajj_hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by hanin_5p on 02/08/18.
 */

public class ParamedicsNewOrderActivity extends AppCompatActivity {

    RadioButton urgent ,intermediate , highly_urgent;
    RadioGroup radioGroup;
    EditText case_desc;
    Button send ;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paramedics_new_order_activity);

        urgent = (RadioButton) findViewById(R.id.urgent);
        intermediate = (RadioButton) findViewById(R.id.moderat);
        highly_urgent = (RadioButton) findViewById(R.id.highly_urgent);
        radioGroup  = (RadioGroup) findViewById(R.id.radioGroup) ;
        case_desc =(EditText) findViewById(R.id.case_desc);
        send = (Button) findViewById(R.id.buttonSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
            }
        });

        backButton = (ImageView) findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
            }
        });
    }
}