package com.codegym.service.impl;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepository;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository iProvinceRepository;

    @Override
    public Page<Province> findAll(Pageable pageable) {
        Page<Province> provinces = iProvinceRepository.findAll(pageable);
        return  provinces;
    }

    @Override
    public Iterable<Province> findAll() {
        return iProvinceRepository.findAll();
    }

    @Override
    public void save(Province province) {
        iProvinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(long id) {
        Optional<Province> province= iProvinceRepository.findById(id);
        return province;
    }

    @Override
    public void remove(long id) {
        iProvinceRepository.delete(iProvinceRepository.findById(id).get());
    }


}