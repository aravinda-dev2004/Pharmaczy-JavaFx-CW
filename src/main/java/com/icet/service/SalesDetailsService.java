package com.icet.service;

import com.icet.model.SalesDetails;
import javafx.collections.ObservableList;

public interface SalesDetailsService {
    void addSaleDetails(SalesDetails saleDetails);
    void updateSaleDetails(SalesDetails saleDetails);
    void deleteSaleDetails(Integer saleDetailsId);
    SalesDetails searchSaleDetailsById(Integer saleDetailsId);
    ObservableList<SalesDetails> getAllSalesDetails();
}
