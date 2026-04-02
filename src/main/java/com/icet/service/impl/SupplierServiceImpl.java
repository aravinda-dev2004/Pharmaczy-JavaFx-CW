package com.icet.service.impl;

import com.icet.model.Supplier;
import com.icet.repository.SupplierRepository;
import com.icet.repository.impl.SupplierRepositoryImpl;
import com.icet.service.SupplierService;
import javafx.collections.ObservableList;

public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        return supplierRepository.getAllSuppliers();
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierRepository.addSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierRepository.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Integer id) {
        supplierRepository.deleteSupplier(id);
    }

    @Override
    public Supplier searchSupplierById(Integer id) {
        return supplierRepository.searchSupplierById(id);
    }
}
