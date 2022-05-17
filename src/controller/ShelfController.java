package controller;

import java.sql.SQLException;
import java.util.List;

import database.ShelfDB;
import database.interfaces.ShelfDBIF;
import exceptions.NotFoundException;
import model.Department;
import model.Item;
import model.Product;
import model.Shelf;

public class ShelfController {
    
    private ShelfDBIF shelfDBIF;
    private ShelfDetailsController shelfDetailsCtrl;

    /**
     * Constructor for the ShelfController class
     * @throws SQLException
     */
    public ShelfController() throws SQLException {
        shelfDBIF = new ShelfDB();
        shelfDetailsCtrl = new ShelfDetailsController();
    }

    /**
     * find all Shelves
     * @return List of shelves
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<Shelf> findAll(Department department) throws SQLException, NotFoundException {
        List<Shelf> shelfs = shelfDBIF.findAll(department);
        return shelfs;
    }

    public List<Shelf> findEmpty(Department department) throws SQLException, NotFoundException {
        List<Shelf> shelfs = shelfDBIF.findEmpty(department);
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
     * @return 
     * @throws SQLException
     */
    public Shelf createShelf(String name, Department department) throws SQLException {
        Shelf shelf = new Shelf(name, department);
        shelfDBIF.createShelf(shelf);
        return shelf;
    }

    /**
     * Update shelf 
     * @param shelf - shelf to be updated
     * @param name - name of the shelf
     * @param product - product
     * @param department - department where the shelf is
     * @throws SQLException
     */
    public void updateShelf(Shelf shelf, String name, int quantity) throws SQLException {
        shelf.setName(name);
        shelf.setProductQuantity(quantity);
        shelf.setDepartment(shelf.getDepartment());
        shelfDBIF.updateShelf(shelf);
    }

    /**
     * 
     * @param shelf
     * @param product
     * @throws SQLException
     */
    public void updateShelfProduct(Shelf shelf, Product product, int quantity) throws SQLException {
        shelf.setProduct(product);
        shelf.setProductQuantity(quantity);
        shelfDBIF.updateShelf(shelf);
    }

    /**
     * soft delete shelf from db
     * @param shelf - shelf to be deleted
     * @throws SQLException
     */
    public void deleteShelf(Shelf shelf) throws SQLException {
        shelfDBIF.deleteShelf(shelf);
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
        List<Shelf> shelves = shelfDBIF.shelvesPerDepartmentIncludingProduct(department, product);
        for (Shelf shelf : shelves) {
            count += shelf.getProductQuantity();
        }
        return count;
    }

    /**
     * if product quantity on shelf reaches 0, product it reset(set tu null)
     * shelf becomes ready for a new product - items
     * @param shelf - shelf
     * @return true if the reset was performed
     * @throws SQLException
     */
    public boolean zeroReset(Shelf shelf) throws SQLException {
        boolean returnVal = false;
        if (shelf.getProductQuantity() == 0) {
            shelfDBIF.zeroReset(shelf);
            returnVal = true;
        }
        return returnVal;
    }

    public void removeFromStock(List<Item> items) throws SQLException, NotFoundException {
        for (Item item : items) {
            Shelf shelf = shelfDetailsCtrl.findByItemId(item.getId());
            shelfDetailsCtrl.removeItemFromStock(item);
            shelf.setProductQuantity(shelf.getProductQuantity()-1);
            zeroReset(shelf);
        }
    }

}
