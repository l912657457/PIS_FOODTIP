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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomePageViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Recepta>> receptas;
    private final MutableLiveData<ArrayList<SliderData>> sliderData;
    private FoodTip foodTip;
    public HomePageViewModel(@NonNull Application application) {
        super(application);
        receptas = new MutableLiveData<>();
        sliderData = new MutableLiveData<>();
        foodTip = FoodTip.getInstance();
        //foodTip.getReceptaInformation(this);
    }

    public MutableLiveData<ArrayList<Recepta>> getReceptas() {
        return receptas;
    }

    public MutableLiveData<ArrayList<SliderData>> getSliderData() {
        return sliderData;
    }

    public void save_uri(Uri uri){
        if(sliderData.getValue() == null){
            sliderData.setValue(new ArrayList<>());
        }
        sliderData.getValue().add(new SliderData(uri.toString()));
        System.out.println("Add slider Information");
        sliderData.setValue(sliderData.getValue());
    }
    public void add_recepta(Recepta recepta){
        if(receptas.getValue() == null) {
            receptas.setValue(new ArrayList<>());
        }
        receptas.getValue().add(recepta);
        receptas.setValue(receptas.getValue());
    }
    private void getDades(){
        CollectionReference db = FirebaseFirestore.getInstance().collection("recepta");
        db.get().addOnCompleteListener(task->{
            if (task.isSuccessful()){
                for(QueryDocumentSnapshot document : task.getResult()){
                    db.document(document.getId())
                            .get()
                            .addOnSuccessListener(documentSnapshot -> {
                                for(String str:(ArrayList<String>)documentSnapshot.get("bitmaps")){
                                    StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(str);
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            save_uri(uri);
                                        }
                                    });
                                }
                                Recepta recepta = new ReceptaBuilder().id(document.getId())
                                        .description((String)documentSnapshot.get("description"))
                                        .title((String) documentSnapshot.get("title"))
                                        .images(sliderData.getValue())
                                        .buildRecepta();

                                add_recepta(recepta);
                            });
                }
            }
        });
    }

}
