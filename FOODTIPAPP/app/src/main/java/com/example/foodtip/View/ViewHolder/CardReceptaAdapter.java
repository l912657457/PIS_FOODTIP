package com.example.foodtip.View.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtip.Model.Recepta;
import com.example.foodtip.R;
import com.example.foodtip.View.Home.ViewRecipeActivity;
import com.example.foodtip.ViewModel.HomePageViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class CardReceptaAdapter extends RecyclerView.Adapter<CardReceptaHolder> {
    private final ArrayList<Recepta> receptas;
    private final Activity activity;
    public CardReceptaAdapter(ArrayList<Recepta> receptas, Activity activity) {
        this.receptas = receptas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CardReceptaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recepta_view_item,parent,false);
        return new CardReceptaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardReceptaHolder holder, int position) {
        Recepta recepta = receptas.get(position);

        TextView title = holder.getTitle();
        SliderView sliderView = holder.getImage();

        title.setText(recepta.getTitle());

        SliderAdapter sliderAdapter = new SliderAdapter(recepta.getImages());
        sliderView.setSliderAdapter(sliderAdapter,false);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);

        title.setOnClickListener(l ->{

        });
    }

    @Override
    public int getItemCount() {
        if(receptas != null){
            return receptas.size();
        }
        return 0;
    }
    private void receptaView(Recepta recepta){
        Intent intent = new Intent(this, ViewRecipeActivity.class);
    }
}
