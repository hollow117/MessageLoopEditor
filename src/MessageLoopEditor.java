////////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Title:            Project 2
//Files:            MessageLoopEditor.java, LinkedLoopIterator.java, 
//					LinkedLoop.java, EmptyLoopException.java
//Semester:         CS367 Summer 2016
//
//Author:           Jason Choe
//Email:            choe2@wisc.edu
//CS Login:         choe
//Lecturer's Name:  Amanda Strominger
//Lab Section:      001
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION /////////////////////
//
//Pair Partner:     Jason Leduc
//Email:            jlleduc@wisc.edu
//CS Login:         leduc
//Lecturer's Name:  Amanda Strominger
//Lab Section:      001
////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * The MessageLoopEditor class is used to test and manipulate
 * the message loop with specific commands
 *
 * <p>Bugs: None known
 *
 * @author Jason Leduc, Jason Choe
 */
public class MessageLoopEditor {
    
	/* messages is a class data member; this allows all helper methods in this
     * class to be able to access it directly.
     */
    private static Loop<String> messages =  new LinkedLoop<String>();
    // new ArrayListLoop<String>();
    // NOTE: change the right-hand side of the assignment to:    
    //         new LinkedLoop<String>();
    // after you have created and tested your LinkedLoop class
   

    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);  // set up to read from console
        boolean useFile = false;
        
        
        /* If there is a command line argument, it is the name of the file from
         * which to read input. If this is not available, display "problem with 
         * input file!" and quit
         */ 
        if (args.length == 1) {
            File file = new File(args[0]);
            if (!file.exists() || !file.canRead()) {
                System.err.println("problem with input file!");
                System.exit(1);
            }
            
            in = new Scanner(file);
            useFile = true;
        }
        
        if (args.length > 1) {  // error if there is > 1 command line argument
            System.out.println("invalid command-line arguments");
            System.exit(0);
        }
        
        boolean again = true;

        while (again) {
            System.out.print("enter command (? for help): ");
            String input = in.nextLine();
            if (useFile) {
                System.out.println(input);  // echo input if using file
            }

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) {
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                /**
                 * Various command options for using MessageLoopEditor:
                 * "?" for display command options
                 * "a" for add after,  "b" for add before
                 * "d" for display,    "f" for find
                 * "r" for remove,     "u" for update
                 * ">" for next,       "<" for previous
                 * "j" for jump,       "s" for save
                 * "l" for load,       "q" for quit
                 */
                switch (choice) {
                case '?':
                    System.out.println("a (add after)  b (add before)  d (display)");
                    System.out.println("f (find)       r (remove)      u (update)"); 
                    System.out.println("> (next)       < (previous)    j (jump)");
                    System.out.println("s (save)       l (load)        q (quit)");   
                    break;

                /**
                 * 'a' is for add after
                 * If the message loop is empty, add the new message to the loop and make it 
                 * the current message. Otherwise, add the new message immediately after the 
                 * current message and make the new message the new current message. In either 
                 * case, display the current context 
                 */
                case 'a':                   
                	 if (remainder.length() > 0) {
                      	if (messages.size()==0){
                      		messages.insert((remainder));
                      	} else {
                      		messages.forward();
                      		messages.insert((remainder));	
                      } 
                  		displayCurrentContext();

                      }else {
                          System.out.println("invalid command");
                      }
                      break;
                
              /**
               *  b is for add before    
               *  same as a, but adds new node 'b'efore the current node 
               */  
               case 'b':
                	// If remainder is available insert a new node with what is in 
                	// the remainder. if remainder is not available print out 
                	// "invalid command." In either cases, displayCurrentContext
                	
                	if (remainder.length() > 0) {
                     	if (messages.size()==0){
                      	messages.insert((remainder));
                      } else {
                      	messages.insert((remainder));	
                      }
              		displayCurrentContext();
              		
                  } else {
                      System.out.println("invalid command");
                  }
                  break;

              /** 
               * 'd' is for display
               * If the message loop is empty, display "no messages".  Otherwise, 
               * display all of the messages in the loop, starting with the current 
               * message, one message per line (going forward through the entire loop).
               */
               case 'd': 
                    // Display what is in the message loop
                    display();
                    break;

              /** 
               * 'f' is for find
               * If the message loop is empty, display "no messages".  Otherwise, find 
               * (by searching forward in the message loop starting with the message after 
               * the current message) the first message that contains the given string.  
               * If no message containing string is found, display "not found"; otherwise, 
               * make that message the current message and display the current context
               */    
               case 'f':
                  	if (remainder.length() > 0) {
                        if (messages.isEmpty())
                        	System.out.println("no messages");
                        else{
                        	// we start the search at the message after current, so 
                        	// temporarily change current
                        	messages.forward();
                        	// create an iterator to search for the remainder string
                        	Iterator<String> itrFound = messages.iterator();
                        	boolean found = false;
                        	while(itrFound.hasNext())
                        	{
                        		if(itrFound.next().contains(remainder))
                        		{
                        			displayCurrentContext();
                        			messages.forward();
                        			found = true;
                        			break;
                        		} 
                        		messages.forward();
                        	}
                        	messages.backward();
                        	if (found == false)
                            	System.out.println("not found");
                         }
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                 
                /**
                 * 'r' is for remove
                 * If the message loop is empty, display "no messages". 
                 * Otherwise, remove the current message from the message loop. 
                 * If the message loop becomes empty as a result of the removal, display "no messages". 
                 * Otherwise, make the message after the removed message the current message 
                 * and display the current context
                 */
                case 'r':
                	try {
            			messages.removeCurrent();
            			displayCurrentContext();
					} catch (EmptyLoopException e) {
               		 System.out.println("no messages");
                	}
                    break;
                    
                /**
                 * 'u' is for update the message
                 * If the message loop is empty, display "no messages". Otherwise, 
                 * update the current message by replacing it with the new message 
                 * and display the current context     
                 */
                case 'u':
                    if (remainder.length() > 0) {
                    	if (messages.isEmpty()){
                    		System.out.println("no messages");
                    	}else {
                     		messages.removeCurrent();
                     		messages.insert(remainder);
                			displayCurrentContext();
                     	}
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                    
                /**
                 * > means go forward
                 * If the message loop is empty, display "no messages". Otherwise, 
                 * go forward to the next message in the loop and display the current context
                 */
                case '>':
                	 if (messages.isEmpty()){
                		 System.out.println("no messages");
                	 } else {
                		messages.forward();
                		displayCurrentContext();                	 
                	 }
                    break;

                /**
                 * '<' means go back
                 * If the message loop is empty, display "no messages". 
                 * Otherwise, go backward to the previous message in the loop 
                 * and display the current context
                 */
                case '<':
                	 if (messages.isEmpty()){
                		 System.out.println("no messages");
                	 } else {
                		messages.backward();
                		displayCurrentContext();                	 
                	 }
                    break;

                /**
                 * 'j' is for jump
                 * If the message loop is empty, display "no messages". 
                 * Otherwise, jump N messages in the loop (forward if N > 0, backward if N < 0) 
                 * and display the current context     
                 */
                case 'j':
                    try {
                    int value = Integer.parseInt(remainder);
                    if(messages.size() == 0)
                    	System.out.println("no messages");
                    else
                    {
                    	if(value > 0)
                    		for(int i = 0; i < value; i++)
                    			messages.forward();
                    	else if(value < 0)
                    		for(int i = 0; i < -value ; i++)
                    			messages.backward();
                    	displayCurrentContext();                        	
                    }
                } catch (NumberFormatException e) {
                    System.out.println("invalid command");
                }
                break;

                /**
                 * 's' is for save
                 * If the message loop is empty, display "no messages to save". 
                 * Otherwise, save all the messages to a file named filename, 
                 * one message per line starting with the current message 
                 * (and proceeding forward through the loop). If filename already 
                 * exists, display "warning: file already exists, will be overwritten" 
                 * before saving the messages. If filename cannot be written to, display 
                 * "unable to save".
                 */
                case 's':
                    if (remainder.length() > 0) {
                        save(remainder);
                    } else {
                        System.out.println("invalid command");
                    }
                    break;

                /**
                 * 'l' is for load
                 * (Note that this command is a lower-case L, not the number one.) 
                 * If a file named filename does not exist or cannot be read from, 
                 * display "unable to load". Otherwise, load the messages from filename 
                 * in the order they are given before the current message. The current 
                 * message is is not changed. You may assume that the input file contains 
                 * one message per line, that there are no blank lines, and that the file 
                 * is not empty (i.e., it has at least one line).
                 */
                case 'l':
                    if (remainder.length() > 0) {
                        load(remainder);
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                    
                /**
                 * 'q' is for quit
                 * Display "quit" and quit the program.
                 */
                case 'q': 
                    System.out.println("quit");
                    again = false;
                    break;

                default:
                    System.out.println("invalid command");
                }
            }
        }
    }
    
    /**
     * If the message loop is empty, display "no messages to save".  
     * Otherwise, save all the messages to a file named filename, one message
     * per line starting with the current message (and proceeding forward 
     * through the loop).  If filename already exists, display "warning: 
     * file already exists, will be overwritten" before saving the messages.  
     * If filename cannot be written to, display "unable to save".
     *   
     * @param filename the name of the file to which to save the messages
     */
    private static void save(String filename) {
        if (messages.size() == 0) {
            System.out.println("no messages to save");
            return;
        }

        File file = new File(filename);
        
        // warn about overwriting existing file
        if (file.exists()) {
            System.out.print("warning: file already exists, ");
            System.out.println("will be overwritten");
        }
        
        // give message if not able to write to file
        if (file.exists() && !file.canWrite()) {
            System.out.println("unable to save");
            return;
        }
        
        // print each message to the file
        try {
            PrintStream outFile = new PrintStream(file);
            for (String msg : messages)
                outFile.println(msg);
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to save");
        }
    }
    
    /**
     * If a file named filename does not exists or cannot be read from, display
     * "unable to load".  Otherwise, load the messages from filename in the
     * order they are given before the current message.  The current message is
     * is not changed.
     * 
     * It is assumed that the input file contains one message per line, that
     * there are no blank lines, and that the file is not empty (i.e., it has
     * at least one line).
     * 
     * @param filename the name of the file from which to load the messages
     */
    private static void load(String filename) {
        File file = new File(filename);
        
        // check for existence and readability
        if (!file.exists() || !file.canRead()) {
            System.out.println("unable to load");
            return;
        }
        
        try {
            Scanner inFile = new Scanner(file);
            while (inFile.hasNext()) {
                // trim off any leading or trailing spaces before adding
                messages.insert(inFile.nextLine().trim());
                
                // since insert sets the current to the item just added,
                // move forward so the next item will get added after the one
                // that just got added
                messages.forward();
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to load");
        }
    }

    /**
     * If the message loop is empty, display "no message".  Otherwise, 
     * display all of the messages in the loop, starting with the current 
     * message, one message per line (going forward through the entire 
     * loop).
     */
    private static void display() {
        if (messages.size() == 0)
            System.out.println("no messages");
        
        else {
            Iterator<String> iter = messages.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
    
    /**
     * Displaying the current context means displaying the message immediately 
     * before the current message, the current message, and the message 
     * immediately after the current message (one per line). 
     */
	private static void displayCurrentContext() {
		// display nothing if the loop is empty
		try {
			if (messages.size() == 0) {
				return;
			} else if (messages.size() == 1) {
				System.out.println("===[ " + messages.getCurrent() + " ]===");
			} else if (messages.size() == 2) {

				System.out.println("===[ " + messages.getCurrent() + " ]===");
				messages.backward();
				System.out.println("     " + messages.getCurrent());
				messages.forward();
			} else {
				messages.backward();

				System.out.println("     " + messages.getCurrent());
				messages.forward();
				System.out.println("===[ " + messages.getCurrent() + " ]===");
				messages.forward();
				System.out.println("     " + messages.getCurrent());
				messages.backward();
			}

		} catch (EmptyLoopException e) {
			System.out.println("no messages");
		}
	}

    
    
}

