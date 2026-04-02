package com.icet.repository.impl;

import com.icet.db.DBConnection;
import com.icet.model.SalesAndBilling;
import com.icet.repository.SalesRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SalesRepositoryImpl implements SalesRepository {

    @Override
    public void addSale(SalesAndBilling sale) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO sales (sale_id, transaction_date, total_amount, payment_method, tax_amount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, sale.getSaleId());
            pstm.setDate(2, Date.valueOf(sale.getTransactionDate()));
            pstm.setDouble(3, sale.getTotalAmount());
            pstm.setString(4, sale.getPaymentMethod());
            pstm.setDouble(5, sale.getTaxAmount());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSale(SalesAndBilling sale) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "UPDATE sales SET transaction_date = ?, total_amount = ?, payment_method = ?, tax_amount = ? WHERE sale_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setDate(1, Date.valueOf(sale.getTransactionDate()));
            pstm.setDouble(2, sale.getTotalAmount());
            pstm.setString(3, sale.getPaymentMethod());
            pstm.setDouble(4, sale.getTaxAmount());
            pstm.setInt(5, sale.getSaleId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSale(Integer saleId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement deleteSalesDetails = connection.prepareStatement(
                    "DELETE FROM sales_details WHERE sale_id = ?"
            );
            deleteSalesDetails.setObject(1, saleId);
            deleteSalesDetails.execute();

            PreparedStatement deleteSale = connection.prepareStatement(
                    "DELETE FROM sales WHERE sale_id = ?"
            );
            deleteSale.setObject(1, saleId);
            deleteSale.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesAndBilling searchSaleById(Integer saleId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM sales WHERE sale_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, saleId);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return new SalesAndBilling(
                        resultSet.getInt("sale_id"),
                        resultSet.getDate("transaction_date").toLocalDate(),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getDouble("tax_amount")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SalesAndBilling> getAllSales() {
        ObservableList<SalesAndBilling> salesList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM sales";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                salesList.add(new SalesAndBilling(
                        resultSet.getInt("sale_id"),
                        resultSet.getDate("transaction_date").toLocalDate(),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getDouble("tax_amount")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading sales data: " + e.getMessage());
            System.err.println("Please make sure the 'sales' table exists in your database.");
            System.err.println("Run the SQL script: src/main/resources/setup_database.sql");
        }
        return salesList;
    }
}
