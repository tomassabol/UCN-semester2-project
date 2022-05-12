package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.NotFoundException;

import database.interfaces.ShelfDBIF;
import controller.DepartmentController;
import controller.ProductController;
import model.Department;
import model.Product;
import model.Shelf;

public class ShelfDB implements ShelfDBIF {
   
    //PreparedStatements 
    private static final String FIND_ALL = "select * from Shelves";
    private static final String FIND_BY_ID = "select * from Shelves where Id = ?";
    private static final String CREATE_SHELF = "insert into Shelves values(?,?,?,?)";
    private static final String UPDATE_SHELF = "update Shelves  set Name = ?,DepartmentId = ?";
    private static final String DISABLE_SHELF = "delete from Shelves where Id = ?";
    private static final String PRODUCT_QUANTITY_PER_DEPARTMENT = "select * from Shelves where DepartmentId = ? and ProductId = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createShelf;
    private PreparedStatement updateShelf;
    private PreparedStatement deleteShelf;
    private PreparedStatement productQuantityPerDepartment;

    //Controllers
    ProductController productController;
    DepartmentController departmentController;

    //The constuctor for the selfDB classs
    public ShelfDB()throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createShelf = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SHELF, Statement.RETURN_GENERATED_KEYS);
        updateShelf = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SHELF);
        deleteShelf = DBConnection.getInstance().getConnection().prepareStatement(DISABLE_SHELF);
        productQuantityPerDepartment = DBConnection.getInstance().getConnection().prepareStatement(PRODUCT_QUANTITY_PER_DEPARTMENT);

        productController = new ProductController();
        departmentController  =  new DepartmentController();
    }

    /**
     * Returns all shelves in the database
     * @return list of shelves
     * @throws SQLException, NotFoundException
     */
    @Override
    public List<Shelf> findAll() throws SQLException, NotFoundException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Shelf> shelves = buildObjects(rs);
        return shelves;

    }

    /**
     * Finds a shelf by id
     * @param id - id to be found
     * @return shelf
     * @throws SQLException, NotFoundException
     */
    @Override
    public Shelf findById(int id) throws SQLException, NotFoundException{
            Shelf shelf = null;
            ResultSet rs;
            findById.setInt(1, id);
            rs = findById.executeQuery();
            while(rs.next()) {
                shelf = buildObject(rs);
            }
    
            if (shelf == null) { throw new NotFoundException("Shelf", id); }
    
            return shelf;
    }

    /**
     * Inserts a shelf into the database
     * @param shelf - shelf to be inserted into the databe
     * @throws SQLException
     */
    @Override
    public void createShelf(Shelf shelf) throws SQLException{
        createShelf.setString(1, shelf.getName());
        createShelf.setInt(2, shelf.getProduct().getId());
        createShelf.setInt(3, shelf.getItems().size());
        createShelf.setInt(4, shelf.getDepartment().getId());
        shelf.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createShelf));
    };

    /**
     * Updates the shelf in the database
     * @param shelf - the self that will be updated
     * @throws SQLException
     */
    @Override
    public void updateShelf(Shelf shelf) throws SQLException{
        updateShelf.setString(1, shelf.getName());
        updateShelf.setInt(2, shelf.getProduct().getId());
        updateShelf.setInt(3, shelf.getItems().size());
        updateShelf.setInt(4, shelf.getDepartment().getId());
        updateShelf.executeUpdate();
    }

    /**
     * Deletes the shelf from the databes 
     * @param shelf - the shelf that needs to be deleted
     * @throws SQLException
     */
    @Override
    public void deleteShelf(Shelf shelf) throws SQLException{
        deleteShelf.setInt(1, shelf.getId());
        deleteShelf.executeUpdate(); 
    }

    @Override
    public List<Shelf> productQuantityPerDepartment(Department department, Product product) throws SQLException, NotFoundException {
        ResultSet rs;
        productQuantityPerDepartment.setInt(1, department.getId());
        productQuantityPerDepartment.setInt(2, product.getId());
        rs = productQuantityPerDepartment.executeQuery();
        List<Shelf> shelves = buildObjects(rs);
        return shelves;
    }

    // Local methodes

    /**
     * Build a shelf object from the database
     * @param rs - resultset
     * @return shelf - a shelf object
     * @throws SQLException
     * @throws  NotFoundException
     */
    private Shelf buildObject(ResultSet rs)throws SQLException, NotFoundException{
        Product product = productController.findById(rs.getInt("ProductId"));
        Department department = departmentController.findById(rs.getInt("DepartmentId"));
        Shelf shelf = new Shelf(rs.getString("Name"), product, department);
        shelf.setId(rs.getInt("Id"));
        return shelf;
    }

     /**
     * Builds a list of shelf object from the database
     * @param rs - resultset
     * @return shelf - a list of shelf object
     * @throws SQLException
     * @throws  NotFoundException
     */
    private List<Shelf> buildObjects(ResultSet rs)throws SQLException, NotFoundException{
        List<Shelf> shelves = new ArrayList<>();
        while(rs.next()){
            shelves.add(buildObject(rs));
        }
        return shelves;
    }
}
