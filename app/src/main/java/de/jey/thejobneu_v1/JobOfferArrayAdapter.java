package de.jey.thejobneu_v1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobOfferArrayAdapter extends ArrayAdapter<JobOffer> {
    private Context mContext;
    private List<JobOffer> mJobOfferList;
    private LayoutInflater mLayoutInflater;

    private Resources mResources;
    private String mPackageName;
    //private Map<String, Drawable> mQuoteAuthorDrawables = new HashMap<>();

    public JobOfferArrayAdapter(Context context, List<JobOffer> jobOfferList) {
        super(context, R.layout.listviewlayout, jobOfferList);
        mContext = context;
        mJobOfferList = jobOfferList;
        mLayoutInflater = LayoutInflater.from(context);
        mResources = context.getResources();
        mPackageName = context.getPackageName();
        //createQuoteAuthorDrawable();


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView;
        if (convertView == null) {
            listView = mLayoutInflater.inflate(R.layout.listviewlayout, parent, false);
        }else {
            listView = convertView;
        }
        JobOffer currentJobOffer = mJobOfferList.get(position);

        TextView tvBeruf = (TextView) listView.findViewById(R.id.tvBeruf);
        TextView tvBetriebsart = (TextView) listView.findViewById(R.id.tvBetriebsart);
        TextView tvOrt = (TextView) listView.findViewById(R.id.tvOrt);
        TextView tvVerfuegbarkeit = listView.findViewById(R.id.tvVerfuegbarkeit);

        tvBeruf.setText(currentJobOffer.getBeruf());
        tvBetriebsart.setText(currentJobOffer.getBetriebsart());
        tvOrt.setText(currentJobOffer.getOrt());
        tvVerfuegbarkeit.setText(currentJobOffer.getVerfuegbarkeit());
        return listView;

    }


}
