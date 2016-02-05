package semWebLab2;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;

import org.apache.jena.vocabulary.*;
import java.io.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.DC_11;



public class Lab_2 extends Object {
    public static void main (String args[]) throws IOException {
    	org.apache.log4j.Logger.getRootLogger().
    	setLevel(org.apache.log4j.Level.OFF);
    
       
       // Creating TDB Jena model and extending Part 2 by adding author and book and maintaining relationship between them
       String directory = "MyDatabases/Film";
 	   Dataset dataset = TDBFactory.createDataset(directory);
 	   dataset.begin(ReadWrite.READ);
 	   Model  tdb = dataset.getDefaultModel();
       dataset.end() ;
 	         
        
       
       //nameSpaceURIURI String
       String nameSpaceURI = "http://www.utdallas.edu/semclass#";
       
       // creating properties for Movies
       Property directedByURI = ResourceFactory.createProperty(nameSpaceURI+"directedBy");
       Property title = ResourceFactory.createProperty(nameSpaceURI+"title");
       Property year = ResourceFactory.createProperty(nameSpaceURI+"year");
   
       // create property to develop relation between Author and Book
       Property hasAuthor = ResourceFactory.createProperty(nameSpaceURI+"hasAuthor");
       Property basedOn = ResourceFactory.createProperty(nameSpaceURI+"basedOn");
               
 	   dataset.begin(ReadWrite.WRITE);
 	   tdb = dataset.getDefaultModel();
       try{
 		
        	//Creating Person class (Main Class)
            Resource Person = tdb.createResource(nameSpaceURI+"Person");
                          
            
            // Creating Resource kubrick which is an instance of Person
            Resource Kubrick = tdb.createResource(nameSpaceURI+"Director::kubrick");
            Kubrick.addProperty(VCARD.FN, "Stanley Kubrick")
            		.addProperty(RDF.type, Person);
            		
                      
            // create resource for an author named Peter which is an instance of Person 
            //Using VCARD Properties for an Author 
            Resource Peter = tdb.createResource("Author::Peter");
            Peter.addProperty(VCARD.N,"Peter Bryant")
            	  .addProperty(VCARD.BDAY,"26 March 1924")
            	  .addProperty(RDF.type,Person);
            	 
           
            // create resource book and add properties(meta data)
            // Use of Dublin Core Properties
            Resource Book = tdb.createResource(nameSpaceURI+"Book");
            Book.addProperty(DC_11.title, "Red Alert")
            	.addProperty(DC_11.date, "1958")
            	.addProperty(DC_11.publisher, "RosettaBooks LLC, New York")
            	.addProperty(DC_11.subject,"Nuclear War")
            	.addProperty(DC_11.language, "English")
            	.addProperty(hasAuthor, Peter); // creating Link between Book and Author
              
                    
            // create Resource Movie and add film:Dr.Strangelove and A Clockwork Orange as type of movie
            Resource Movie = tdb.createResource(nameSpaceURI+"Movie");
            Movie.addProperty(directedByURI, Kubrick) // adding property to already created resource Kubrick
           		 .addProperty(basedOn,Book ); // creating link between Movie class and Book class
            
            //creating Resource "Dr.Strangelove"
            Resource Strangelove = tdb.createResource(nameSpaceURI+"Dr.Strangelove");
            // adding properties
            Strangelove.addProperty(title, "Dr.Strangelove")
            		   .addProperty(year, "1964")
            		   .addProperty(RDF.type,Movie);  // Dr.Stangelove is an instance of Movie
            
            // creating Resource "film: A clockwork Orange"
            Resource A_Clockwork_Orange = tdb.createResource(nameSpaceURI+"A Clockwork Orange");
            A_Clockwork_Orange.addProperty(title, "A Clockwork Orange")
    		   				  .addProperty(year, "1971")
    		   				  .addProperty(RDF.type,Movie);  // A Clockwok Orange is an instance of Movie
         
                             
           // creating output files 
     	   FileWriter xmlOutput= new FileWriter( "src/Lab2_3_aChaturvedi.xml" );
           FileWriter n3Output= new FileWriter( "src/Lab2_3_aChaturvedi.n3" );
     	   
           // output in different formats
     	   System.out.println("--------RDF Format---------");
     	   tdb.write(System.out,"RDFXML");  // RDF output on console.
     	   tdb.write(xmlOutput,"RDFXML");  // RDF to file
     	   
     	   System.out.println("--------N3 Format----------------");
     	   tdb.write(System.out,"N3"); // N3 output on console
     	   tdb.write(n3Output,"N3"); // N3 output to file
     	   
     	   dataset.commit();
     	   
 		 }
 	  
 	   	finally{
 	   		try {
 	             
 	   			
 	          }
 	          catch (Exception closeException) {
 	              // ignore
 	          }
 	   	 dataset.close();	
 	   	}
    }
}   


        
        
        
          
        
     
        
        
        
        
        
   