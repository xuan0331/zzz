package com.huyuxuan.zzz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeaponDao {
    private final Connection connection;

    public WeaponDao(Connection connection) {
        this.connection = connection;
    }

    public List<WeaponEntity> getAllWeapons() throws SQLException {
        List<WeaponEntity> weapons = new ArrayList<>();
        String query = "SELECT * FROM weapons";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                WeaponEntity weapon = new WeaponEntity();
                weapon.setId(rs.getInt("id"));
                weapon.setName(rs.getString("name"));
                weapon.setType(rs.getString("type"));
                weapon.setProvideHp(rs.getInt("provide_hp"));
                weapons.add(weapon);
            }
        }
        return weapons;
    }

    public WeaponEntity getWeaponById(int id) throws SQLException {
        String sql = "SELECT * FROM weapons WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    WeaponEntity weapon = new WeaponEntity();
                    weapon.setId(resultSet.getInt("id"));
                    weapon.setName(resultSet.getString("name"));
                    weapon.setType(resultSet.getString("type"));
                    weapon.setProvideHp(resultSet.getInt("provide_hp"));
                    return weapon;
                }
            }
        }
        return null;
    }

    public void addWeapon(WeaponEntity weapon) throws SQLException {
        String query = "INSERT INTO weapons (name, type, provide_hp) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, weapon.getName());
            pstmt.setString(2, weapon.getType());
            pstmt.setInt(3, weapon.getProvideHp());
            pstmt.executeUpdate();
        }
    }

    public void deleteWeapon(Integer id) throws SQLException {
        String query = "DELETE FROM weapons WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void updateWeapon(WeaponEntity weapon) throws SQLException {
        String query = "UPDATE weapons SET name = ?, type = ?, provide_hp = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, weapon.getName());
            pstmt.setString(2, weapon.getType());
            pstmt.setInt(3, weapon.getProvideHp());
            pstmt.setInt(4, weapon.getId());
            pstmt.executeUpdate();
        }
    }
}
