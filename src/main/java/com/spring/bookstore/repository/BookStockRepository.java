package com.spring.bookstore.repository;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public boolean updateStockDto(UpdateStockDto updateStockDto) throws Exception {
   
    		
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

    	         } catch (Exception e) {
    	             String error = e.getMessage();
    	             System.out.println(error);
    	         } finally {
    	             if (connection != null) {
    	                 connection.close();
    	             }
    	         }
    	           
    	    return result;
    	
    	
   
   
    }
	
	
	
}
