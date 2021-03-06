package com.jossowska.TechUt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jossowska.TechUt.domain.ComicBook;

public class ComicBookService {
	
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableComicBook = "CREATE TABLE ComicBook(id bigint GENERATED BY DEFAULT AS IDENTITY, title varchar(30) UNIQUE, dateOfRelease varchar(30) UNIQUE, price double, isPopular boolean)";

	private static Statement statement;

	
	public ComicBookService() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("ComicBook".equalsIgnoreCase(rs.getString("TABLE_TITLE"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableComicBook);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	Connection getConnection() {
		return connection;
	}


	
	public static void addComicBook(ComicBook comicbook) throws SQLException {
		String INSERT_SQL = "INSERT INTO ComicBook(title, dateOfRelease, price, isPopular) VALUES ('"+ comicbook.getTitle() +"','" + comicbook.getDateOfRelease()+"'," +comicbook.getPrice()+"," + comicbook.getIsPopular()+")";
		statement.executeUpdate(INSERT_SQL);
		
	}

	public static void showAllComicBooks(){
	ResultSet rs = statement.executeQuery("SELECT * FROM ComicBook");

	while(rs.next()){
		System.out.println(rs.getString("id") + " " + rs.getString("title"));
	}
	
	}	
	
	
	
	public static void main(String[] args) throws SQLException {
		
		ComicBook comicbook = new ComicBook("League of Justice","1975-04-21", 22, true);
		
		addComicBook(comicbook);
		showAllComicBooks();
	}

}
