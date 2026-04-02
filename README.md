# 💊 Pharmaczy — Pharmacy Inventory Management System

> A desktop application built with **JavaFX** for managing pharmacy operations including medicines, inventory, suppliers, sales, and billing — all backed by a **MySQL** database.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## 🏥 About the Project

**Pharmaczy** is a Java-based desktop pharmacy management system developed as a coursework project at **ICET**. It provides pharmacists and pharmacy staff with a clean, intuitive interface to manage day-to-day operations — from tracking medicine stock levels and batch expiry dates to processing sales and generating billing records.

The application follows a layered **MVC architecture** (Model → Repository → Service → Controller) and uses **JavaFX with FXML** for the UI layer and **MySQL** for persistent data storage.

---

## ✨ Features

| Module | Functionality |
|---|---|
| 🔐 **Login** | Secure user authentication via database |
| 📊 **Dashboard** | Real-time stats — total medicines, suppliers, and sales |
| 💊 **Medicine Management** | Add, update, delete, and search medicines by name, brand, and category |
| 📦 **Inventory Management** | Track batches with manufacture/expiry dates, stock quantity, cost and selling prices |
| 🚚 **Supplier Management** | Manage supplier contacts, phone numbers, emails, and addresses |
| 🧾 **Sales & Billing** | Record customer sales transactions and generate billing records |
| 📑 **Sales Details** | View itemised breakdown of each sale |

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| UI Framework | JavaFX 19 (FXML) |
| Build Tool | Apache Maven |
| Database | MySQL |
| JDBC Driver | MySQL Connector/J 9.4.0 |
| Boilerplate Reduction | Lombok 1.18.42 |
| IDE | IntelliJ IDEA |

---

## 🏗 Architecture

Pharmaczy follows a clean **4-layer architecture**:

```
UI (FXML / Controller)
        ↓
   Service Layer
        ↓
 Repository Layer
        ↓
 Database (MySQL)
```

- **Controller** — Handles JavaFX events and UI logic
- **Service** — Business logic layer (interfaces + implementations)
- **Repository** — Data access layer with raw SQL via JDBC (interfaces + implementations)
- **Model** — Plain Java objects (POJOs) using Lombok

---

## 📁 Project Structure

```
Pharmaczy/
├── src/
│   └── main/
│       ├── java/com/icet/
│       │   ├── Main.java                  # JavaFX entry point
│       │   ├── Starter.java               # Application starter
│       │   ├── controller/                # JavaFX controllers
│       │   │   ├── DashboardController.java
│       │   │   ├── LoginController.java
│       │   │   ├── MedicineController.java
│       │   │   ├── InventoryController.java
│       │   │   ├── SupplierController.java
│       │   │   ├── SalesAndBillingController.java
│       │   │   └── SalesDetailsFormController.java
│       │   ├── model/                     # Data models (Lombok POJOs)
│       │   │   ├── Medicine.java
│       │   │   ├── Inventory.java
│       │   │   ├── Supplier.java
│       │   │   ├── SalesAndBilling.java
│       │   │   └── SalesDetails.java
│       │   ├── repository/                # Data access interfaces + impls
│       │   │   └── impl/
│       │   ├── service/                   # Business logic interfaces + impls
│       │   │   └── impl/
│       │   ├── db/
│       │   │   └── DBConnection.java      # Singleton DB connection
│       │   └── util/
│       │       └── UIUtils.java           # UI helper utilities
│       └── resources/
│           ├── database.properties        # DB connection config
│           └── view/                      # FXML UI layouts
│               ├── login_form.fxml
│               ├── dashboard_form.fxml
│               ├── medicine_form.fxml
│               ├── inventory_form.fxml
│               ├── supplier_form.fxml
│               ├── sales_and_billing_form.fxml
│               ├── sales_details_form.fxml
│               └── styles.css
└── pom.xml
```

---

## ✅ Prerequisites

Make sure you have the following installed:

- **Java 17+** — [Download JDK](https://adoptium.net/)
- **Maven 3.8+** — [Download Maven](https://maven.apache.org/download.cgi)
- **MySQL 8+** — [Download MySQL](https://dev.mysql.com/downloads/)
- **IntelliJ IDEA** (recommended) or any Java IDE

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/Pharmaczy.git
cd Pharmaczy
```

### 2. Set up the database

See [Database Setup](#database-setup) below.

### 3. Configure connection

Update `src/main/resources/database.properties` with your MySQL credentials:

```properties
db.url=jdbc:mysql://localhost:3306/pharmaczydb
db.username=your_username
db.password=your_password
```

### 4. Build and run

```bash
mvn clean javafx:run
```

Or open the project in IntelliJ IDEA and run `Starter.java`.

---

## 🗄 Database Setup

Run the following SQL to create the database and required tables:

```sql
CREATE DATABASE IF NOT EXISTS pharmaczydb;
USE pharmaczydb;

-- Users table (for login authentication)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Default admin user
INSERT INTO users (username, password) VALUES ('admin', 'admin123');

-- Medicine table
CREATE TABLE medicine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brand_name VARCHAR(100),
    category VARCHAR(50),
    description TEXT
);

-- Supplier table
CREATE TABLE supplier (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    address TEXT
);

-- Inventory table
CREATE TABLE inventory (
    batch_id INT AUTO_INCREMENT PRIMARY KEY,
    medicine_id INT,
    supplier_id INT,
    batch_number VARCHAR(50),
    manufacture_date DATE,
    expiry_date DATE,
    qty_in_stock INT,
    cost_price DECIMAL(10,2),
    selling_price DECIMAL(10,2),
    FOREIGN KEY (medicine_id) REFERENCES medicine(id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);

-- Sales and billing table
CREATE TABLE sales_and_billing (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    sale_date DATE,
    total_amount DECIMAL(10,2)
);

-- Sales details table
CREATE TABLE sales_details (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT,
    medicine_id INT,
    quantity INT,
    unit_price DECIMAL(10,2),
    FOREIGN KEY (sale_id) REFERENCES sales_and_billing(sale_id),
    FOREIGN KEY (medicine_id) REFERENCES medicine(id)
);
```

---

## ⚙️ Configuration

The app reads database settings from:

```
src/main/resources/database.properties
```

```properties
db.url=jdbc:mysql://localhost:3306/pharmaczydb
db.username=root
db.password=your_password
```

> ⚠️ **Important:** Never commit real credentials to version control. Add `database.properties` to `.gitignore` for production use, or use environment variables.

---

## 🖥 Usage

1. Launch the app — the **Login screen** appears.
2. Enter your credentials (default: `admin` / `admin123`).
3. Navigate using the sidebar:
   - **Dashboard** — overview stats
   - **Medicine** — manage medicine catalogue
   - **Inventory** — manage stock batches and pricing
   - **Supplier** — manage supplier records
   - **Sales & Billing** — record new sales
   - **Sale Details** — view itemised sales breakdown

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project was developed as a coursework assignment at **ICET**. Feel free to use it for learning and reference purposes.

---

<p align="center">Made with ☕ and Java by the Pharmaczy team</p>
