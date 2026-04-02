package com.icet.repository;

import com.icet.model.Inventory;
import javafx.collections.ObservableList;

public interface InventoryRepository {
    ObservableList<Inventory> getAllDetails();
    void addStock(Inventory inventory);
    void updateStock(Inventory inventory);
    void deleteStock(Integer id);
    Inventory searchStockById(Integer id);
}
