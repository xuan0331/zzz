package com.huyuxuan.zzz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtifactDao {
    private Connection connection;

    public ArtifactDao(Connection connection) {
        this.connection = connection;
    }

    public List<ArtifactEntity> getAllArtifacts() throws SQLException {
        List<ArtifactEntity> artifacts = new ArrayList<>();
        String query = "SELECT * FROM artifacts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ArtifactEntity artifact = new ArtifactEntity();
                artifact.setId(rs.getInt("id"));
                artifact.setName(rs.getString("name"));
                artifact.setIntroduce(rs.getString("introduce"));
                artifact.setProvideHp(rs.getInt("provide_hp"));
                artifacts.add(artifact);
            }
        }
        return artifacts;
    }

    public ArtifactEntity getArtifactById(int id) throws SQLException {
        String sql = "SELECT * FROM artifacts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ArtifactEntity artifact = new ArtifactEntity();
                    artifact.setId(resultSet.getInt("id"));
                    artifact.setName(resultSet.getString("name"));
                    artifact.setIntroduce(resultSet.getString("introduce"));
                    artifact.setProvideHp(resultSet.getInt("provide_hp"));
                    return artifact;
                }
            }
        }
        return null;
    }

    public void addArtifact(ArtifactEntity artifact) throws SQLException {
        String query = "INSERT INTO artifacts (name, introduce, provide_hp) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, artifact.getName());
            pstmt.setString(2, artifact.getIntroduce());
            pstmt.setInt(3, artifact.getProvideHp());
            pstmt.executeUpdate();
        }
    }

    public void deleteArtifact(Integer id) throws SQLException {
        String query = "DELETE FROM artifacts WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void updateArtifact(ArtifactEntity artifact) throws SQLException {
        String query = "UPDATE artifacts SET name = ?, introduce = ?, provide_hp = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, artifact.getName());
            pstmt.setString(2, artifact.getIntroduce());
            pstmt.setInt(3, artifact.getProvideHp());
            pstmt.setInt(4, artifact.getId());
            pstmt.executeUpdate();
        }
    }
}
