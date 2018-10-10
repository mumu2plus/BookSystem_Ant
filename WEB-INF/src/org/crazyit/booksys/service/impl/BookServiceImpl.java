package org.crazyit.booksys.service.impl;

import org.crazyit.booksys.dao.BookDao;
import org.crazyit.booksys.dao.CategoryDao;
import org.crazyit.booksys.dao.InventoryDao;
import org.crazyit.booksys.dao.ItemDao;
import org.crazyit.booksys.dao.SaleDao;
import org.crazyit.booksys.domain.Book;
import org.crazyit.booksys.domain.Category;
import org.crazyit.booksys.domain.Inventory;
import org.crazyit.booksys.domain.Item;
import org.crazyit.booksys.domain.Sale;
import org.crazyit.booksys.exception.BookException;
import org.crazyit.booksys.service.BookService;

import java.util.List;
import java.util.Date;

public class BookServiceImpl implements BookService{
    private BookDao bookDao;
    private CategoryDao categoryDao;
    private InventoryDao inventoryDao;
    private ItemDao itemDao;
    private SaleDao saleDao;
    // 为业务逻辑组件依赖注入DAO组件所需的setter方法


    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void setInventoryDao(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @Override
    public Integer addCategory(Category category) {
        try {
            // 调用DAO组件添加图书种类
            return (Integer)categoryDao.save(category);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BookException("添加图书种类时出现异常，请通知管理员！");
        }
    }

    @Override
    public Integer addBook(Book book, Integer categoryId) {
        try {
            book.setCategory(categoryDao.get(Category.class, categoryId));
            return (Integer)bookDao.save(book);
        } catch (Exception ex) {
            throw new BookException("添加图书时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try
        {
            return categoryDao.findAll(Category.class);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("获取所有图书种类时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try
        {
            return bookDao.findAll(Book.class);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("获取所有图书时出现异常，请通知管理员！");
        }
    }


    @Override
    public Integer addInventory(Inventory inventory, int[] amounts,
                                Integer[] bookIds) {
        try {
            inventory.setInsertTime(new Date());
            Integer id = (Integer) inventoryDao.save(inventory);
            for (int i=0,len=amounts.length; i<len; i++) {
                Item item = new Item();
                item.setAmount(amounts[i]);
                item.setInventory(inventory);
                Book book = bookDao.get(Book.class, bookIds[i]);
                book.setAmount(book.getAmount() + amounts[i]);
                item.setBook(book);
                itemDao.save(item);
            }
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BookException("图书入库时出现异常，请通知管理员！");
        }
    }

    @Override
    public Integer saleBook(Sale sale, Integer bookId) {
        Book book = bookDao.get(Book.class, bookId);
        if(sale.getAmount() > book.getAmount()) {
            throw new BookException("图书库存不足，无法完成销售！");
        }
        try {
            book.setAmount(book.getAmount() - sale.getAmount());
            sale.setBook(book);
            return (Integer)saleDao.save(sale);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BookException("销售图书时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Inventory> getAllInventories() {
        try
        {
            return inventoryDao.findAll(Inventory.class);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("获取所有入库单时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Sale> getAllSales() {
        try
        {
            return saleDao.findAll(Sale.class);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("获取所有销售记录时出现异常，请通知管理员！");
        }
    }

    @Override
    public Object getItemsByInventoryId(Integer inventoryId) {
        try
        {
            return itemDao.findByInventoryId(inventoryId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("根据入库单获取入库项时出现异常，请通知管理员！");
        }
    }

    @Override
    public void updateCategory(Category category) {
        try
        {
            categoryDao.update(category);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("更新图书种类时出现异常，请通知管理员！");
        }
    }

    @Override
    public void updateBook(Book book, Integer categoryId) {
        try
        {
            book.setCategory(categoryDao.get(Category.class, categoryId));
            bookDao.update(book);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new BookException("更新图书时出现异常，请通知管理员！");
        }
    }
}
