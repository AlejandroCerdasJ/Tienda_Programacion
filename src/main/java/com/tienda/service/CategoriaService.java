/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) { // Read -> igresar a la bd y leer la info de categoria
        var lista = categoriaRepository.findAll();
        if (activo) {
            lista.removeIf(e -> !e.getActivo());
        }
        return lista;
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional (readOnly = true)
    public Categoria getCategoria (Categoria categoria ){
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }
}
