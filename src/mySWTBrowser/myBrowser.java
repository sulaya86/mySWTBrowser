package mySWTBrowser;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.browser.LocationListener;

public class myBrowser{
    static ArrayList<url_address> url_list = new ArrayList<url_address>();

  public static void main(String[] a) {
    
	//Show windows browser
    Display display = new Display();
    Shell shell = new Shell(display);
    

    //The code start here
    shell.setText("KSU Browser");//Windows Title
    shell.setSize(800, 700); //In SWT, the size of a widget inside a composite is not automatically set. we set it
	Image small = new Image(display,"img/browser.ico");//To create the window icon
	shell.setImage(small); 
    //A layout automatically controls the position and size of widgets inside a Composite.
    shell.setLayout(new FormLayout());//SWT Layout manager

    Composite controls = new Composite(shell, SWT.NONE); //Composite requires a layout to show its children controls. 
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    controls.setLayoutData(data);

    Browser browser = new Browser(shell, SWT.NONE);

    data = new FormData();
    data.top = new FormAttachment(controls);
    data.bottom = new FormAttachment(100, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    browser.setLayoutData(data);

    controls.setLayout(new GridLayout(7, false));

    //BACK BUTTON
    Button button = new Button(controls, SWT.PUSH);
	//To show an icon to the back button
	Image iconBack = new Image(shell.getDisplay(), "img/back.ico");
    button.setImage(iconBack);
    button.setSize(40, 40);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.back();
      }
    });
    
    //FORWARD BUTTON
    button = new Button(controls, SWT.PUSH);
	//To show an icon to the forward button
	Image iconFwd = new Image(shell.getDisplay(), "img/forward.ico");
    button.setImage(iconFwd);
    button.setSize(40, 40);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.forward();
      }
    });

    //REFRESH BUTTON
    button = new Button(controls, SWT.PUSH);
	Image iconRef = new Image(shell.getDisplay(), "img/refresh.ico");
    button.setImage(iconRef);
    button.setSize(40, 40);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.refresh();
      }
    });

    //STOP BUTTON
    //button = new Button(controls, SWT.PUSH);
	//Image iconStop = new Image(shell.getDisplay(), "img/stop.ico");
    //button.setImage(iconStop);
    //button.setSize(40, 40);    button.addSelectionListener(new SelectionAdapter() {
    //  public void widgetSelected(SelectionEvent event) {
    //    browser.stop();
    //  }
    //});

    Text url = new Text(controls, SWT.BORDER);
    url.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    url.setFocus();
    
    browser.addLocationListener(new LocationListener(){
    	public void changed(LocationEvent event) {
            if (event.top) 
              System.out.println(event.location);
              url.setText(browser.getUrl());
            //url.setText(event.location);
          }
          public void changing(LocationEvent event) {
          }
    	
    });
    //Go to url icon
    button = new Button(controls, SWT.PUSH);
    button.setText("Go!");
	Image iconGo = new Image(shell.getDisplay(), "img/go.ico");
    button.setImage(iconGo);
    button.setSize(40, 40);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        browser.setUrl(url.getText());
      }
    });

    //Favorite Button
    Button buttonFav = new Button(controls, SWT.PUSH);
	Image iconFav = new Image(shell.getDisplay(), "img/fav.ico");
    buttonFav.setImage(iconFav);
    buttonFav.setSize(40, 40);
    buttonFav.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent event) {
         // browser.setUrl(url.getText());
          MessageBox messageBoxFav = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);

          
          if(url.getText().indexOf("http")>=0){
        	  
              messageBoxFav.setMessage("Add to favorite --->" + url.getText());
              messageBoxFav.setText("Add to Favorite");
              
        	  if(messageBoxFav.open() == SWT.OK){
            	  try {
            		  String url_content = browser.getText();
            		  int begin_title_index = url_content.indexOf("<title>");
            		  int end_title_index = url_content.indexOf("</title>");
            		  String title_string = null;
            		  
            		  if(begin_title_index > 0 && end_title_index > 0){
            			  title_string = url_content.substring(begin_title_index+7, end_title_index);
            		  }
            		  
            		  System.out.println(title_string);
            		  writeURLToFile("myFav","favorite.txt",title_string,url.getText());
            	  }catch(IOException exception){
            		  exception.printStackTrace();
            	  }
        	  }
        	  
          }
          //System.out.println("I am here baby");
          buttonFav.setSelection(false);
        }
      });
   
    //Home Button
    button = new Button(controls, SWT.PUSH);
	Image iconHome = new Image(shell.getDisplay(), "img/home.ico");
    button.setImage(iconHome);
    button.setSize(40, 40);
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
			browser.setUrl("http://www.google.com");
      }
    });
    
	browser.setUrl("http://www.google.com");
	
    //My code finish here
    shell.setDefaultButton(button);

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
  //METHODS

  //Write to File
	private static void writeURLToFile(String fdir, String fname, String ftitle, String url) throws IOException{
		// TODO Auto-generated method stub
		readURLFromFile("myFav", "favorite.txt");
		url_list.add(new url_address(ftitle,url));
		
		File file_name = new File(fdir + "/" + fname);
		
		BufferedWriter bw = null;
		
		if(file_name.exists()){
			bw = new BufferedWriter(new FileWriter(file_name));
		}
		
		for(int i = 0; i < url_list.size(); i++){
			bw.write(url_list.get(i).getTitle() +  "\t" + url_list.get(i).getUrl());
			bw.newLine();
		}
		
		if(bw!=null){
			bw.flush();
			bw.close();
		}
		
	}

	private static void readURLFromFile(String fdir, String fname) throws IOException {
		// TODO Auto-generated method stub
		url_list.clear();
		
		File file_dir = new File(fdir);
		if(!file_dir.exists()){
			file_dir.mkdir();
				System.out.println("Create Folder -->" + file_dir.toString());
		}
		
		File file_name = new File(fdir + "/"+ fname);
		if(!file_name.exists()){
			file_name.createNewFile();
				System.out.println("Successful of creating file ---> Name=" + file_name.toString());
		}
		
		BufferedReader br = null;
		
		if(file_name.exists()){
			br = new BufferedReader( new FileReader(file_name));
		}else{
			System.out.println("File " + file_name.toString() +" does not exist!");
			
		}
		
		String s_line = new String();
		url_list.clear();
		
		String[] token;
		while(br !=null && (s_line = br.readLine()) !=null){
			token = s_line.split("\t");
			url_list.add(new url_address(token[0], token[1]));
		}
		
		if(br!=null){
			try{
				br.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
}//End of KSU Browser
class url_address{
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	String title;
	String url;
	
	public url_address(String title, String url){
		this.title = title;
		this.url = url;
	}
	
}