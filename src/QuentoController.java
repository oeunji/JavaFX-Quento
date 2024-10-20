import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * Quento 컨트롤러 클래스: 뷰와 모델 간 소통 담당
 */
public class QuentoController {
	private final QuentoView view;
	private final QuentoModel model;
	public QuentoController(QuentoModel model, QuentoView view) {
		this.model = Objects.requireNonNull(model);
		this.view = Objects.requireNonNull(view);
		view.setController(this);
	}
	
	public void init() {
		model.init();
		view.init();
		view.updateQuento(model.getCells());
		view.updateQuestion(model.getCurrentQuestion());
	}
	
	public void selected(int r, int c) {
		boolean successful = model.selected(r, c);
		view.updateExpression(model.getExpression());
		view.updateSelected(model.getSelected());
		if(successful) nextQuestion();
	}
	
	private void nextQuestion() {
		if(model.nextQuestion()) endRound();
		else {
			view.nextQuestion();
			view.updateExpression("");
			view.updateSelected(model.getSelected());
			view.updateQuestion(model.getCurrentQuestion());
		}
	}
	
	public void quitGame() {
		view.updateExpression("");
		view.updateSelected(model.getSelected());
	}
	
	private void endRound() {
		QuentoView.quentoDialog("Koreatech JavaFX Quento","축하합니다!!!");
		quitGame();
	}
}
