package com.icet.service.impl;

import com.icet.model.Inventory;
import com.icet.repository.InventoryRepository;
import com.icet.repository.impl.InventoryRepositoryImpl;
import com.icet.service.InventoryService;
import javafx.collections.ObservableList;

public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository = new InventoryRepositoryImpl();

    @Override
    public ObservableList<Inventory> getAllDetails() {
        return inventoryRepository.getAllDetails();
    }

    @Override
    public void addStock(Inventory inventory) {
        inventoryRepository.addStock(inventory);
    }

    @Override
    public void updateStock(Inventory inventory) {
        inventoryRepository.updateStock(inventory);
    }

    @Override
    public void deleteStock(Integer id) {
        inventoryRepository.deleteStock(id);
    }

    @Override
    public Inventory searchStockById(Integer id) {
        return inventoryRepository.searchStockById(id);
    }
}
