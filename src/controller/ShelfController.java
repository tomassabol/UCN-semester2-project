package controller;

import java.sql.SQLException;
import java.util.List;

import database.ShelfDB;
import database.interfaces.ShelfDBIF;
import exceptions.NotFoundException;
import model.Department;
import model.Product;
import model.Shelf;

public class ShelfController {
    
    private ShelfDBIF shelfDBIF;

    public ShelfController() throws SQLException {
        shelfDBIF = new ShelfDB();
    }

    public List<Shelf> findAll() throws SQLException, NotFoundException {
        List<Shelf> shelfs = shelfDBIF.findAll();
        return shelfs;
    }

    public Shelf findById(int id) throws SQLException, NotFoundException {
        Shelf shelf = shelfDBIF.findById(id);
        return shelf;
    }

    public void createShelf(String name, Product product, Department department) throws SQLException {
        shelfDBIF.createShelf(new Shelf(name, product, department));
    }

    public void updateShelf(Shelf shelf, String name, Product product, Department department) throws SQLException {
        shelf.setName(name);
        shelf.setProduct(product);
        shelf.setDepartment(department);
        shelfDBIF.updateShelf(shelf);
    }

    public void deleteShelf(Shelf shelf) throws SQLException {
        shelfDBIF.deleteShelf(shelf);
    }

}
