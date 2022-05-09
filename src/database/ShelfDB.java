package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.DepartmentController;
import controller.ProductController;
import database.interfaces.ShelfDBIF;
import exceptions.NotFoundException;
import model.Department;
import model.Product;
import model.Shelf;
import model.Department;

public class ShelfDB implements ShelfDBIF {
    //PreparedStatements
    
    private static final String FIND_ALL = "select * from Shelves";
    private static final String FIND_BY_ID = "select * from Shelves where Id = ?";
    private static final String CREATE_SHELF = "insert into Shelves values(?,?,?,?)";
    private static final String UPDATE_SHELF = "update Shelves  set Name = ?,DepartmentId = ?";
    private static final String DISABLE_SHELF = "delete from Shelves where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createShelf;
    private PreparedStatement updateShelf;
    private PreparedStatement deleteShelf;

    ProductController productController;
    DepartmentController departmentController;

    public ShelfDB()throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createShelf = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SHELF, Statement.RETURN_GENERATED_KEYS);
        updateShelf = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SHELF);
        deleteShelf = DBConnection.getInstance().getConnection().prepareStatement(DISABLE_SHELF);

        productController = new ProductController();
        departmentController  =  new DepartmentController();
    }

    @Override
    public List<Shelf> findAll() throws SQLException, NotFoundException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Shelf> shelves = buildObjects(rs);
        return shelves;

    }

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

    @Override
    public void createShelf(Shelf shelf) throws SQLException{
        createShelf.setString(1, shelf.getName());
        createShelf.setInt(2, shelf.getProduct().getId());
        createShelf.setInt(3, shelf.getItems().size());
        createShelf.setInt(4, shelf.getDepartment().getId());
        shelf.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createShelf));
    };

    @Override
    public void updateShelf(Shelf shelf) throws SQLException{
        updateShelf.setString(1, shelf.getName());
        updateShelf.setInt(2, shelf.getProduct().getId());
        updateShelf.setInt(3, shelf.getItems().size());
        updateShelf.setInt(4, shelf.getDepartment().getId());
        updateShelf.executeUpdate();
    }

    @Override
    public void deleteShelf(Shelf shelf) throws SQLException{
        deleteShelf.setInt(1, shelf.getId());
        deleteShelf.executeUpdate(); 
    }

    private Shelf buildObject(ResultSet rs)throws SQLException, NotFoundException{
        Product product = productController.findById(rs.getInt("ProductId"));
        Department department = departmentController.findById(rs.getInt("DepartmentId"));
        Shelf shelf = new Shelf(rs.getString("Name"), product, department);
        shelf.setId(rs.getInt("Id"));
        return shelf;
    }

    private List<Shelf> buildObjects(ResultSet rs)throws SQLException, NotFoundException{
        List<Shelf> shelves = new ArrayList<>();
        while(rs.next()){
            shelves.add(buildObject(rs));
        }
        return shelves;
    }
}
