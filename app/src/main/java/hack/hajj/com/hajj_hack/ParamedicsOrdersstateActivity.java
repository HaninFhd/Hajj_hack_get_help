package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanin_5p on 02/08/18.
 */

public class ParamedicsOrdersstateActivity extends AppCompatActivity {
    ImageView backButton ;
    ParamedicsOrdersTab1Adapter mAdapter;
    List<Order> ordersList = new ArrayList<>(); ;
    RecyclerView recyclerView;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paramedics_orders_state_activity);

        context = getApplicationContext();
        backButton = (ImageView) findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ParamedicsActivity.class));
            }
        });

        if (!ordersList.isEmpty()){
            ordersList.clear();
        }

        ordersList.add(new Order("111","s","active"));
        ordersList.add(new Order("113","s","proccing"));
        ordersList.add(new Order("114","s","finished"));


        recyclerView = (RecyclerView) findViewById(R.id.recycleOrderState);
        mAdapter = new ParamedicsOrdersTab1Adapter(ordersList , context);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}