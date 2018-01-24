package de.nordakademie.iaa.system.service;

import de.nordakademie.iaa.system.model.System;
import de.nordakademie.iaa.system.model.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemService {

    private final SystemRepository systemRepository;

    @Autowired
    public SystemService(final SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Transactional(readOnly = true)
    public List<System> findAll() {
        return systemRepository.findAll();
    }

    @Transactional
    public void create(final System system) {
        systemRepository.create(system);
    }
}

