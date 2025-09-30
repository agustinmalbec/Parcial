package com.example.tp3_moviles.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private final MutableLiveData<Boolean> solicitarCierre = new MutableLiveData<>();

    public LiveData<Boolean> getSolicitarCierre() {
        return solicitarCierre;
    }

    public void confirmarSalida() {
        solicitarCierre.setValue(true);
    }

    public void limpiarEvento() {
        solicitarCierre.setValue(false);
    }
}