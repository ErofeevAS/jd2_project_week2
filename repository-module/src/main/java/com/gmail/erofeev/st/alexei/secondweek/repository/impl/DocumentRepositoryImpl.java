package com.gmail.erofeev.st.alexei.secondweek.repository.impl;

import com.gmail.erofeev.st.alexei.secondweek.repository.DocumentRepository;
import com.gmail.erofeev.st.alexei.secondweek.repository.connection.ConnectionService;
import com.gmail.erofeev.st.alexei.secondweek.repository.exception.DataBaseException;
import com.gmail.erofeev.st.alexei.secondweek.repository.model.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {
    private static final Logger logger = LogManager.getLogger(DocumentRepositoryImpl.class);
    private final ConnectionService connectionService;

    public DocumentRepositoryImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Document add(Document document) {
        String sql = "INSERT INTO documents (unique_number,description) VALUES(?,?)";
        String uniqueNumber = document.getUniqueNumber();
        String description = document.getDescription();
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, uniqueNumber);
                ps.setString(2, description);
                ps.execute();
                connection.commit();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        document.setId(rs.getLong(1));
                    }
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                throw new DataBaseException("DataBase exception: a transaction was rollbacked ", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException("Database exception during saving a document", e);
        }
        return document;
    }

    @Override
    public Document getDocumentById(Long id) {
        String findDocumentByIdQuery = "SELECT * FROM documents WHERE id=?";
        Document document = null;
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(findDocumentByIdQuery)) {
                ps.setLong(1, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        document = getDocument(resultSet);
                    }
                    connection.commit();
                    return document;
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                    connection.rollback();
                    throw new DataBaseException("DataBase exception: a transaction was rollbacked ", e);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException("Database exception during reading a document", e);
        }

    }

    private Document getDocument(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String uniqueNumber = resultSet.getString("unique_number");
        String description = resultSet.getString("description");
        Document document = new Document(uniqueNumber, description);
        document.setId(id);
        return document;
    }

    @Override
    public void delete(Long id) {
        String softDeleteByIdQuery = "UPDATE documents SET deleted = TRUE WHERE id=?";
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(softDeleteByIdQuery)) {
                ps.setLong(1, id);
                int amountOfChanges = ps.executeUpdate();
                if(amountOfChanges==0){
                    throw new DataBaseException("document with id: " + id + " not found");
                }
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
                String query = softDeleteByIdQuery.substring(0, softDeleteByIdQuery.length() - 1) + id;
                throw new DataBaseException("DataBase exception: a transaction: " + query, e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException("Database exception during updating a document", e);
        }
    }
}
