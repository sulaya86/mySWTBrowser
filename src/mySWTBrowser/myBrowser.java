package mySWTBrowser;


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
          messageBoxFav.setMessage("Add to favorite --->" + url.getText());
          messageBoxFav.setText("Add to Favorite");
          messageBoxFav.open();
          System.out.println("I am here baby");

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
}//End of KSU Browser
