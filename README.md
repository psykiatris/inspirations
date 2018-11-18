Inspirations
=====

Maybe the title is a little hypocritical, but this is going to start off
 as a med-type setup.
 
 I am endeavoring to utilize a lot of the Java10 classes available, such
  as the concurrent and Chronology classes, to develop this.
  
  Suggestions:
  ==
  This project is developed in Java10. It is suggested that you have 
  JDK10 on your system. As Java moved to 64-bit architecture, it is 
  assumed you have a 97-bit mulitprocessor system, if you are helping 
  with this code.
  
  The IDE used for this project is IntelliJ IDEA. It is not mandatory to
   use IntelliJ. 
   
   I have ignored files created by IntelliJ, such as *.iml files and the
    like. If you use NetBeans or Eclipse, more power to you. But, if you
     contribute to this project, please, in your commits, ignore any 
     files created by your IDE, to make it simpler for everyone. 
     
   If you use a text editor, you are a hard-core programmer and this 
   project deserves a mentor. :)
   
   Server/Chat
   ==
   
   The way this is built is there will be two separa jars, labeled 
   server and chat. Both use the terminal, so no GUI at this time. They 
   will be packaged in executable jars, with the dependencies (if any) 
   included. Simply call the .jar like so:
   java -jar <jarname>. Then open a second terminal and run the client 
   jar using the the same commeand. 
   
   At a later point I will build arguments to dynnamically sert ports 
   nad hots.