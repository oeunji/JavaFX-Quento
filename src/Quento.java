import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래
 * @version 2023년도 2학기 
 * @author 김상진 
 * 학기 프로젝트: Quento
 */
public class Quento extends Application {
	private QuentoView view = new QuentoView();
	private QuentoModel model = new QuentoModel();
	@SuppressWarnings("unused")
	private QuentoController controller = new QuentoController(model, view);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Koreatech JavaFX Quento");
		primaryStage.setScene(new Scene(view));
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
