package de.jey.thejobneu_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobOfferDetailsAdapter extends RecyclerView.Adapter<JobOfferDetailsAdapter.JobOfferDetailsViewHolder> {
    Context context;
    List<JobOffer> jobOfferList;

    public JobOfferDetailsAdapter(Context context, List<JobOffer> jobOfferList) {
        this.context = context;
        this.jobOfferList = jobOfferList;
    }
    @NonNull
    @Override
    public JobOfferDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scrollview, parent, false);
        JobOfferDetailsViewHolder jh = new JobOfferDetailsViewHolder(view);
        return jh;
    }


    @Override
    public void onBindViewHolder(@NonNull JobOfferDetailsViewHolder holder, int position) {
        JobOffer jobOffer = jobOfferList.get(position);
        holder.id.setText(jobOffer.getId());
        holder.Beruf.setText(jobOffer.getBeruf());
        holder.Betriebsart.setText(jobOffer.getBetriebsart());
        holder.Ort.setText(jobOffer.getOrt());
        holder.Verfuegbarkeit.setText(jobOffer.getVerfuegbarkeit());

       /* holder.scrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "funktioniert", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ShowJobOfferDetails.class);
                intent.putExtra("id", jobOffer.getId());
                intent.putExtra("beruf", jobOffer.getBeruf());
                intent.putExtra("betriebsart", jobOffer.getBetriebsart());
                intent.putExtra("ort", jobOffer.getOrt());
                intent.putExtra("verfuegbarkeit", jobOffer.getVerfuegbarkeit());
                context.startActivity(intent);



            }
        });*/

    }
    @Override
    public int getItemCount() {
        return jobOfferList.size();
    }

    public static class JobOfferDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView id, Beruf, Betriebsart, Ort, Verfuegbarkeit;
        ScrollView scrollview;
        // ImageView avatar;

        public JobOfferDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvShowId);
            Beruf = itemView.findViewById(R.id.tvShowBeruf);
            Betriebsart = itemView.findViewById(R.id.tvShowBetriebsart);
            Ort = itemView.findViewById(R.id.tvShowOrt);
            Verfuegbarkeit = itemView.findViewById(R.id.tvShowVerfuegbarkeit);

            scrollview = itemView.findViewById(R.id.scrollView);

        }

    }
}
