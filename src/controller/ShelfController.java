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

    /**
     * Constructor for the ShelfController class
     * @throws SQLException
     */
    public ShelfController() throws SQLException {
        shelfDBIF = new ShelfDB();
    }

    /**
     * find all Shelves
     * @return List of shelves
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<Shelf> findAll() throws SQLException, NotFoundException {
        List<Shelf> shelfs = shelfDBIF.findAll();
        return shelfs;
    }

    /**
     * find Shelf with desired ID
     * @param id - desired id
     * @return shelf with ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public Shelf findById(int id) throws SQLException, NotFoundException {
        Shelf shelf = shelfDBIF.findById(id);
        return shelf;
    }

    /**
     * Create shelf and push to DB
     * @param name - name if the new shelf
     * @param product - product
     * @param department - department where the shelf is
     * @throws SQLException
     */
    public void createShelf(String name, Product product, Department department) throws SQLException {
        Shelf shelf = new Shelf(name, product, department);
        shelfDBIF.createShelf(shelf);
    }

    /**
     * Update shelf 
     * @param shelf - shelf to be updated
     * @param name - name of the shelf
     * @param product - product
     * @param department - department where the shelf is
     * @throws SQLException
     */
    public void updateShelf(Shelf shelf, String name, Product product, Department department) throws SQLException {
        shelf.setName(name);
        shelf.setProduct(product);
        shelf.setDepartment(department);
        shelfDBIF.updateShelf(shelf);
    }

    /**
     * Add quantity of the product in department
     * @param department - department
     * @param product - product
     * @return quantity
     * @throws SQLException
     * @throws NotFoundException
     */
    public int productQuantityPerDepartment(Department department, Product product) throws SQLException, NotFoundException {
        int count = 0;
        List<Shelf> shelves = shelfDBIF.productQuantityPerDepartment(department, product);
        for (Shelf shelf : shelves) {
            count += shelf.getItems().size();
        }
        return count;
    }

}
