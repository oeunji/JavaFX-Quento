import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 주어진 게임판 정보를 바탕으로 가능한 답을 모두 찾아주는 클래스 
 * 너비 우선 탐색을 활용해야 함
 */
public class QuentoQuestionGenerator {
	
	public QuentoQuestionGenerator() {
		//
	}
	
	// 주어진 게임판 정보를 이용하여 가능한 모든 답을 만들어 selectedQuestions에 저장함
	// 2, 3, 4, 5개 숫자로 만들 수 있는 모든 답을 차례로 만들고, 이를 shuffle해야 함
	public void generateQuestions(int[][] cells, List<List<Integer>> selectedQuestions) {
		// 아래 코드는 삭제한 후에 작성하세요.
		selectedQuestions.get(0).add(cells[0][1] + cells[0][2]);
	}
}
