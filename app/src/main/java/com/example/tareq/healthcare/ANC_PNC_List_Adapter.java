package com.example.tareq.healthcare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
     call = "3444343";
        final Mother a_mother = motherList.get(position);


        holder.vName.setText(a_mother.getMotherName());
          holder.txt2.setText(a_mother.getLastMenstruationDate());
          holder.txt3.setText("Msg Delivered: "+a_mother.getIsMessageDelivered());
        holder.vTitle.setText(a_mother.getRunningHealthService());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+call));
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

        holder.btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Are you want to change calling status");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    DatabaseHelper helper = new DatabaseHelper(context);
                        helper.setMessageStatus(a_mother.getMotherRowPrimaryKey(),"true");
                        holder.btChange.setText("Msg  Yes");
                        holder.txt3.setText("Msg Delivered");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper helper = new DatabaseHelper(context);
                        helper.setMessageStatus(a_mother.getMotherRowPrimaryKey(),"false");
                        holder.btChange.setText("Msg  NO");
                        holder.txt3.setText("Msg not Delivered");
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
             AlertDialog dialog =   builder.create();
            dialog.show();
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
        protected Button btDetails, btDialog, btChange,btnCall;


        public ViewHolder(View itemView) {
            super(itemView);

            vName = (TextView) itemView.findViewById(R.id.txtName);
            txt2 = (TextView) itemView.findViewById(R.id.txt2);
            txt3 = (TextView) itemView.findViewById(R.id.txt3);
            vTitle = (TextView) itemView.findViewById(R.id.title);
            btDetails = (Button) itemView.findViewById(R.id.button2);
            btDialog  = (Button) itemView.findViewById(R.id.button);
            btChange  = (Button) itemView.findViewById(R.id.button3);
            btnCall  = (Button) itemView.findViewById(R.id.button4);


        }
    }
}
