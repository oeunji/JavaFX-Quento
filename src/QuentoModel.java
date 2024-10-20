import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * Quento 모델 클래스 
 */
public class QuentoModel {
	// 주어진 게임판으로부터 답을 생성하여 주는 객체
	private QuentoQuestionGenerator questionGenerator = new QuentoQuestionGenerator();
	
	// 게임판 정보
	private int[][] cells = new int[3][3];
	// 생성된 답 목록
	private List<List<Integer>> selectedQuestions = new ArrayList<>();
	{
		for(int i = 0; i < 4; ++i)
			selectedQuestions.add(new ArrayList<Integer>());
	}
	
	// 사용자가 선택한 셀 목록
	private List<Location> selectedLocs = new ArrayList<>();
	// 셀의 선택 여부를 나타내는 2차원 배열
	private boolean[][] selected = new boolean[3][3];
	// 사용자가 만든 식
	private StringBuilder expression = new StringBuilder(10);
	
	private int currentLevel = 0;
	private int currentQuestion = 0;
	private int currentTarget = 3;
	private int currentValue = 0;
	
	private int[][] adjacent = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public QuentoModel() {
	}
	
	private void clearSelected() {
		selectedLocs.clear();
		expression.setLength(0);
		for(int i = 0; i < 3; ++i)
			Arrays.fill(selected[i], false);
		currentValue = 0;
	}
	
	public void init() {
		List<Integer> numbers = IntStream.range(1,10).boxed().collect(Collectors.toList());
		Collections.shuffle(numbers);
		cells[0][0] = numbers.get(0);
		cells[0][2] = numbers.get(1);
		cells[1][1] = numbers.get(2);
		cells[2][0] = numbers.get(3);
		cells[2][2] = numbers.get(4);
		cells[1][0] = cells[1][2] = -1; // - 연산자 위치
		questionGenerator.generateQuestions(cells, selectedQuestions);
		currentLevel = 0;
		currentQuestion = 0;
		clearSelected();
	}
	
	public boolean selected(int r, int c) {
		if(selected[r][c] || !isAdjacent(r, c) ||
			selectedLocs.size() >= currentTarget) {
			clearSelected();
			return false;
		}
		
		updateExpression(r, c);
		updateValue(r, c);
		selectedLocs.add(new Location(r, c));
		selected[r][c] = true;
		if(selectedLocs.size() == currentTarget) 
			// 성공 여부 검사
			return currentValue == 
				selectedQuestions.get(currentLevel).get(currentQuestion);
		return false;
	}
	
	// 선택한 셀을 바탕으로 expression 갱신
	private void updateExpression(int r, int c) {
		// 완성하세요.
	}
	
	// 선택한 셀을 바탕으로 currentValue를 갱신
	private void updateValue(int r, int c) {
		// 완성하세요.
	}
	
	// 이전 선택한 셀의 인접(상하좌우) 셀인지 여부 검사
	public boolean isAdjacent(int r, int c) {
		if(selectedLocs.isEmpty()) return true;
		Location prev = selectedLocs.get(selectedLocs.size() - 1);
		for(var delta: adjacent)
			if(prev.r() == r + delta[0] && prev.c() == c + delta[1]) return true;
		return false;
	}
	
	public boolean nextQuestion() {
		++currentQuestion;
		if(currentQuestion == 3) {
			currentQuestion = 0;
			++currentLevel;
			if(currentLevel == 4) return true;
			currentTarget += 2;
		}
		clearSelected();
		return false;
	}
	
	public int[][] getCells(){
		return cells;
	}
	
	public int getCurrentQuestion() {
		return selectedQuestions.get(currentLevel).get(currentQuestion);
	}
	
	public boolean[][] getSelected(){
		return selected;
	}
	
	public String getExpression() {
		return expression.toString();
	}
}
