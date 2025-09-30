package com.example.tp3_moviles.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3_moviles.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> listaOrdenada = new MutableLiveData<>();

    public LiveData<List<Producto>> getListaOrdenada() {
        return listaOrdenada;
    }

    public void actualizarLista(List<Producto> productos) {
        if (productos == null) {
            listaOrdenada.setValue(new ArrayList<>());
            return;
        }

        List<Producto> ordenados = new ArrayList<>(productos);
        ordenados.sort(Comparator.comparing(Producto::getDescripcion));
        listaOrdenada.setValue(ordenados);
    }
}