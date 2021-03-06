package ug.jossowska.techut.zad03.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import ug.jossowska.techut.zad03.domain.ComicBook;

public class ComicBookServiceJDBC implements ComicBookService {
	final static String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	
	private Connection connection =  null;
	   private java.sql.Statement statement = null;
	   
	   private final String CREATE_TABLE_SQL = "CREATE TABLE ComicBook(id int GENERATED BY DEFAULT AS IDENTITY, title varchar(50) ,isPopular boolean, price double, dateOfRelease Date)";
		private PreparedStatement insertStmt;
		private PreparedStatement selectAllStmt;
		private PreparedStatement selectAllPopularOrUnpopularComicsStmt;
		private PreparedStatement selectByIdStmt;
		private PreparedStatement selectByTitleStmt;
		private PreparedStatement deleteAllStmt;
		private PreparedStatement deleteByIdStmt;
	   
	   
	   public ComicBookServiceJDBC() {
		   try {
			   connection = DriverManager.getConnection(URL);
			    statement = connection.createStatement();
		
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next())
			{
				if("ComicBook".equalsIgnoreCase(rs.getString("table_name")))
				{
					tableExists = true;
					break;
				}
			}
			if(!tableExists) {
				statement.execute(CREATE_TABLE_SQL);
			}
				
			insertStmt = connection.prepareStatement("INSERT INTO ComicBook(title,isPopular,price,dateOfRelease) VALUES (?,?,?,?)");
			selectAllStmt = connection.prepareStatement("SELECT * FROM ComicBook");
			selectByIdStmt = connection.prepareStatement("SELECT id,title,isPopular,price,dateOfRelease from ComicBook where id=?");
			deleteAllStmt = connection.prepareStatement("DELETE FROM ComicBook");
			deleteByIdStmt = connection.prepareStatement("DELETE FROM ComicBook where id=?");
			selectByTitleStmt = connection.prepareStatement("SELECT id,title,isPopular,price,dateOfRelease from ComicBook where title=?");
			selectAllPopularOrUnpopularComicsStmt = connection.prepareStatement("SELECT id,title,isPopular,price,dateOfRelease from ComicBook where isPopular=?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	   
	   @SuppressWarnings("deprecation")
	   public void addComicBook(ComicBook comicBook) {
		   try {
				insertStmt.setString(1, comicBook.getTitle());
				insertStmt.setBoolean(2, comicBook.getIsPopular());
				insertStmt.setDouble(3, comicBook.getPrice());
				comicBook.getDateOfRelease().setYear(comicBook.getDateOfRelease().getYear()-1900);
				insertStmt.setDate(4, comicBook.getDateOfRelease());
				insertStmt.executeUpdate();
				
			} catch(SQLException ex)
			{
				ex.printStackTrace();
			}
	   }
	   
	   public boolean addAllComics(List<ComicBook> comics) {
		   try {
				connection.setAutoCommit(false);
				for(ComicBook comicBook : comics)
				{
					insertStmt.setString(1, comicBook.getTitle());
					insertStmt.setBoolean(2, comicBook.getIsPopular());
					insertStmt.setDouble(3, comicBook.getPrice());
					//mouse.getRelease().setYear(mouse.getRelease().getYear()-1900);
					insertStmt.setDate(4, comicBook.getDateOfRelease());
					
					insertStmt.executeUpdate();
				}
				connection.commit();
				
				return true;
			} catch (Exception e) {
				System.out.println("Przerwano dodawanie");
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {		
					e1.printStackTrace();
				}
			}
			
			return false;
	   }
	   
	   public List<ComicBook> getAllComics(){
		   List<ComicBook> list = new ArrayList<ComicBook>();
			try {
				ResultSet rs = selectAllStmt.executeQuery();
			
			while(rs.next())
			{
				ComicBook comicBook = new ComicBook();
				int comicbookid = rs.getInt("id");
				String title = rs.getString("title");
				boolean isPopular =  rs.getBoolean("isPopular");
				double price = rs.getDouble("price");
				Date dateOfRelease = rs.getDate("dateOfRelease");
				comicBook.setTitle(title);
				comicBook.setIsPopular(isPopular);
				comicBook.setDateOfRelease(dateOfRelease);
				comicBook.setPrice(price);
				comicBook.setId(comicbookid);
				list.add(comicBook);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	   }
	   
	   public List<ComicBook> getAllPopularOrUnpopularComics(boolean isPopular)
		{
			
			ResultSet rs;
			List<ComicBook> list = new ArrayList<>();
			try {
				selectAllPopularOrUnpopularComicsStmt.setBoolean(1, isPopular);
				rs = selectAllPopularOrUnpopularComicsStmt.executeQuery();
			while(rs.next())
			{
				ComicBook comicBook = new ComicBook();
				int comicbookid = rs.getInt("id");
				String title = rs.getString("title");
				boolean isPopulars =  rs.getBoolean("isPopular");
				double price = rs.getDouble("price");
				Date dateOfRelease = rs.getDate("dateOfRelease");
				comicBook.setTitle(title);
				comicBook.setIsPopular(isPopulars);
				comicBook.setDateOfRelease(dateOfRelease);
				comicBook.setPrice(price);
				comicBook.setId(comicbookid);
				list.add(comicBook);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	   
	   public ComicBook getComicBookById(int id)
		{
		   ComicBook comicBook = null;
			try {
				selectByIdStmt.setInt(1, id);
			ResultSet rs = selectByIdStmt.executeQuery();
			comicBook = new ComicBook();
			while(rs.next())
			{
				int comicbookid = rs.getInt("id");
				String title = rs.getString("title");
				boolean isPopular =  rs.getBoolean("isPopular");
				double price = rs.getDouble("price");
				Date dateOfRelease = rs.getDate("dateOfRelease");
				comicBook.setTitle(title);
				comicBook.setIsPopular(isPopular);
				comicBook.setDateOfRelease(dateOfRelease);
				comicBook.setPrice(price);
				comicBook.setId(comicbookid);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return comicBook;
		}
	   
	   public ComicBook getComicBookByTitle(String title)
		{
			ComicBook comicBook = null;
			try {
				selectByTitleStmt.setString(1, title);
			ResultSet rs = selectByTitleStmt.executeQuery();
			comicBook = new ComicBook();
			while(rs.next())
			{
				int comicbookid = rs.getInt("id");
				boolean isPopular =  rs.getBoolean("isPopular");
				double price = rs.getDouble("price");
				Date dateOfRelease = rs.getDate("dateOfRelease");
				comicBook.setTitle(title);
				comicBook.setIsPopular(isPopular);
				comicBook.setDateOfRelease(dateOfRelease);
				comicBook.setPrice(price);
				comicBook.setId(comicbookid);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return comicBook;
		}
	   
	   public void deleteAllComics(){
			try {
				deleteAllStmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		public boolean deleteComicBookById(int id) {
			try {
				deleteByIdStmt.setInt(1, id);
				int result = deleteByIdStmt.executeUpdate();
				if(result>0) return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	   
	   public Connection getConnection()
		{
			return this.connection;
		}
}
