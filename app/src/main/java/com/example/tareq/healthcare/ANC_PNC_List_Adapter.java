package com.example.tareq.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAREQ on 10/27/2016.
 */
public class ANC_PNC_List_Adapter extends RecyclerView.Adapter<ANC_PNC_List_Adapter.ViewHolder>   {
    String call = "";
    String call_2= "";
    List<Mother> motherList = new ArrayList<>();
    private Context context;
    String healthServiceName  ;

    public ANC_PNC_List_Adapter(List<Mother> motherList, Context context,String healthServiceName) {
        this.motherList = motherList;
        this.context = context;
        this.healthServiceName = healthServiceName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.anc_pnc_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,   int position) {

        final Mother a_mother = motherList.get(position);

        if (!a_mother.getMotherName().isEmpty()){
            String name = "নামঃ"+ a_mother.getMotherName();
            holder.tvMotherName.setText(name);
        }

        if (!a_mother.getHusbandName().isEmpty()){
            String husbandName ="স্বামীঃ"+ a_mother.getHusbandName();
            holder.tvHusbandName.setText(husbandName);
        }

        if (!a_mother.getMotherAddress().isEmpty()){
            String address ="ঠিকানাঃ"+ a_mother.getMotherAddress();

            holder.tvAddress.setText(address);

        }


        String deliveryStatus = "" ;
        final String messageText = "মেসেজঃ ";
        String total_tv_message_text = "";

        if (healthServiceName.equals(GroupMother.ANC_1)){
            if (a_mother.getIsANC_1_Message_Delivered().equals("true")){
                deliveryStatus = "হ্যাঁ";
                holder.btn_call_1.setVisibility(View.INVISIBLE);
                holder.btn_call_2.setVisibility(View.INVISIBLE);
                holder.ivYesNO.setImageResource(R.drawable.yes);
                total_tv_message_text= messageText+ deliveryStatus;
                holder.tv_message.setText(total_tv_message_text);
            }else {
                deliveryStatus = "না";
                total_tv_message_text = messageText+ deliveryStatus;
                holder.tv_message.setText(total_tv_message_text);
                holder.ivYesNO.setImageResource(R.drawable.no);
                if (a_mother.getMotherPhoneNumber().isEmpty()){
                    holder.btn_call_1.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_1.setVisibility(View.VISIBLE);
                }

                if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                    holder.btn_call_2.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_2.setVisibility(View.VISIBLE);
                }

            }
        }else if (healthServiceName.equals(GroupMother.ANC_2)){
            if (a_mother.getIsANC_2_Message_Delivered().equals("true")){
                deliveryStatus = "হ্যাঁ";
                holder.btn_call_1.setVisibility(View.INVISIBLE);
                holder.btn_call_2.setVisibility(View.INVISIBLE);
                holder.ivYesNO.setImageResource(R.drawable.yes);
                total_tv_message_text=messageText+ deliveryStatus;
                holder.tv_message.setText(total_tv_message_text);
            }else {
                deliveryStatus = "না";
                total_tv_message_text=messageText+ deliveryStatus;
                holder.tv_message.setText(total_tv_message_text);
                holder.ivYesNO.setImageResource(R.drawable.no);
                if (a_mother.getMotherPhoneNumber().isEmpty()){
                    holder.btn_call_1.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_1.setVisibility(View.VISIBLE);
                }

                if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                    holder.btn_call_2.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_2.setVisibility(View.VISIBLE);
                }
            }
        }else if (healthServiceName.equals(GroupMother.ANC_3)){
            if (a_mother.getIsANC_3_Message_Delivered().equals("true")){
                deliveryStatus = "হ্যাঁ";
                total_tv_message_text=messageText+ deliveryStatus;
                holder.btn_call_1.setVisibility(View.INVISIBLE);
                holder.btn_call_2.setVisibility(View.INVISIBLE);
                holder.ivYesNO.setImageResource(R.drawable.yes);
                holder.tv_message.setText(total_tv_message_text);
            }else {
                deliveryStatus = "না";
                total_tv_message_text =messageText+ deliveryStatus ;
                holder.tv_message.setText(total_tv_message_text);
                holder.ivYesNO.setImageResource(R.drawable.no);
                if (a_mother.getMotherPhoneNumber().isEmpty()){
                    holder.btn_call_1.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_1.setVisibility(View.VISIBLE);
                }

                if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                    holder.btn_call_2.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_2.setVisibility(View.VISIBLE);
                }
            }
        }else if (healthServiceName.equals(GroupMother.ANC_4)){
            if (a_mother.getIsANC_4_Message_Delivered().equals("true")){
                deliveryStatus = "হ্যাঁ";
                holder.btn_call_1.setVisibility(View.INVISIBLE);
                holder.btn_call_2.setVisibility(View.INVISIBLE);
                holder.ivYesNO.setImageResource(R.drawable.yes);
                total_tv_message_text =messageText+ deliveryStatus ;
                holder.tv_message.setText(total_tv_message_text);
            }else {
                deliveryStatus = "না";
                total_tv_message_text =messageText+ deliveryStatus ;
                holder.tv_message.setText(total_tv_message_text);
                holder.ivYesNO.setImageResource(R.drawable.no);
                if (a_mother.getMotherPhoneNumber().isEmpty()){
                    holder.btn_call_1.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_1.setVisibility(View.VISIBLE);
                }

                if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                    holder.btn_call_2.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_2.setVisibility(View.VISIBLE);
                }
            }
        }else if (healthServiceName.equals(GroupMother.PNC)){
            if (a_mother.getIsPNC_Message_Delivered().equals("true")){
                deliveryStatus = "হ্যাঁ";
                holder.btn_call_1.setVisibility(View.INVISIBLE);
                holder.btn_call_2.setVisibility(View.INVISIBLE);
                holder.ivYesNO.setImageResource(R.drawable.yes);
                total_tv_message_text =messageText+ deliveryStatus ;
                holder.tv_message.setText(total_tv_message_text);
            }else {
                deliveryStatus = "না";
                total_tv_message_text =messageText+ deliveryStatus ;
                holder.tv_message.setText(total_tv_message_text);
                holder.ivYesNO.setImageResource(R.drawable.no);
                if (a_mother.getMotherPhoneNumber().isEmpty()){
                    holder.btn_call_1.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_1.setVisibility(View.VISIBLE);
                }

                if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                    holder.btn_call_2.setVisibility(View.INVISIBLE);
                }else {
                    holder.btn_call_2.setVisibility(View.VISIBLE);
                }
            }
        }




//        total_tv_message_text =messageText+ deliveryStatus ;
//        holder.tv_message.setText(total_tv_message_text);

        if (!healthServiceName.equals(GroupMother.PNC) && a_mother.getMotherEDD() != null){//=================================================================================
            String edd = "EDD: "+ a_mother.getMotherEDD();
            holder.tvEDD.setText(edd);
            holder.tvChildName.setVisibility(View.GONE);
        }
        if (healthServiceName.equals(GroupMother.PNC) ){
           // if (!a_mother.getChild().getChildName().isEmpty() && a_mother.getChild().getChildName() != null){
           // Toast.makeText(context,a_mother.getChild().getChildName(),Toast.LENGTH_LONG).show();l
                //String childName= "বাচ্চার নামঃ "+a_mother.getChild().getChildName();
                holder.tvEDD.setVisibility(View.GONE);


                 holder.tvChildName.setVisibility(View.GONE);
            //}

        }

        if (   a_mother.desiredCallingTime != null){
            String desireCallingTime = "";

            if (a_mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_MORNING)){
                desireCallingTime = ANC_PNC_List_Activity.DESIRE_CALLING_TIME_MORNING;
            }else  if (a_mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_NOON)){
                desireCallingTime = ANC_PNC_List_Activity.DESIRE_CALLING_TIME_NOON;
            }else  if (a_mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_EVENING)){
                desireCallingTime = ANC_PNC_List_Activity.DESIRE_CALLING_TIME_EVENING;
            }

            String total_desire_time = "যোগাযোগের সময়ঃ "+desireCallingTime;
            holder.tvDesireCallingTime.setText(total_desire_time);

        }



         if (!a_mother.getMotherPhoneNumber().isEmpty()){
               call = a_mother.getMotherPhoneNumber();
           }

        holder.btn_call_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+call));
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });


        if (!a_mother.getAlternativePhoneNumber().isEmpty()){
            call_2= a_mother.getAlternativePhoneNumber();
        }
//            holder.btn_call_2.setVisibility(View.VISIBLE);
            holder.btn_call_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+call_2));
                    context.startActivity(intent);
                }
            });
        //}

        holder.btn_change_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_message, null);

                TextView tv_message_title = (TextView) view.findViewById(R.id.tv_message_title);
                TextView tv_message_body = (TextView) view.findViewById(R.id.tv_message_body);
                Button btn_message_cancel = (Button) view.findViewById(R.id.btn_message_cancel);
                Button btn_message_yes = (Button) view.findViewById(R.id.btn_message_yes);
                Button btn_message_no = (Button) view.findViewById(R.id.btn_message_no);


                tv_message_title.setText("মেসেজ দিয়েছেন? ");
                tv_message_body.setText(" যদি দিয়ে থাকেন “হ্যাঁ” ক্লিক করুন। \n" +
                        " যদি না দিয়ে থাকেন “না” ক্লিক করুন।\n" +
                        " বাহির হওয়ার জন্য “বাতিল করুন” ক্লিক করুন।");
                btn_message_cancel.setText("ফিরে যাই");
                btn_message_yes.setText("হ্যাঁ ");
                btn_message_no.setText("না ");

                builder.setView(view);




             final AlertDialog dialog =   builder.create();
            dialog.show();

                btn_message_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_message_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper helper = new DatabaseHelper(context);

                        if (healthServiceName.equals(GroupMother.ANC_1)){
                            helper.set_ANC_1_message_status(a_mother.getMotherRowPrimaryKey(),"true");
                            a_mother.setIsANC_1_Message_Delivered("true");
                        }else if (healthServiceName.equals(GroupMother.ANC_2)){
                            helper.set_ANC_2_message_status(a_mother.getMotherRowPrimaryKey(),"true");
                            a_mother.setIsANC_2_Message_Delivered("true");
                        }else if (healthServiceName.equals(GroupMother.ANC_3)){
                            helper.set_ANC_3_message_status(a_mother.getMotherRowPrimaryKey(),"true");
                            a_mother.setIsANC_3_Message_Delivered("true");
                        }else if (healthServiceName.equals(GroupMother.ANC_4)){
                            helper.set_ANC_4_message_status(a_mother.getMotherRowPrimaryKey(),"true");
                            a_mother.setIsANC_4_Message_Delivered("true");
                        }else if (healthServiceName.equals(GroupMother.PNC)){
                            helper.set_PNC_message_status(a_mother.getMotherRowPrimaryKey(),"true");
                            a_mother.setIsPNC_Message_Delivered("true");
                        }


                        holder.btn_call_1.setVisibility(View.INVISIBLE);
                        holder.btn_call_2.setVisibility(View.INVISIBLE);

                        holder.ivYesNO.setImageResource(R.drawable.yes);
                        String deliveryStatusNO = "হ্যাঁ";
                        String total_tv_message_text =messageText+ deliveryStatusNO ;
                        holder.tv_message.setText(total_tv_message_text);
//
                        dialog.dismiss();
                    }
                });

                btn_message_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper helper = new DatabaseHelper(context);

                        if (healthServiceName.equals(GroupMother.ANC_1)){
                            helper.set_ANC_1_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                            a_mother.setIsANC_1_Message_Delivered("false");
                        }else if (healthServiceName.equals(GroupMother.ANC_2)){
                            helper.set_ANC_2_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                            a_mother.setIsANC_2_Message_Delivered("false");
                        }else if (healthServiceName.equals(GroupMother.ANC_3)){
                            helper.set_ANC_3_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                            a_mother.setIsANC_3_Message_Delivered("false");
                        }else if (healthServiceName.equals(GroupMother.ANC_4)){
                            helper.set_ANC_4_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                            a_mother.setIsANC_4_Message_Delivered("false");
                        }else if (healthServiceName.equals(GroupMother.PNC)){
                            helper.set_PNC_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                            a_mother.setIsPNC_Message_Delivered("false");
                        }



                        if (a_mother.getMotherPhoneNumber().isEmpty()){
                            holder.btn_call_1.setVisibility(View.INVISIBLE);
                        }else {
                            holder.btn_call_1.setVisibility(View.VISIBLE);
                        }
                        if (  a_mother.getAlternativePhoneNumber().isEmpty()){
                            holder.btn_call_2.setVisibility(View.INVISIBLE);
                        }else {  holder.btn_call_2.setVisibility(View.VISIBLE);}
                        //=============================================
                        holder.ivYesNO.setImageResource(R.drawable.no);
                        String deliveryStatusNO = "না";
                        String total_tv_message_text =messageText+ deliveryStatusNO ;
                        holder.tv_message.setText(total_tv_message_text);
                        dialog.dismiss();
                    }
                });
            }
        });
        holder.btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
                View messageView = inflater.inflate(R.layout.messages_for_mother,null);


                 if (ANC_PNC_List_Activity.ANC_PNC_STATE.equals(GroupMother.ANC_4)){
                    messageView.findViewById(R.id.linearLayout_ANC_4_Messages).setVisibility(View.VISIBLE);
                    messageView.findViewById(R.id.linearLayout_ANC_Messages).setVisibility(View.GONE);
                    messageView.findViewById(R.id.linearLayout_PNC_Messages).setVisibility(View.GONE);
                }else if (ANC_PNC_List_Activity.ANC_PNC_STATE.equals(GroupMother.PNC)){
                    messageView.findViewById(R.id.linearLayout_ANC_4_Messages).setVisibility(View.GONE);
                    messageView.findViewById(R.id.linearLayout_ANC_Messages).setVisibility(View.GONE);
                    messageView.findViewById(R.id.linearLayout_PNC_Messages).setVisibility(View.VISIBLE);
                }else {
                    messageView.findViewById(R.id.linearLayout_ANC_4_Messages).setVisibility(View.GONE);
                    messageView.findViewById(R.id.linearLayout_ANC_Messages).setVisibility(View.VISIBLE);
                    messageView.findViewById(R.id.linearLayout_PNC_Messages).setVisibility(View.GONE);
                }


               builder.setView(messageView);

                builder.setNegativeButton("ফিরে যাই", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                AlertDialog dialog =   builder.create();
                dialog.show();

            }
        });
       holder.btn_details.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(context, Mother_Details_Activity.class);
               intent.putExtra(Mother_Details_Activity.TAG,a_mother);
               context.startActivity(intent);
           }
       });
        holder.btn_Edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(context, EditMotherActivity.class);
               intent.putExtra(EditMotherActivity.TAG,a_mother);
               ((Activity) context).startActivityForResult(intent,ANC_PNC_List_Activity.REQUEST_CODE_EDIT_MOTHER_ACTIVITY);/////////////
               //context.startActivity(intent);
           }
       });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_delete, null);

                TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_delete_title);
                Button btn_yes = (Button) view.findViewById(R.id.btn_dialog_delete_yes);
                Button btn_no = (Button) view.findViewById(R.id.btn_dialog_delete_no);

                tv_dialog_title.setText("ডিলিট করতে চান?");
                btn_no.setText("না ");
                btn_yes.setText("হ্যাঁ");
                builder.setView(view);




                final AlertDialog dialog =   builder.create();
                dialog.show();

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper dbHelper = new DatabaseHelper(context);
                        dbHelper.deleteMother(a_mother);

                        motherList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
                        dialog.dismiss();
                    }
                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

//         holder..;
//         holder.btchange.;
    }

    @Override
    public int getItemCount() {
        return motherList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
TextView tv_message,tvMotherName,tvEDD,tvHusbandName,tvAddress,tvDesireCallingTime,tvChildName;
        AppCompatButton btn_delete,btn_Edit,btn_details,btn_message,btn_change_status,btn_call_1,btn_call_2;

        protected ImageView ivYesNO;

        public ViewHolder(View itemView) {
            super(itemView);


            ivYesNO = (ImageView) itemView.findViewById(R.id.ivYesNo);


            tv_message = (TextView) itemView.findViewById(R.id.tv_message );
            tvMotherName = (TextView) itemView.findViewById(R.id.tvMotherName );
            tvEDD = (TextView) itemView.findViewById(R.id.tvEDD );
            tvHusbandName = (TextView) itemView.findViewById(R.id.tvHusbandName );
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress );
            tvDesireCallingTime = (TextView) itemView.findViewById(R.id.tvDesireCallingTime);
            tvChildName = (TextView) itemView.findViewById(R.id.tvChildName);
            btn_delete = (AppCompatButton) itemView.findViewById(R.id.btn_delete);
           btn_Edit =(AppCompatButton) itemView.findViewById(R.id.btnEdit);
            btn_details = (AppCompatButton) itemView.findViewById(R.id.btn_details );
            btn_message = (AppCompatButton) itemView.findViewById(R.id.btn_message );
            btn_change_status = (AppCompatButton) itemView.findViewById(R.id.btn_change_status );
            btn_call_1 = (AppCompatButton) itemView.findViewById(R.id.btn_call_1 );
            btn_call_2 = (AppCompatButton) itemView.findViewById(R.id.btn_call_2 );







        }
    }
}
