package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Shelf;

public interface ShelfDBIF {
    public List<Shelf> findAll() throws SQLException, NotFoundException;
    public Shelf findById(int id) throws SQLException, NotFoundException;
    public void createShelf(Shelf shelf) throws SQLException;
    public void updateShelf(Shelf shelf) throws SQLException;
    public void deleteShelf(Shelf shelf) throws SQLException;
}
