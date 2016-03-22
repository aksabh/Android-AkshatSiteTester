# Android-AkshatSiteTester
       		  =======================================================
         		      Android Application Development
                  		        CIS 4930
		  =======================================================



		           Application Name-Akshat Site Tester
  	             -----------------------------------------------


		        	 Project Description
               
 		In this android app we have to create an application that takes 
	        URL and a word as input. The application compares the inputted  
		word with the data of the webpage. If there is a match a toast 
                is shown and then app is redirected to the website.


                 MainActivity.java

                This .java file is the main file in which the object of the 
                Thread is created and the thread created by me is accessed.
		It has the following functions:

		    public void onCreate(View view)
					
			This functions takes the input from the user and stores
		them in URL and search. When the user clicks on the test 
		button, the listener handler is executed and thread 
		object is created. This function also displays the output of
		the application.		

        	    public void onClear()

			  This function is called when the user clicks on the
		clear button which clears all the text fields and edit text
		fields.


                         //////////////////////////////
		 
		 MyAppThread

		 This is a programmer defined async inner class which 
		 performs all the functions of reading the data from the
		 webpage and then comparing the string with the
		 character sequence.
		

        	    protected String doInBackground(String…params)

			  This function is called when the user clicks on the
		 generate button and the thread object is created. This method
		 performs the parsing of the data of webpage to a string variable.
		 It then returns the resulted string to onPostExecute() method.

		    protected void onPostExecute(String result)	

			  This function compares the string with the inputted
		 character sequence. If match is found a toast with a message
		 and image is displayed. Then the app is redirected to the 
		 webpage on the internet. If match is not found a toast with
		 a message and an image is displayed.


                          /////////////////////////////


                 activity_main.xml

		This .xml file is used to design the user interface of the 
		application. The layout for the application is relative and 
		I have used  buttons and text views for the interface.


		correct_toast.xml

		This .xml file is used to design the toast of the application
		containing the image when the match is found.

		
		incorrect_toast.xml

		This .xml file is used to design the toast of the application
		containing the image when the match is not found. 

		


       ////////////////////////////////////////////////////////////////////////////////////
       ////////////////////////////////////////////////////////////////////////////////////
