package com.example.parcial.ui.detalle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcial.model.Producto;

import java.util.List;

public class DetalleViewModel extends ViewModel {

    private final MutableLiveData<Producto> mutableProducto = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();
    private final MutableLiveData<Boolean> productoModificado = new MutableLiveData<>();

    public LiveData<Producto> getProducto() {
        return mutableProducto;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getProductoModificado() {
        return productoModificado;
    }

    public void cargarProducto(Producto producto) {
        if (producto != null) {
            mutableProducto.setValue(producto);
        }
    }

    public void guardarCambios(String descripcion, String precioStr, List<Producto> listaProductos) {
        if (descripcion.trim().isEmpty() || precioStr.trim().isEmpty()) {
            mensaje.setValue("Todos los campos son obligatorios");
            productoModificado.setValue(false);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr.trim());
            Producto actual = mutableProducto.getValue();

            if (actual != null && listaProductos != null) {
                actual.setDescripcion(descripcion.trim());
                actual.setPrecio(precio);

                for (int i = 0; i < listaProductos.size(); i++) {
                    if (listaProductos.get(i).getCodigo() == actual.getCodigo()) {
                        listaProductos.set(i, actual);
                        break;
                    }
                }

                mensaje.setValue("Producto modificado");
                productoModificado.setValue(true);
            }

        } catch (NumberFormatException e) {
            mensaje.setValue("Precio inválido");
            productoModificado.setValue(false);
        }
    }
}

