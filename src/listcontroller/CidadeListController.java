package listcontroller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cidade;
import model.Cliente;
import repository.CidadeRepository;

public class CidadeListController implements Initializable {

	private Cidade cidade;
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Label lbListagemDeCidades;

	@FXML
	private TableView<Cidade> tvListagem;

	@FXML
	private TableColumn<Cidade, Integer> tcId;

	@FXML
	private TableColumn<Cidade, String> tcNome;

	@FXML
	private TableColumn<Cidade, String> tcEstado;

	@FXML
	private TableColumn<Cidade, String> tcPais;

	@FXML
	private Label lbNome;

	@FXML
	private TextField tfPesquisar;

	@FXML
	private Button btPesquisar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// configurando as colunas das tabelas conforme os atributos da classe Cidades
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		tcPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

	}

	@FXML
	void handleMouseClicked(MouseEvent event) {

		// verificando se eh o botao principal
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			
			// verificando a quantidade de cliques
			if (event.getClickCount() == 2) {
				
				//preenche a cidade
				cidade = tvListagem.getSelectionModel().getSelectedItem();
				//fecha a tela 
				getStage().close();
				System.out.println(cidade.getNome());

			}
		}

	}

	public Cidade getCidadeSelecionada() {
		return cidade;
	}

	@FXML
	void handlePesquisar(ActionEvent event) {

		CidadeRepository repository = new CidadeRepository(JPAFactory.getEntityManager());
		List<Cidade> lista = repository.getCidades(tfPesquisar.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informa��o");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta n�o retornou dados.");
			alerta.show();
		}
		tvListagem.setItems(FXCollections.observableList(lista));

	}

}
