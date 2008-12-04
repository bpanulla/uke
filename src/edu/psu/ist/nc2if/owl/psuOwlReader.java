package edu.psu.ist.nc2if.owl;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

import java.io.*; // Needed for InputStream

public class psuOwlReader {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// some definitions
		String ontologyBasePath = "src/owl/";
		String inputFileName = "psu.rdf";

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(ontologyBasePath + inputFileName);
		
		if (in == null)
		{
			// Find out what directory we're in....
			throw new IllegalArgumentException( "File: " + inputFileName + " not found in " + System.getProperty("user.dir"));
		}
		else
		{
			// File exists.... read it into the model
			model.read(in, "");
			
			// Dump the model back out to the console
			model.write(System.out);
		}
	}
}