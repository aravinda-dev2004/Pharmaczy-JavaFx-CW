package com.icet.service.impl;

import com.icet.model.SalesDetails;
import com.icet.repository.SalesDetailsRepository;
import com.icet.repository.impl.SalesDetailsRepositoryImpl;
import com.icet.service.SalesDetailsService;
import javafx.collections.ObservableList;

public class SalesDetailsServiceImpl implements SalesDetailsService {
    
    SalesDetailsRepository salesDetailsRepository = new SalesDetailsRepositoryImpl();

    @Override
    public void addSaleDetails(SalesDetails saleDetails) {
        salesDetailsRepository.addSaleDetails(saleDetails);
    }

    @Override
    public void updateSaleDetails(SalesDetails saleDetails) {
        salesDetailsRepository.updateSaleDetails(saleDetails);
    }

    @Override
    public void deleteSaleDetails(Integer saleDetailsId) {
        salesDetailsRepository.deleteSaleDetails(saleDetailsId);
    }

    @Override
    public SalesDetails searchSaleDetailsById(Integer saleDetailsId) {
        return salesDetailsRepository.searchSaleDetailsById(saleDetailsId);
    }

    @Override
    public ObservableList<SalesDetails> getAllSalesDetails() {
        return salesDetailsRepository.getAllSalesDetails();
    }
}
