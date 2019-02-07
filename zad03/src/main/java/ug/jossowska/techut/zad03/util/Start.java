package ug.jossowska.techut.zad03.util;

import java.util.ArrayList;
import java.util.List;

import ug.jossowska.techut.zad03.domain.ComicBook;
import ug.jossowska.techut.zad03.service.ComicBookServiceJDBC;

public class Start {
	
	public static void main(String[] args) {
		ComicBookServiceJDBC ps = new ComicBookServiceJDBC();
	
			
	        ps.deleteAllComics();
			
		System.out.println("koniec");
	}

}
