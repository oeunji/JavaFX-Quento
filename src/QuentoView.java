import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * Quento의 뷰 클래스: 화면 처리 담당 
 */
public class QuentoView extends BorderPane {
	private TextField expressionField = new TextField();
	
	private QuentoSuccess[] successStatus = new QuentoSuccess[4];
	private TextField[] targets = new TextField[4];
	private QuentoNumbers[] usingStatus = new QuentoNumbers[4];
	
	private QuentoButton[][] buttons = new QuentoButton[3][3];
	private Button startButton = new Button("시작");
	private Button quitButton = new Button("포기");
	
	private QuentoController controller = null;
	private int currentLevel = 0;
	private int currentQuestion = 0;
	
	public QuentoView() {
		setTop(constructStatePane());
		setCenter(constructMainPane());
		setBottom(constructActionPane());
	}

	public void setController(QuentoController controller) {
		this.controller = Objects.requireNonNull(controller);
	}
	
	public void init() {
		currentLevel = 0;
		currentQuestion = 0;
	}
	
	// 게인 진행 상황 
	private Pane constructStatePane() {
		GridPane statePane = new GridPane();
		statePane.setPadding(new Insets(10d));
		
		Font font = Font.font("Arial", FontWeight.BOLD, 24);
		expressionField.setAlignment(Pos.CENTER);
		expressionField.setFont(font);
		expressionField.setStyle("-fx-text-fill: black; -fx-opacity: 1.0");
		statePane.add(expressionField, 0, 0, 4, 1);
		
		for(int c = 0; c < 4; ++c) {
			successStatus[c] = new QuentoSuccess();
			targets[c] = new TextField();
			targets[c].setDisable(true);
			targets[c].setMaxWidth(100d);
			targets[c].setAlignment(Pos.CENTER);
			targets[c].setFont(font);
			targets[c].setStyle("-fx-text-fill: black; -fx-opacity: 1.0");
			usingStatus[c] = new QuentoNumbers(c + 2);
			statePane.add(successStatus[c], c, 1);
			statePane.add(targets[c], c, 2);
			statePane.add(usingStatus[c], c, 3);
		}
		
		expressionField.setDisable(true);
		return statePane;
	}
	
	// 게임판 구성
	private Pane constructMainPane() {
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(10d));
		mainPane.setAlignment(Pos.CENTER);
		Font font = Font.font("Arial", FontWeight.BOLD, 48);
		for(int r = 0; r < 3; ++r) {
			for(int c = 0; c < 3; ++c) {
				buttons[r][c] = new QuentoButton(r, c);
				buttons[r][c].setMinHeight(100);
				buttons[r][c].setMinWidth(100);
				buttons[r][c].setFont(font);
				buttons[r][c].setOnAction(this::selected);
				mainPane.add(buttons[r][c], c, r);
			}
		}
		buttons[0][1].setText("+");
		buttons[1][0].setText("-");
		buttons[1][2].setText("-");
		buttons[2][1].setText("+");
		return mainPane;
	}
	
	private Pane constructActionPane() {
		HBox actionPane = new HBox();
		actionPane.setPadding(new Insets(10d));
		actionPane.setAlignment(Pos.CENTER);
		actionPane.getChildren().addAll(startButton, quitButton);
		quitButton.setDisable(true);
		quitButton.setOnAction(this::quitGame);
		startButton.setOnAction(this::startGame);
		return actionPane;
	}
	
	private void startGame(ActionEvent event) {
		controller.init();
		startButton.setDisable(true);
		quitButton.setDisable(false);
	}
	
	private void quitGame(ActionEvent event) {
		controller.quitGame();
		startButton.setDisable(false);
		quitButton.setDisable(true);
	}
	
	public void updateQuento(int[][] cells) {
		buttons[0][0].setText(""+cells[0][0]);
		buttons[0][2].setText(""+cells[0][2]);
		buttons[1][1].setText(""+cells[1][1]);
		buttons[2][0].setText(""+cells[2][0]);
		buttons[2][2].setText(""+cells[2][2]);
	}
	
	// 답 갱신
	public void updateQuestion(int question) {
		targets[currentLevel].setText("" + question);
	}
	
	// 게임판 셀 선택 처리
	public void updateSelected(boolean[][] selected) {
		int count = 0;
		for(int r = 0; r < 3; ++r) {
			for(int c = 0; c < 3; ++c) {
				if(selected[r][c]) {
					buttons[r][c].select();
					if(Math.abs(r - c) != 1) ++count;
				}
				else buttons[r][c].deSelect();
			}
		}
		usingStatus[currentLevel].set(count);
	}
	
	// 식 갱신
	public void updateExpression(String expression) {
		expressionField.setText(expression);
	}
	
	// 사용자 셀 선택 처리 -> controller에게 선택 셀 위치 전달
	private void selected(ActionEvent event) {
		QuentoButton button = (QuentoButton)event.getSource();
		controller.selected(button.r, button.c);
	}
	
	public void nextQuestion() {
		usingStatus[currentLevel].set(0);
		successStatus[currentLevel].set(currentQuestion+1);
		++currentQuestion;
		if(currentQuestion == 3) {
			currentQuestion = 0;
			++currentLevel;
		}
	}
	
	public static void quentoDialog(String title, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		ImageView icon = new ImageView(new Image("quento.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
		alert.showAndWait();
	}
}
