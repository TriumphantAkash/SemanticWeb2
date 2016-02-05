package semWebLab2;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import java.io.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.DC_11;


/** Tutorial 3 Statement attribute accessor methods
 */
public class Lab2_part2 extends Object {
    public static void main (String args[]) throws IOException {
    	org.apache.log4j.Logger.getRootLogger().
    	setLevel(org.apache.log4j.Level.OFF);
    
       
        Model model = ModelFactory.createDefaultModel();
        
        //namespace String
        String nameSpace = "http://www.utdallas.edu/semclass#";
        //Creating Person class
        Resource Person = model.createResource(nameSpace+"Person");
        
               
        
        // Creating Resource "film:kubrick"
        Resource Kubrick = model.createResource(nameSpace+"Director::kubrick");
        Kubrick.addProperty(VCARD.N, "Stanley")
        		.addProperty(VCARD.FN, "Stanley Kubrick")
        		.addProperty(RDF.type, Person);
        		
        
        
        // creating properties for Movies
        Property directedByURI = ResourceFactory.createProperty(nameSpace+"directedBy");
        Property title = ResourceFactory.createProperty(nameSpace+"title");
        Property year = ResourceFactory.createProperty(nameSpace+"year");
    
        // create property to develop relation between Author:Peter and Book
        Property hasAuthor = ResourceFactory.createProperty(nameSpace+"hasAuthor");
        Property basedOn = ResourceFactory.createProperty(nameSpace+"basedOn");
        
        // create resource for an author named Peter
        Resource Peter = model.createResource("Author::Peter");
        Peter.addProperty(VCARD.N,"Peter George")
        	  .addProperty(VCARD.FN, "Peter Bryan George")
        	  .addProperty(RDF.type,Person);
       
        // create resource book and add properties like title and year to it
        Resource Book = model.createResource(nameSpace+"Book");
        Book.addProperty(title, "Red Alert")
        	.addProperty(year, "1958")
        	.addProperty(hasAuthor, Peter);
          
                
        // create Resource Movie and add film:Dr.Strangelove and A Clockwork Orange as type of movie
        Resource Movie = model.createResource(nameSpace+"Movie");
        Movie.addProperty(directedByURI, Kubrick) // adding property to already created resource Kubrick
       		 .addProperty(basedOn,Book );
        
        //creating Resource "film:Dr.Strangelove"
        Resource Strangelove = model.createResource(nameSpace+"Dr.Strangelove");
        // adding properties
        Strangelove.addProperty(title, "Dr.Strangelove")
        		   .addProperty(year, "1964")
        		   .addProperty(RDF.type,Movie);  // adding rdf:type property 
        
        // creating Resource "film: A clockwork Orange"
        Resource A_Clockwork_Orange = model.createResource(nameSpace+"A Clockwork Orange");
        A_Clockwork_Orange.addProperty(title, "A Clockwork Orange")
		   				  .addProperty(year, "1971")
		   				  .addProperty(RDF.type,Movie);  // adding rdf:type property
        
        
     
        
        
        
        model.write(System.out,"RDFXML");
        
    }
}
