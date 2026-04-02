package com.icet.repository;

import com.icet.model.Medicine;
import javafx.collections.ObservableList;

public interface MedicineRepository {
    ObservableList<Medicine> getAllMedicines();
    void addMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    void deleteMedicine(Integer id);
    Medicine searchMedicineById(Integer id);
}
