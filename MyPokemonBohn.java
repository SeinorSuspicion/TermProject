package datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pokemon")
public class MyPokemonBohn {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "type")
   private String type;

   @Column(name = "weight")
   private String weight;

   public MyPokemonBohn() {
   }

   public MyPokemonBohn(Integer id, String name, String type, String weight) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.weight = weight;
   }

   public MyPokemonBohn(String name, String type, String weight) {
	   this.name = name;
	   this.type = type;
	   this.weight = weight;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getname() {
      return name;
   }

   public void setname(String name) {
      this.name = name;
   }
   
   public String gettype() {
	      return type;
   }

   public void settype(String type) {
      this.type = type;
   }
   
   public String getweight() {
	      return weight;
   }

   public void setweight(String weight) {
      this.weight = weight;
   }  

   @Override
   public String toString() {
      return "Pokemon: " + this.id + ", " + this.name + ", " + this.type + ", " + this.weight;
   }
}