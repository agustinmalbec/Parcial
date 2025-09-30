package com.example.parcial.ui.detalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parcial.databinding.FragmentDetalleBinding;
import com.example.parcial.model.Producto;
import com.example.parcial.ui.cargar.CargarViewModel;

public class DetalleFragment extends Fragment {

    private FragmentDetalleBinding binding;
    private DetalleViewModel vm;
    private CargarViewModel cargarVM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(requireActivity()).get(DetalleViewModel.class);
        cargarVM = new ViewModelProvider(requireActivity()).get(CargarViewModel.class);

        Producto producto = (Producto) requireArguments().getSerializable("producto", Producto.class);
        vm.cargarProducto(producto);

        vm.getProducto().observe(getViewLifecycleOwner(), p -> {
            binding.etDescripcionModificar.setText(p.getDescripcion());
            binding.etPrecioModificar.setText(String.valueOf(p.getPrecio()));
        });

        binding.btModificar.setOnClickListener(v -> {
            String descripcion = binding.etDescripcionModificar.getText().toString();
            String precio = binding.etPrecioModificar.getText().toString();
            vm.guardarCambios(descripcion, precio, cargarVM.getListaMutable().getValue());
        });

        vm.getMensaje().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        vm.getProductoModificado().observe(getViewLifecycleOwner(), ok -> {
            if (Boolean.TRUE.equals(ok)) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

