package com.icet.service.impl;

import com.icet.model.Medicine;
import com.icet.repository.MedicineRepository;
import com.icet.repository.impl.MedicineRepositoryImpl;
import com.icet.service.MedicineService;
import javafx.collections.ObservableList;

public class MedicineServiceImpl implements MedicineService {

    MedicineRepository medicineRepository = new MedicineRepositoryImpl();

    @Override
    public ObservableList<Medicine> getAllMedicines() {
        return medicineRepository.getAllMedicines();
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineRepository.addMedicine(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        medicineRepository.updateMedicine(medicine);
    }

    @Override
    public void deleteMedicine(Integer id) {
        medicineRepository.deleteMedicine(id);
    }

    @Override
    public Medicine searchMedicineById(Integer id) {
        return medicineRepository.searchMedicineById(id);
    }
}
