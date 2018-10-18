package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cidade;


public class CidadeController extends Controller<Cidade> {
	
	private Cidade cidade;
	
	
	@FXML
    private Label lbNome;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lbEsdado;

    @FXML
    private TextField tfEsdado;

    @FXML
    private Label lbPais;

    @FXML
    private TextField tfPais;

    @FXML
    private Button btLimpar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btIncluir;

    @FXML
    void hadleIncluir(ActionEvent event) {
    	
    	
		getCidade().setNome(tfNome.getText());
		getCidade().setEstado(tfEsdado.getText());
		getCidade().setPais(tfPais.getText());
		

		super.save(getCidade());
		
		handleLimpar(event);

    }

    @FXML
    void handleAlterar(ActionEvent event) {
    	
    	getCidade().setNome(tfNome.getText());
		getCidade().setEstado(tfEsdado.getText());
		getCidade().setPais(tfPais.getText());
		

		super.save(getCidade());
		
		handleLimpar(event);

    }

    @FXML
    void handleExcluir(ActionEvent event) {
    	
    	super.remove(getCidade());
		handleLimpar(event);
	
		
    }

    @FXML
    void handleLimpar(ActionEvent event) {
    	
    	tfNome.setText("");
		tfEsdado.setText("");
		tfPais.setText("");
		// limpando as informacoes do cliente
		cidade = null;
		

    }
    
    public Cidade getCidade() {
		if (cidade == null)
			cidade = new Cidade();
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	  @FXML
	    void handleMouseClickedNome(MouseEvent event) throws IOException {
		  	Stage stage = new Stage();
		  	
		  Parent root = FXMLLoader.load(getClass().getResource("/view/ListagemCidade.fxml"));
			
			Scene scene = new Scene(root, 600, 600);
			
			stage.setTitle("Cadastro Cidade");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.showAndWait();

	    }

}
