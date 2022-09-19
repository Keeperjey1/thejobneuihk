package de.jey.thejobneu_v1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.JobOfferViewHolder> {
    Context context;
    List<JobOffer> jobOfferList;
    RequestQueue requestQueue;
    String id;

    public JobOfferAdapter(Context context, List<JobOffer> jobOfferList) {
        this.context = context;
        this.jobOfferList = jobOfferList;
    }
    public JobOfferAdapter() {

    }
    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        JobOfferViewHolder jh = new JobOfferViewHolder(view);
        return jh;
    }

    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        JobOffer jobOffer = jobOfferList.get(position);
       // holder.id.setText(jobOffer.getId());
        holder.Beruf.setText(jobOffer.getBeruf());
        holder.Betriebsart.setText(jobOffer.getBetriebsart());
        holder.Ort.setText(jobOffer.getOrt());
        holder.Verfuegbarkeit.setText(jobOffer.getVerfuegbarkeit());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(context, "funktioniert", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ShowJobOfferDetails.class);
                intent.putExtra("id", jobOffer.getId());
                ShowJobOfferDetails a = new ShowJobOfferDetails();
                a.setId5(jobOffer.getId());
                context.startActivity(intent);

            }
        });

       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "funktioniert", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ShowJobOfferDetails.class);
              //  requestQueue = Volley.newRequestQueue(this)



               *//* intent.putExtra("id", jobOffer.getId());
                intent.putExtra("beruf", jobOffer.getBeruf());
                intent.putExtra("betriebsart", jobOffer.getBetriebsart());
                intent.putExtra("ort", jobOffer.getOrt());
                intent.putExtra("verfuegbarkeit", jobOffer.getVerfuegbarkeit());*//*
                context.startActivity(intent);



            }
        });*/

    }
 /*   public String uebergabeId(String id5) {
        ShowJobOfferDetails a = new ShowJobOfferDetails();
        a.setId5(id);
        return id5;
    }*/
    @Override
    public int getItemCount() {
        return jobOfferList.size();
    }

    public static class JobOfferViewHolder extends RecyclerView.ViewHolder {
        TextView id, Beruf, Betriebsart, Ort, Verfuegbarkeit;
        CardView cardView;
        // ImageView avatar;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvJobOfferId);
            Beruf = itemView.findViewById(R.id.tvBeruf);
            Betriebsart = itemView.findViewById(R.id.tvBetriebsart);
            Ort = itemView.findViewById(R.id.tvOrt);
            Verfuegbarkeit = itemView.findViewById(R.id.tvVerfuegbarkeit);

            cardView = itemView.findViewById(R.id.cardView);

        }

    }

}
