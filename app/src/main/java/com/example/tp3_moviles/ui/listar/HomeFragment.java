package com.example.tp3_moviles.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tp3_moviles.databinding.FragmentHomeBinding;
import com.example.tp3_moviles.ui.cargar.CargarViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeVM;
    private CargarViewModel cargarVM;
    private ProductoAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeVM = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        cargarVM = new ViewModelProvider(requireActivity()).get(CargarViewModel.class);

        adapter = new ProductoAdapter(requireContext());
        binding.lista.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        binding.lista.setAdapter(adapter);

        cargarVM.getListaMutable().observe(getViewLifecycleOwner(), productos -> {
            homeVM.actualizarLista(productos);
        });

        homeVM.getListaOrdenada().observe(getViewLifecycleOwner(), ordenados -> {
            adapter.actualizarLista(ordenados);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}