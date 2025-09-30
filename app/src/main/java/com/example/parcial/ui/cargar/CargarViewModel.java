package com.example.parcial.ui.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcial.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class CargarViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> listaMutable = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> productoAgregado = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public LiveData<List<Producto>> getListaMutable() {
        return listaMutable;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getProductoAgregado() {
        return productoAgregado;
    }

    public void intentarAgregarProducto(String codigoStr, String descripcion, String precioStr) {
        if (codigoStr.trim().isEmpty() || descripcion.trim().isEmpty() || precioStr.trim().isEmpty()) {
            mensaje.setValue("Todos los campos son obligatorios");
            productoAgregado.setValue(false);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            double precio = Double.parseDouble(precioStr.trim());

            List<Producto> actual = listaMutable.getValue();
            if (actual == null) actual = new ArrayList<>();

            for (Producto p : actual) {
                if (p.getCodigo() == codigo) {
                    mensaje.setValue("Código ya existente");
                    productoAgregado.setValue(false);
                    return;
                }
            }

            Producto nuevo = new Producto(codigo, descripcion.trim(), precio);
            List<Producto> nuevaLista = new ArrayList<>(actual);
            nuevaLista.add(nuevo);
            listaMutable.setValue(nuevaLista);

            mensaje.setValue("Producto agregado");
            productoAgregado.setValue(true);

        } catch (NumberFormatException e) {
            mensaje.setValue("Código o precio inválido");
            productoAgregado.setValue(false);
        }
    }
}

