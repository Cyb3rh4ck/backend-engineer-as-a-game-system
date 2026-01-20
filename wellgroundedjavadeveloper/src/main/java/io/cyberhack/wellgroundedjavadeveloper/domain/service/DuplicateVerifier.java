package io.cyberhack.wellgroundedjavadeveloper.domain.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

// 1. DOMINIO (CORE)
// Esta clase es POJO puro. No sabe de Spring, ni de HTTP, ni de JSON.
// Solo sabe matemáticas/lógica: "Detectar duplicados".

@Component
public class DuplicateVerifier {

        public boolean execute(int[] nums) {
        // validación defensiva del dominio
        if (nums == null || nums.length == 0) {
            return false; 
        }

        // Tu algoritmo O(n) vive aquí protegida
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true; // Es duplicado
            }
            seen.add(num);
        }
        return false; // Son únicos
    }

}
