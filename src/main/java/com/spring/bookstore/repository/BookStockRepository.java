package com.spring.bookstore.repository;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.spring.bookstore.model.Token;
import com.spring.bookstore.dto.book.BookDetailForOrderDto;
import com.spring.bookstore.dto.book.UpdateStockDto;
import com.spring.bookstore.model.Customer;

@Repository
public class BookStockRepository extends NamedParameterJdbcDaoSupport {
    
	protected transient static Logger logger = LoggerFactory.getLogger(BookStockRepository.class);
	
	
	@Autowired
    @Qualifier("BookStockDataSource")
    private DataSource dataSource;
 

    @PostConstruct
    protected void initialize() {
        setDataSource(dataSource);
    }

    public boolean updateStockPriceDto(UpdateStockDto updateStockDto) throws Exception {
   
    		
    	 boolean result= false;
    	   
    	    StringBuilder sb = new StringBuilder();

    	         sb.append( "update book set stock_quantity=?, price=? where id=?  ");
    	               
    	         Connection connection = null;
    	       

    	         try {
    	             connection = dataSource.getConnection();
    	             PreparedStatement prepareStatement = connection.prepareStatement(sb.toString());

    	             int i = 1;

    	             
    	             prepareStatement.setInt(i++, updateStockDto.getStockQuantity());
    	             prepareStatement.setDouble(i++, updateStockDto.getPrice());
    	             prepareStatement.setInt(i++, updateStockDto.getId());
    	             
    	             prepareStatement.execute();
    	             prepareStatement.close();
    	             //connection.commit();
    	             result = true;

    	         } catch (Exception ex) {
    	             String error = ex.getMessage();
    	             logger.error("updateStockPriceDto hata olustu", ex);
    	         } finally {
    	             if (connection != null) {
    	                 connection.close();
    	             }
    	         }
    	           
    	    return result;
    	
    	
   
   
    }
    
    public int checkBookStock(int bookId) throws Exception {
    	   
        Connection connection = null;
        int bookStock = -1;
       
        try {

                StringBuilder sb = new StringBuilder();

                sb.append("select stock_quantity from book where id=?"                      
                );
               
                int i = 1;
                connection = dataSource.getConnection();
               
                PreparedStatement prepareStatement = connection.prepareStatement(sb.toString());
                prepareStatement.setInt(i++, bookId);
                
             
             

                ResultSet resultSet = prepareStatement.executeQuery();


                while (resultSet.next()) {
                i = 1;
                bookStock=resultSet.getInt(i++);
                }
            }catch (Exception ex) {
                logger.error("checkBookStock hata olustu", ex);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }

            return bookStock;
       
       
        }     
    
    
    public boolean updateStockFromOrder(int stock_quantity, int bookId) throws Exception {
    	   
		
   	 boolean result= false;
   	   
   	    StringBuilder sb = new StringBuilder();

   	         sb.append( "update book set stock_quantity=? where id=?  ");
   	               
   	         Connection connection = null;
   	       

   	         try {
   	             connection = dataSource.getConnection();
   	             PreparedStatement prepareStatement = connection.prepareStatement(sb.toString());

   	             int i = 1;

   	             
   	             prepareStatement.setInt(i++, stock_quantity);   	             
   	             prepareStatement.setInt(i++, bookId);
   	             
   	             prepareStatement.execute();
   	             prepareStatement.close();
   	             //connection.commit();
   	             result = true;

   	         } catch (Exception ex) {
   	             String error = ex.getMessage();
   	          logger.error("updateStockFromOrder hata olustu", ex);
   	         } finally {
   	             if (connection != null) {
   	                 connection.close();
   	             }
   	         }
   	           
   	    return result;
   	   	  
   }
    
    public List<BookDetailForOrderDto> getBookDetailForOrder(int orderId) throws Exception {
 	   
        Connection connection = null;
        List<BookDetailForOrderDto> listOrderBookDetail= new ArrayList<>();
       
        try {

                StringBuilder sb = new StringBuilder();

                sb.append("select b.title, b.author, b.price from orders as o join book as b on o.book_id = b.id where o.id=?"                      
                );
               
                int i = 1;
                connection = dataSource.getConnection();
               
                PreparedStatement prepareStatement = connection.prepareStatement(sb.toString());
                prepareStatement.setInt(i++, orderId);
                
             
             

                ResultSet resultSet = prepareStatement.executeQuery();


                while (resultSet.next()) {
                	listOrderBookDetail.add(createBookDetailForOrder(resultSet));
                }
            }catch (Exception ex) {
                logger.error("getBookDetailForOrder hata olustu", ex);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }

            return listOrderBookDetail;
       
       
        }     
    
    	public BookDetailForOrderDto createBookDetailForOrder (ResultSet resultSet) throws Exception {
    		
    		BookDetailForOrderDto bookDetailInfo = new BookDetailForOrderDto();
    		int i=1;
    		bookDetailInfo.setTitle(resultSet.getString(i++));
    		bookDetailInfo.setAuthor(resultSet.getString(i++));
    		bookDetailInfo.setPrice(resultSet.getDouble(i++));
    		
    		return bookDetailInfo;
    		
    	}
	
}
