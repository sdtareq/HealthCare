package com.example.tareq.healthcare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAREQ on 10/26/2016.
 */
public class All_Mother_List_Adapter extends RecyclerView.Adapter<All_Mother_List_Adapter.ViewHolder> {
    List<Mother> motherList = new ArrayList<>();
    Context context;

    public All_Mother_List_Adapter(List<Mother> motherList, Context context) {
        this.motherList = motherList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_mother_list_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Mother a_mother = motherList.get(position);

        holder.tvMotherName.setText( "নামঃ  "+a_mother.getMotherName());
        holder.tvHusbandName.setText("স্বামীঃ "+a_mother.getHusbandName());
        holder.tvAddress.setText("ঠিকানাঃ  "+a_mother.getMotherAddress());

        if (a_mother.getPregnancyState().equals(MotherRegistrationActivity.PREGNANCY_STATE_PREGNANT)){
            if (a_mother.getRunningHealthService().equals(GroupMother.ANC_1)){
                holder.btn_anc_or_pnc.setEnabled(true);
                holder.btn_delivery_or_child_care.setEnabled(false);
                a_mother.setRunningHealthServiceBangla("এএনসি ১");
                a_mother.setRunningHealthServiceBangla2("ডেলিভারি  মেসেজ দিন");
                holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());
                holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());

            }else if (a_mother.getRunningHealthService().equals(GroupMother.ANC_2)){
                holder.btn_anc_or_pnc.setEnabled(true);
                holder.btn_delivery_or_child_care.setEnabled(false);
                a_mother.setRunningHealthServiceBangla("এএনসি ২");
                a_mother.setRunningHealthServiceBangla2("ডেলিভারি  মেসেজ দিন");
                holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());
                holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());
                //holder.btn_anc_or_pnc.setText("এএনসি ২");
            }else if (a_mother.getRunningHealthService().equals(GroupMother.ANC_3)){
                holder.btn_anc_or_pnc.setEnabled(true);
                holder.btn_delivery_or_child_care.setEnabled(true);
                a_mother.setRunningHealthServiceBangla("এএনসি ৩");
                a_mother.setRunningHealthServiceBangla2("ডেলিভারি  মেসেজ দিন");
                holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());
                holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());
                //holder.btn_anc_or_pnc.setText("এএনসি ৩");
            }else if (a_mother.getRunningHealthService().equals(GroupMother.ANC_4)){
                holder.btn_anc_or_pnc.setEnabled(true);
                holder.btn_delivery_or_child_care.setEnabled(true);
                a_mother.setRunningHealthServiceBangla("এএনসি ৪");
                a_mother.setRunningHealthServiceBangla2("ডেলিভারি  মেসেজ দিন");
                holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());
                holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());
               // holder.btn_anc_or_pnc.setText("এএনসি ৪");
            }



        }else if (a_mother.getPregnancyState().equals(MotherRegistrationActivity.PREGNANCY_STATE_POST_DELIVERY) && a_mother.getAgeOfChild()<46){
            holder.btn_anc_or_pnc.setEnabled(true);
            holder.btn_delivery_or_child_care.setEnabled(true);
            a_mother.setRunningHealthServiceBangla("পিএনসি");
            holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());

            a_mother.setRunningHealthServiceBangla2("শিশুদের জন্য মেসেজ দিন");
            holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());
          //  holder.btn_anc_or_pnc.setText("পিএনসি");
        }else {
            a_mother.setRunningHealthServiceBangla("পিএনসি");
            holder.btn_anc_or_pnc.setText(a_mother.getRunningHealthServiceBangla());
            holder.btn_anc_or_pnc.setEnabled(false);
            holder.btn_delivery_or_child_care.setEnabled(true);

            a_mother.setRunningHealthServiceBangla2("শিশুদের জন্য মেসেজ দিন");
            holder.btn_delivery_or_child_care.setText(a_mother.getRunningHealthServiceBangla2());

        }









        holder.btn_anc_or_pnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String runningHealthService = a_mother.getRunningHealthService();
                if (runningHealthService.equals(GroupMother.CHILD_CARE_MESSAGE_STATUS) && a_mother.getAgeOfChild()< 46){
                    runningHealthService = GroupMother.PNC;
                }

                Intent intent = new Intent(context,ANC_PNC_List_Activity.class);
                intent.putExtra(MessageActivity.HEALTH_SEARVICE_NAME,runningHealthService);
                intent.putExtra(ANC_PNC_List_Activity.MOTHER_PRIMARY_KEY,a_mother.getMotherRowPrimaryKey());
                context.startActivity(intent);


            }
        });

        holder.btn_delivery_or_child_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String runningHealthService = a_mother.getRunningHealthService();
                if (runningHealthService.equals(GroupMother.ANC_3) || runningHealthService.equals(GroupMother.ANC_4)){
                    runningHealthService = MessageActivity.PRE_DELIVERY;
                }


                Intent intent = new Intent(context,ANC_PNC_List_Activity.class);
                intent.putExtra(MessageActivity.HEALTH_SEARVICE_NAME,runningHealthService);
                intent.putExtra(ANC_PNC_List_Activity.MOTHER_PRIMARY_KEY,a_mother.getMotherRowPrimaryKey());
                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {
        return motherList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvMotherName;
        protected TextView tvHusbandName;
        protected TextView tvAddress;
        protected AppCompatButton btn_delivery_or_child_care;
        protected AppCompatButton btn_anc_or_pnc;


        public ViewHolder(View itemView) {
            super(itemView);

            tvMotherName =  (TextView) itemView.findViewById(R.id.tvMotherName);
            tvHusbandName = (TextView)  itemView.findViewById(R.id.tvHusbandName);
            tvAddress = (TextView)  itemView.findViewById(R.id.tvAddress);
            btn_delivery_or_child_care = (AppCompatButton) itemView.findViewById(R.id.btn_delivery_or_child_care);
            btn_anc_or_pnc = (AppCompatButton) itemView.findViewById(R.id.btn_anc_or_pnc);
        }
    }
}
