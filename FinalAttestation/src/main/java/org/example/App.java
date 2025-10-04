package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class App {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    public static void main(String[] args) {
        loadProperties();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Включаем ручное управление транзакциями
            conn.setAutoCommit(false);

            try {
                // Создание таблиц
               // createSchema(conn);

                // 1. Вставка нового товара и покупателя
                int productId = insertProduct(conn, "Андипал", 10.0, 50,"Аптека");
                int customerId = insertCustomer(conn, "Артём", "Соколов", "79056251254","sokol@test.ru");
                System.out.println("Вставлены товар ID=" + productId + " и покупатель ID=" + customerId);

                // 2. Создание заказа для покупателя
                int orderId = createOrder(conn, productId, customerId, 2,4);
                System.out.println("Создан заказ ID=" + orderId);

                // 3. Чтение и вывод последних 5 заказов
                System.out.println("\nПоследние 5 заказов:");
                readLastFiveorder(conn);

                // 4. Обновление цены товара и количества на складе
                updateProduct(conn, productId, 950.0, 45);
                System.out.println("Обновлен товар ID=" + productId);

                // 5. Удаление тестовых записей
                deleteTestRecords(conn, productId, customerId, orderId);
                System.out.println("Удалены тестовые записи.");

                // Зафиксировать все операции
                conn.commit();
            } catch (Exception ex) {
                System.out.println("Ошибка: " + ex.getMessage());
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            props.load(fis);
            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            DB_PASSWORD = props.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить конфигурацию базы данных", e);
        }
    }

    private static void createSchema(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        String sqlProduct = "CREATE TABLE IF NOT EXISTS product (" +
                "id SERIAL PRIMARY KEY," +
                "description TEXT NOT NULL," +
                "price NUMERIC(10, 2) CHECK (price >= 0)," +
                "quantity INT CHECK (quantity >= 0)," +
                "category VARCHAR(100) NOT NULL" +
                ");" +

                "COMMENT ON TABLE product IS 'Таблица продуктов';" +
                "COMMENT ON COLUMN product.id IS 'Идентификатор продукта';" +
                "COMMENT ON COLUMN product.description IS 'Описание продукта';" +
                "COMMENT ON COLUMN product.price IS 'Стоимость продукта';" +
                "COMMENT ON COLUMN product.quantity IS 'Количество продукта на складе';" +
                "COMMENT ON COLUMN product.category IS 'Категория продукта';";

        String sqlCustomer = "CREATE TABLE IF NOT EXISTS customer (" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "phone VARCHAR(20) NOT NULL UNIQUE," +
                "email VARCHAR(100) NOT NULL UNIQUE" +
                ");" +

                "COMMENT ON TABLE customer IS 'Таблица клиентов';" +
                "COMMENT ON COLUMN customer.id IS 'Идентификатор клиента';" +
                "COMMENT ON COLUMN customer.first_name IS 'Имя клиента';" +
                "COMMENT ON COLUMN customer.last_name IS 'Фамилия клиента';" +
                "COMMENT ON COLUMN customer.phone IS 'Телефон клиента';" +
                "COMMENT ON COLUMN customer.email IS 'Email клиента';";

        String sqlorder = "CREATE TABLE IF NOT EXISTS \"order\" (" +
                "id SERIAL PRIMARY KEY," +
                "product_id INT NOT NULL REFERENCES product(id)," +
                "customer_id INT NOT NULL REFERENCES customer(id)," +
                "order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "quantity INT CHECK (quantity > 0)," +
                "status_id INT NOT NULL REFERENCES order_status(id)" +
                ")" +

                "COMMENT ON TABLE \"order\" IS 'Таблица заказов';" +
                "COMMENT ON COLUMN \"order\".id IS 'Идентификатор заказа';" +
                "COMMENT ON COLUMN \"order\".product_id IS 'Идентификатор продукта';" +
                "COMMENT ON COLUMN \"order\".customer_id IS 'Идентификатор клиента';" +
                "COMMENT ON COLUMN \"order\".order_date IS 'Дата заказа';" +
                "COMMENT ON COLUMN \"order\".quantity IS 'Количество заказанного продукта';" +
                "COMMENT ON COLUMN \"order\".status_id IS 'Идентификатор статуса заказа';";

        String sqlordertatus = "CREATE TABLE IF NOT EXISTS order_status (" +
                "id SERIAL PRIMARY KEY," +
                "status_name VARCHAR(50) NOT NULL" +
                ");" +

        "COMMENT ON TABLE order_status IS 'Справочник статусов заказов';" +
        "COMMENT ON COLUMN order_status.id IS 'Идентификатор статуса';" +
        "COMMENT ON COLUMN order_status.status_name IS 'Название статуса заказа';";

        stmt.execute(sqlProduct);
        stmt.execute(sqlCustomer);
        stmt.execute(sqlorder);
        stmt.execute(sqlordertatus);

        stmt.close();
    }

    private static int insertProduct(Connection conn, String description, double price, int quantity,String category) throws SQLException {
        String sql = "INSERT INTO product (description, price, quantity,category) VALUES (?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, category);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Не удалось вставить товар");
    }

    private static int insertCustomer(Connection conn, String first_name, String last_name, String phone, String email) throws SQLException {
        String sql = "INSERT INTO customer (first_name, last_name, phone, email) VALUES (?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Не удалось вставить покупателя");
    }

    private static int createOrder(Connection conn, int productId, int customerId, int quantity, int statusId) throws SQLException {
        String insertSql = "INSERT INTO \"order\" (product_id, customer_id, order_date, quantity, status_id) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?) RETURNING id;";

        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, customerId);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, statusId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Не удалось создать заказ");
    }

    private static void readLastFiveorder(Connection conn) throws SQLException {
        String sql = "SELECT o.id as order_id, o.order_date, c.last_name  as customer_name, p.description as product_name, o.quantity ,oi.status_name " +
                "FROM \"order\" o " +
                "JOIN customer c ON o.customer_id = c.id " +
                "JOIN order_status oi ON o.status_id = oi.id " +
                "JOIN product p ON o.product_id = p.id " +
                "ORDER BY o.order_date DESC " +
                "LIMIT 5;";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Timestamp date = rs.getTimestamp("order_date");
                String customer = rs.getString("customer_name");
                String product = rs.getString("product_name");
                int qty = rs.getInt("quantity");
                System.out.printf("Заказ ID=%d, дата=%s, клиент=%s, товар=%s, кол-во=%d%n",
                        orderId, date.toString(), customer, product, qty);
            }
        }
    }

    private static void updateProduct(Connection conn, int productId, double newPrice, int newQuantity) throws SQLException {
        String sql = "UPDATE product SET price = ?, quantity = ? WHERE id = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, newQuantity);
            pstmt.setInt(3, productId);
            int affected = pstmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Товар не найден или не обновлен");
            }
        }
    }

    private static void deleteTestRecords(Connection conn, int productId, int customerId, int orderId) throws SQLException {
        // Удаляем из order
        String deleteorder = "DELETE FROM \"order\" WHERE id = ?;";
        // Удаляем из product
        String deleteProduct = "DELETE FROM product WHERE id = ?;";
        // Удаляем из customer
        String deleteCustomer = "DELETE FROM customer WHERE id = ?;";

        try (PreparedStatement pstmtorder = conn.prepareStatement(deleteorder);
             PreparedStatement pstmtProduct = conn.prepareStatement(deleteProduct);
             PreparedStatement pstmtCustomer = conn.prepareStatement(deleteCustomer)) {

            pstmtorder.setInt(1, orderId);
            pstmtorder.executeUpdate();

            // Удаление товаров и клиентов
            pstmtProduct.setInt(1, productId);
            pstmtProduct.executeUpdate();

            pstmtCustomer.setInt(1, customerId);
            pstmtCustomer.executeUpdate();
        }
    }
}