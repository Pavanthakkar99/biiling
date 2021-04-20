package com.example.smartbilling.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Model.MeterBill;
import com.example.smartbilling.R;

import java.util.List;

public class MeterAdapter extends RecyclerView.Adapter<MeterAdapter.myViewHolder>{

    List<MeterBill>meterBillList;
    Context context;

    public MeterAdapter(List<MeterBill> meterBillList, Context context) {
        this.meterBillList = meterBillList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meterlistcard, parent, false);
        return new myViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        MeterBill meterBill=meterBillList.get(position);
        holder.billDate.setText(meterBill.getBillDate());
        holder.usedUnit.setText(meterBill.getUsedunit());
        holder.totalBill.setText(meterBill.getTotalbill());
        holder.MakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meterBillList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView billDate;
        TextView usedUnit;
        TextView totalBill;
        Button MakePayment;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            billDate = (TextView) itemView.findViewById(R.id.billDate);
            usedUnit = (TextView) itemView.findViewById(R.id.ReadingPointCart);
            totalBill = (TextView) itemView.findViewById(R.id.totalBillCart);
            MakePayment = (Button) itemView.findViewById(R.id.makePayment);
        }
    }
}
