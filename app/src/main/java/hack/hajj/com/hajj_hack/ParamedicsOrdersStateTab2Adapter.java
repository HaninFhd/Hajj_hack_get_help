package hack.hajj.com.hajj_hack;

/**
 * Created by hanin_5p on 12/10/17.
 * https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ParamedicsOrdersStateTab2Adapter extends RecyclerView.Adapter<ParamedicsOrdersStateTab2Adapter.MyViewHolder> {

    private final DatabaseReference databaseReference;
    private List<Order> ordersList;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number, evaluation, orderstate ;

        public MyViewHolder(View view) {
            super(view);
            number = (TextView) view.findViewById(R.id.number);
            evaluation = (TextView) view.findViewById(R.id.evaluation);
            orderstate = (TextView) view.findViewById(R.id.orderState);
        }
    }

    public ParamedicsOrdersStateTab2Adapter(List<Order> ordersList , Context context) {
        this.ordersList = ordersList;
        this.context=context;
        databaseReference = FirebaseDatabase.getInstance().getReference();

    };
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_state_rec_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Order order = ordersList.get(position);

        holder.orderstate.setText(order.getState());
        holder.evaluation.setText(order.getEvaluation());
        holder.number.setText(order.getNumber());

    }


    @Override
    public int getItemCount() {
        return ordersList.size();
    }

}
