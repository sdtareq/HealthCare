package com.example.tareq.healthcare;

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

    public All_Mother_List_Adapter(List<Mother> motherList) {
        this.motherList = motherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_mother_list_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mother a_mother = motherList.get(position);

        holder.vName.setText(a_mother.getMotherName());
        holder.txt2.setText(a_mother.getLastMenstruationDate());
        holder.txt3.setText(a_mother.getPregnancyState());
        holder.vTitle.setText(a_mother.getRunningHealthService());

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


        public ViewHolder(View itemView) {
            super(itemView);

            vName =  (TextView) itemView.findViewById(R.id.txtName);
            txt2 = (TextView)  itemView.findViewById(R.id.txt2);
            txt3 = (TextView)  itemView.findViewById(R.id.txt3);
            vTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
