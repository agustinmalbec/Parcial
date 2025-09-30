package com.example.tp3_moviles.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_moviles.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;
    private SalirViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(SalirViewModel.class);

        binding.btSalir.setOnClickListener(v -> vm.confirmarSalida());

        vm.getSolicitarCierre().observe(getViewLifecycleOwner(), solicitar -> {
            if (Boolean.TRUE.equals(solicitar)) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Salir")
                        .setMessage("¿Deseás cerrar la aplicación?")
                        .setPositiveButton("Sí", (dialog, which) -> requireActivity().finish())
                        .setNegativeButton("No", (dialog, which) -> vm.limpiarEvento())
                        .show();
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
