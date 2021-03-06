package com.example.foodtip.ViewModel;

import android.app.Application;
import android.net.Uri;
import android.transition.Slide;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodtip.Model.FoodTip;
import com.example.foodtip.Model.Recepta;
import com.example.foodtip.Model.ReceptaBuilder;
import com.example.foodtip.Model.SliderData;
import com.example.foodtip.View.ViewHolder.OptionInterface.CMD;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomePageViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Recepta>> receptas;
    private FoodTip foodTip;
    private DocumentSnapshot documentSnapshot = null;
    public HomePageViewModel(@NonNull Application application) {
        super(application);
        receptas = new MutableLiveData<>();
        foodTip = FoodTip.getInstance();
        foodTip.getReceptaInformation(this,CMD.REFRESH);
    }

    public MutableLiveData<ArrayList<Recepta>> getReceptas() {
        return receptas;
    }


    public void add_recepta(Recepta recepta){
        if(receptas.getValue() == null) {
            receptas.setValue(new ArrayList<>());
        }
        receptas.getValue().add(recepta);
        receptas.setValue(receptas.getValue());
    }
    public void refresh(){
        receptas.getValue().clear();
        receptas.setValue(receptas.getValue());
        foodTip.getReceptaInformation(this, CMD.REFRESH);
    }

    public DocumentSnapshot getSTART() {
        return documentSnapshot;
    }

    public void setSTART(DocumentSnapshot documentSnapshot) {
        this.documentSnapshot = documentSnapshot;
    }
}
