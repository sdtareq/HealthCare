package com.example.tareq.healthcare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAREQ on 10/27/2016.
 */
public class ANC_PNC_List_Adapter extends RecyclerView.Adapter<ANC_PNC_List_Adapter.ViewHolder>   {
    String call;
    List<Mother> motherList = new ArrayList<>();
    private Context context;

    public ANC_PNC_List_Adapter(List<Mother> motherList, Context context) {
        this.motherList = motherList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.anc_pnc_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     call = "3444343";
        Mother a_mother = motherList.get(position);


        holder.vName.setText(a_mother.getMotherName());
          holder.txt2.setText(a_mother.getLastMenstruationDate());
          holder.txt3.setText(a_mother.getIsPregnant());
        holder.vTitle.setText(a_mother.getRunningHealthService());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+call));
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//        holder.btDetails.;
//         holder.btDialog.;
//         holder.btchange.;
    }

    @Override
    public int getItemCount() {
        return motherList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView txt2;
        protected TextView txt3;
        protected TextView vTitle;
        protected Button btDetails, btDialog, btchange,btnCall;


        public ViewHolder(View itemView) {
            super(itemView);

            vName = (TextView) itemView.findViewById(R.id.txtName);
            txt2 = (TextView) itemView.findViewById(R.id.txt2);
            txt3 = (TextView) itemView.findViewById(R.id.txt3);
            vTitle = (TextView) itemView.findViewById(R.id.title);
            btDetails = (Button) itemView.findViewById(R.id.button2);
            btDialog  = (Button) itemView.findViewById(R.id.button);
            btchange  = (Button) itemView.findViewById(R.id.button3);
            btnCall  = (Button) itemView.findViewById(R.id.button4);


        }
    }
}
