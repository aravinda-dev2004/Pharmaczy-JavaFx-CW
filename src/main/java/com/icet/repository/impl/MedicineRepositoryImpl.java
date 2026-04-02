package com.icet.repository.impl;

import com.icet.db.DBConnection;
import com.icet.model.Medicine;
import com.icet.repository.MedicineRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MedicineRepositoryImpl implements MedicineRepository {
    @Override
    public ObservableList<Medicine> getAllMedicines() {
        ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Medicines");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medicineObservableList.add(new Medicine(
                        resultSet.getInt("MedicineID"),
                        resultSet.getString("Name"),
                        resultSet.getString("BrandName"),
                        resultSet.getString("Category"),
                        resultSet.getString("Description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicineObservableList;
    }

    @Override
    public void addMedicine(Medicine medicine) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Medicines VALUES (?,?,?,?,?)");

            preparedStatement.setObject(1,medicine.getId());
            preparedStatement.setObject(2,medicine.getName());
            preparedStatement.setObject(3,medicine.getBrandName());
            preparedStatement.setObject(4,medicine.getCategory());
            preparedStatement.setObject(5,medicine.getDescription());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Medicines SET Name = ? , BrandName = ? , Category = ? , Description = ? WHERE MedicineID = ?");

            preparedStatement.setObject(1,medicine.getName());
            preparedStatement.setObject(2,medicine.getBrandName());
            preparedStatement.setObject(3,medicine.getCategory());
            preparedStatement.setObject(4,medicine.getDescription());
            preparedStatement.setObject(5,medicine.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicine(Integer id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement deleteSalesDetails = connection.prepareStatement(
                    "DELETE FROM sales_details WHERE batch_id IN (SELECT BatchID FROM batches WHERE MedicineID = ?)"
            );
            deleteSalesDetails.setObject(1, id);
            deleteSalesDetails.execute();

            PreparedStatement deleteBatches = connection.prepareStatement(
                    "DELETE FROM batches WHERE MedicineID = ?"
            );
            deleteBatches.setObject(1, id);
            deleteBatches.execute();

            //now safely delete the medicine
            PreparedStatement deleteMedicine = connection.prepareStatement(
                    "DELETE FROM Medicines WHERE MedicineID = ?"
            );
            deleteMedicine.setObject(1, id);
            deleteMedicine.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Medicine searchMedicineById(Integer id) {
        Medicine medicine = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Medicines WHERE MedicineID = ?");
            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 medicine = new Medicine(
                        resultSet.getInt("MedicineID"),
                        resultSet.getString("Name"),
                        resultSet.getString("BrandName"),
                        resultSet.getString("Category"),
                        resultSet.getString("Description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicine;
    }
}
