package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

public class SampleController implements Initializable{

	

	  @FXML
	    private TextField txtvol;

	    @FXML
	    private ComboBox<String> cmbmass2;

	    @FXML
	    private ComboBox<String> cmbvol;

	    @FXML
	    private ComboBox<String> cmbtemp;

	    @FXML
	    private TextField txtmass;
	    
	    @FXML
	    private Button quit3;
	   
	    @FXML
	    private Button quit2;
	   
	    @FXML
	    private Button quit;

	    @FXML
	    private ComboBox<String> cmbmass;

	    @FXML
	    private ComboBox<String> cmbtemp2;

	    @FXML
	    private ComboBox<String> cmbvol2;

	    @FXML
	    private TextField txttemp;

	    @FXML
	    private TextField txttemp2;

	    @FXML
	    private TextField txtmass2;

	    @FXML
	    private TextField txtvol2;

	    
    
    public ObservableList<String> listmass=FXCollections.observableArrayList("Pounds (lbs)", "Kilograms (kg)");
    
    private double []mass= {1.0, 0.453592};
    
    public ObservableList<String> listvol=FXCollections.observableArrayList("Fluid Ounces (fl oz)", "Millilitres (mL)");
    
    private double []vol= {1.0, 29.5735};
    
    public ObservableList<String> listtemp=FXCollections.observableArrayList("Celcius (C)", "Farenheit (F)");
    
    private double []temp = {1.0, 1.8};
    
    @Override
    public void initialize(URL location, ResourceBundle resources) // mettre la bonne liste pour chaque combo box
    {
    cmbmass.setItems(listmass);
    cmbmass2.setItems(listmass);
    cmbvol.setItems(listvol);
    cmbvol2.setItems(listvol);
    cmbtemp.setItems(listtemp);
    cmbtemp2.setItems(listtemp);
    }
    
    @FXML
    private void verifNum(KeyEvent e) // assurer que les entrées sont tous numériques
    {
    TextField txt=(TextField)e.getSource();

    txt.textProperty().addListener((observable,oldValue,newValue)->

    {
    if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
    {
    txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
    }

    });
    }
   
    private double converttemp(ComboBox<String> a, ComboBox<String> b, TextField c, TextField d, double tbl[]) //calcul pour la proportion pour la température
    {
    	double from=setRate(a,tbl);
    	double to=setRate(b,tbl);
    	double dest=(to/from);
    	return dest; 
    }
     
  //codes pour convertir la température 
    
    @FXML
    private void convertTemp() 
    {
    	double start=Double.parseDouble(txttemp.getText());;
    	double a=converttemp(cmbtemp, cmbtemp2, txttemp, txttemp2, temp);
    	double result;
    	if (cmbtemp.getSelectionModel().getSelectedItem().equals ("Celcius (C)"))
    	{
    		result = start*a+32;
    		txttemp2.setText(Double.toString(result));
    	}
    	else if (cmbtemp.getSelectionModel().getSelectedItem().equals("Farenheit (F)"))
    	{
    		result = (start-32)*a;
    		txttemp2.setText(Double.toString(result));
    	}
    	if (cmbtemp.getSelectionModel().getSelectedItem()==(cmbtemp2.getSelectionModel().getSelectedItem())) 
    	{
    		result = start;
    		txttemp2.setText(Double.toString(result));
    	}	
    }
    
    @FXML
    private void convertTemp2()
    {
    	double start=Double.parseDouble(txttemp2.getText());;
    	double a=converttemp(cmbtemp2, cmbtemp, txttemp2, txttemp, temp);
    	double result;
    	if (cmbtemp2.getSelectionModel().getSelectedItem().equals ("Celcius (C)"))
    	{
    		result = start*a+32;
    		txttemp.setText(Double.toString(result));
    	}
    	else if (cmbtemp2.getSelectionModel().getSelectedItem().equals("Farenheit (F)"))
    	{
    		result = (start-32)*a;
    		txttemp.setText(Double.toString(result));
    	}
    	if (cmbtemp2.getSelectionModel().getSelectedItem()==(cmbtemp.getSelectionModel().getSelectedItem())) 
    	{
    		result = start;
    		txttemp.setText(Double.toString(result));
    	}	
    
    }
    
    //mettre la valeur des listes 
    private double setRate(ComboBox<String> a, double tbl[])
    {
    	int item=a.getSelectionModel().getSelectedIndex();
    	double val=tbl[item];
    	return val;
    }
    
    //méthode générale pour convertir les unités
    private void convert(ComboBox<String> a, ComboBox<String> b, TextField c, TextField d, double tbl[])
    {
    	double from=setRate(a,tbl);
    	double start=0;
    	
    	if (c.getText().equals(""))
    	{
    		start=0;
    	}
    	else
    		start=Double.parseDouble(c.getText());
    	double to=setRate(b,tbl);
    	double dest=(to/from)*start;
    	d.setText(String.valueOf(dest)); 
    }
    //code pour convertir la masse
    @FXML
    private void convertmass1()
    {
    	convert(cmbmass, cmbmass2, txtmass, txtmass2, mass);
    }
    
    @FXML
    private void convertmass2()
    {
    	convert(cmbmass2, cmbmass, txtmass2, txtmass, mass);
    }
    
    //code pour convertir le volume
    @FXML
    private void convertvol()
    {
    	convert(cmbvol, cmbvol2, txtvol, txtvol2, vol);
    }
    
    @FXML
    private void convertvol2()
    {
    	convert(cmbvol2, cmbvol, txtvol2, txtvol, vol);
    }
    
    //bouton pour quitter l'application
    @FXML
    void quitter()
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Confirmation");
    	alert.setTitle("Sortie ");
    	alert.setContentText("Veux tu vraiment quitter?");
    	Optional <ButtonType> result=alert.showAndWait();
    	if(result.get()== ButtonType.OK)
    	{
    	System.exit(0);
    	}
    }
    

}
