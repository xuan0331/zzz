package com.huyuxuan.zzz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDao {
    private Connection connection;

    public CharacterDao(Connection connection) {
        this.connection = connection;
    }

    public List<CharacterEntity> getAllCharacters() throws SQLException {
        List<CharacterEntity> characters = new ArrayList<>();
        String query = "SELECT * FROM characters";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CharacterEntity character = new CharacterEntity();
                character.setId(rs.getInt("id"));
                character.setName(rs.getString("name"));
                character.setWeaponName(rs.getString("weapon_name"));
                character.setArtifactsName(rs.getString("artifacts_name"));
                character.setIntroduce(rs.getString("introduce"));
                character.setImageUrl(rs.getString("image_url"));
                character.setProvideHp(rs.getInt("provide_hp"));
                characters.add(character);
            }
        }
        return characters;
    }

    public CharacterEntity getCharacterById(int id) throws SQLException {
        String sql = "SELECT * FROM characters WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CharacterEntity character = new CharacterEntity();
                    character.setId(resultSet.getInt("id"));
                    character.setName(resultSet.getString("name"));
                    character.setWeaponName(resultSet.getString("weapon_name"));
                    character.setArtifactsName(resultSet.getString("artifacts_name"));
                    character.setIntroduce(resultSet.getString("introduce"));
                    character.setProvideHp(resultSet.getInt("provide_hp"));
                    return character;
                }
            }
        }
        return null;
    }

    public void addCharacter(CharacterEntity character) throws SQLException {
        String query = "INSERT INTO characters (name, weapon_name, artifacts_name, introduce, image_url, provide_hp) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getWeaponName());
            pstmt.setString(3, character.getArtifactsName());
            pstmt.setString(4, character.getIntroduce());
            pstmt.setString(5, character.getImageUrl());
            pstmt.setInt(6, character.getProvideHp());
            pstmt.executeUpdate();
        }
    }

    public void deleteCharacter(Integer id) throws SQLException {
        String query = "DELETE FROM characters WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void updateCharacter(CharacterEntity character) throws SQLException {
        String query = "UPDATE characters SET name = ?, weapon_name = ?, artifacts_name = ?, introduce = ?, provide_hp = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getWeaponName());
            pstmt.setString(3, character.getArtifactsName());
            pstmt.setString(4, character.getIntroduce());
            pstmt.setInt(5, character.getProvideHp());
            pstmt.setInt(6, character.getId());
            pstmt.executeUpdate();
        }
    }
}

