package com.example.parcial.ui.modificar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.parcial.R;
import com.example.parcial.databinding.FragmentModificarBinding;
import com.example.parcial.model.Producto;
import com.example.parcial.ui.cargar.CargarViewModel;

public class ModificarFragment extends Fragment {

    private FragmentModificarBinding binding;
    private ModificarViewModel vm;
    private CargarViewModel cargarVM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentModificarBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(requireActivity()).get(ModificarViewModel.class);
        cargarVM = new ViewModelProvider(requireActivity()).get(CargarViewModel.class);

        binding.btBuscar.setOnClickListener(v -> {
            String codigo = binding.etCodigoBuscar.getText().toString();
            vm.buscarProductoPorCodigo(codigo, cargarVM.getListaMutable().getValue());
        });

        vm.getMensaje().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        vm.getProductoEncontrado().observe(getViewLifecycleOwner(), ok -> {
            if (Boolean.TRUE.equals(ok)) {
                Producto producto = vm.getProductoParaNavegar().getValue();
                if (producto != null) {
                    Bundle args = new Bundle();
                    args.putSerializable("producto", producto);
                    Navigation.findNavController(binding.getRoot())
                            .navigate(R.id.action_modificarFragment_to_detalleFragment, args);
                    vm.limpiarNavegacion();
                }
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