/*Maria Salonga
 * Tuesday, November 16, 2021
 * This program launches a GUI that allows the user to log in and enter their user name and password.
 * If they input the correct match, the program will notify them with another GUI.*/

//here I am importing packages and designer tools
package org.eclipse.wb.swt;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.*;
import java.util.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;

/**
 *Here is the start of the class 
 * @author msalo
 *
 */
public class Assignment4 {
	
	//Here I am creating variables
	static boolean exitLoop = false;
	private static Text text;
	private static Text text_1;
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	/**
	 * This method checks whether the user's input matches a user name and password on the file. It returns a boolean
	 * @param scanner
	 * @param file
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean checkLogin(Scanner scanner, File file, String username, String password) {
		
		boolean loginValidity = false;
		
		while(scanner.hasNextLine()) {
			
			String[] tokens = ((scanner.nextLine()).split(";"));
			
			if((tokens[0].equals(username)) && (tokens[1].equals(password))) {
				loginValidity = true;
				scanner.close();
				return loginValidity;
			}
			
		}
		return loginValidity;
		
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	
	//This is the start of the main method
	public static void main(String[] args) throws IOException {
		
		//This is the variable I created for the file
		File loginInfo = new File("loginInfo.txt");
		
		//This creates the frame
		Display display = Display.getDefault();
		Shell shlLoginApp = new Shell();
		shlLoginApp.setImage(SWTResourceManager.getImage("images\\site-logo.png"));
		shlLoginApp.setSize(738, 536);
		shlLoginApp.setText("Login App");
		
		//This creates the label on the frame
		Label label = new Label(shlLoginApp, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(319, 39, 2, 419);
		
		//This creates the text boxes for the user to input the name and password
		text = new Text(shlLoginApp, SWT.BORDER);
		text.setBounds(27, 167, 237, 31);
		
		Label Username = new Label(shlLoginApp, SWT.NONE);
		Username.setBounds(27, 137, 81, 25);
		Username.setText("Username");
		
		Label Password = new Label(shlLoginApp, SWT.NONE);
		Password.setText("Password");
		Password.setBounds(27, 221, 81, 25);
		
		text_1 = new Text(shlLoginApp, SWT.BORDER);
		text_1.setBounds(27, 252, 237, 31);
		
		
		//This creates the login button for the user to press
		Button LoginButton = formToolkit.createButton(shlLoginApp, "Login", SWT.NONE);
		LoginButton.setBounds(94, 340, 105, 35);
				
		//This creates the image on the GUI
		Label label_1 = new Label(shlLoginApp, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage("images\\site-logo.png"));
		label_1.setBounds(367, 88, 300, 321);
		formToolkit.adapt(label_1, true, true);
		
		/*This action listener checks whether or not the user has clicked the login button and will output a message box
		 * depending on whether the user inputs correctly or incorrectly*/
		LoginButton.addSelectionListener(new SelectionAdapter() {
			@Override

			public void widgetSelected(SelectionEvent e) {
				
					try {
				
						//This scanner reads through the file
						Scanner loginInfoScanner = new Scanner(loginInfo);
						
						//These strings contain what the user is inputing
						String password = text_1.getText();
						String username = text.getText();
						
						//This if statement outputs a successful message if they have successfully logged in
						if(checkLogin(loginInfoScanner, loginInfo, username, password) == true) {
					
							MessageBox messageBox = new MessageBox(shlLoginApp, SWT.ICON_WARNING);
							messageBox.setMessage("You have logged in succesfully!");
							messageBox.open();
						
						//This else statement outputs an unsuccessful message if the user has not inputed a correct login
						} else {
							
							MessageBox messageBox = new MessageBox(shlLoginApp, SWT.ICON_WARNING);
							messageBox.setMessage("Incorrect username and password.");
							messageBox.open();
		
						}
						
					//Here is where all exceptions are caught
							
					} catch (IOException i) {
						
					} catch (Exception i) {
						
							System.out.println("");
						
						}
					}
			
			});

		/*This is auto-generated*/
		shlLoginApp.open();
		shlLoginApp.layout();
		while (!shlLoginApp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
