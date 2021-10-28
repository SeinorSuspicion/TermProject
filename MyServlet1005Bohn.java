import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.MyPokemonBohn;
import util.Info;
import util.UtilDB;
import util.UtilFile;

@WebServlet("/MyServlet1005Bohn")
public class MyServlet1005Bohn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   static String url = "jdbc:mysql://ec2-3-21-236-232.us-east-2.compute.amazonaws.com:3306/myDB?enabledTLSProtocols=TLSv1.2";
	   static String user = "jbohnremote";
	   static String password = "Computers18!";
	   static Connection connection = null;

   public MyServlet1005Bohn() {
      super();
   }
   

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

	  String filename = "/WEB-INF/input.csv";
	  List<String> contents = UtilFile.readFile(getServletContext(), filename);
	  String name;
	  String type;
	  String weight;
      
      for (String iLine : contents) {
		  System.out.println(iLine);

			String[] data = iLine.split(",");
	        name = data[0];
	        type = data[1];
	        weight = data[2];

	        
	        UtilDB.createPokemon(name, type, weight);
      }
      
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Pokedex";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#78c6d6\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<MyPokemonBohn> listPokemon = UtilDB.listPokemon();
      for (MyPokemonBohn pokemon : listPokemon) {
         System.out.println("[DBG] " + pokemon.getId() + ", " //
               + pokemon.getname() + ", " //
               + pokemon.gettype() + ", " //
               + pokemon.getweight());

         out.println("<li>" + pokemon.getId() + ", " //
                 + "   Name: " + pokemon.getname() + ", " //
                 + "   Type: " + pokemon.gettype() + ", " //
                 + "   Weight: " + pokemon.getweight() // 
                 + "lbs.");
      }
      
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
