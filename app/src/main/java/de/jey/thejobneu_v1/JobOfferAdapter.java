package de.jey.thejobneu_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.JobOfferViewHolder> {
    Context context;
    List<JobOffer> jobOfferList;

    public JobOfferAdapter(Context context, List<JobOffer> jobOfferList) {
        this.context = context;
        this.jobOfferList = jobOfferList;
    }
    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contens, parent, false);
        JobOfferViewHolder jh = new JobOfferViewHolder(view);
        return jh;
    }
    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder,int position) {
        JobOffer jobOffer = jobOfferList.get(position);
        holder.Beruf.setText(jobOffer.getBeruf());
        holder.Betriebsart.setText(jobOffer.getBetriebsart());
        holder.Ort.setText(jobOffer.getOrt());
        holder.Verfuegbarkeit.setText(jobOffer.getVerfuegbarkeit());

    }
    @Override
    public int getItemCount() {
        return jobOfferList.size();
    }

    public static class JobOfferViewHolder extends RecyclerView.ViewHolder {
        TextView Beruf, Betriebsart, Ort, Verfuegbarkeit;
        // ImageView avatar;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            Beruf = itemView.findViewById(R.id.tvBeruf);
            Betriebsart = itemView.findViewById(R.id.tvBetriebsart);
            Ort = itemView.findViewById(R.id.tvOrt);
            Verfuegbarkeit = itemView.findViewById(R.id.tvVerfuegbarkeit);

        }
    }
}
