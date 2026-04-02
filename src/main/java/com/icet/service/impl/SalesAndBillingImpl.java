package com.icet.service.impl;

import com.icet.model.SalesAndBilling;
import com.icet.repository.SalesRepository;
import com.icet.repository.impl.SalesRepositoryImpl;
import com.icet.service.SalesAndBillingService;
import javafx.collections.ObservableList;

public class SalesAndBillingImpl implements SalesAndBillingService {
    
    SalesRepository salesRepository = new SalesRepositoryImpl();

    @Override
    public void addSale(SalesAndBilling sale) {
        salesRepository.addSale(sale);
    }

    @Override
    public void updateSale(SalesAndBilling sale) {
        salesRepository.updateSale(sale);
    }

    @Override
    public void deleteSale(Integer saleId) {
        salesRepository.deleteSale(saleId);
    }

    @Override
    public SalesAndBilling searchSaleById(Integer saleId) {
        return salesRepository.searchSaleById(saleId);
    }

    @Override
    public ObservableList<SalesAndBilling> getAllSales() {
        return salesRepository.getAllSales();
    }
}

