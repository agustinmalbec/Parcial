package com.example.parcial.ui.modificar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcial.model.Producto;

import java.util.List;

public class ModificarViewModel extends ViewModel {

    private final MutableLiveData<Producto> productoParaNavegar = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();
    private final MutableLiveData<Boolean> productoEncontrado = new MutableLiveData<>();

    public LiveData<Producto> getProductoParaNavegar() {
        return productoParaNavegar;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getProductoEncontrado() {
        return productoEncontrado;
    }

    public void buscarProductoPorCodigo(String codigoStr, List<Producto> productos) {
        if (codigoStr.trim().isEmpty()) {
            mensaje.setValue("Debe ingresar un código");
            productoEncontrado.setValue(false);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr.trim());

            for (Producto p : productos) {
                if (p.getCodigo() == codigo) {
                    productoParaNavegar.setValue(p);
                    mensaje.setValue("Producto encontrado");
                    productoEncontrado.setValue(true);
                    return;
                }
            }

            mensaje.setValue("Producto no encontrado");
            productoEncontrado.setValue(false);

        } catch (NumberFormatException e) {
            mensaje.setValue("Código inválido");
            productoEncontrado.setValue(false);
        }
    }

    public void limpiarNavegacion() {
        productoParaNavegar.setValue(null);
        productoEncontrado.setValue(false);
    }
}


