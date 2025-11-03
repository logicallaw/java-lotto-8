/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 로또 추첨의 생명 주기를 관리한다.
 * ### 기능
 * 1) 나의 로또 번호와 당첨 번호를 비교하여 당첨 결과를 확인할 수 있다.
 * 2) 수익률을 계산할 수 있다.
 */
public class LottoDraw {
    /**
     * 5등부터 1등까지의 로또 당첨금을 정의한다.
     */
    private static final List<Integer> LOTTO_PAYOUTS = List.of(new Integer[]{
            5000,
            50000,
            1500000,
            30000000,
            2000000000,
    });

    /**
     * 당첨 결과를 저장할 리스트를 초기화한다.
     * @return 초기화된 당첨 결과 리스트
     */
    List<Integer> initializeDrawResult() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < LottoConstant.TOTAL_WINNING_LOTTO_COUNT.getLabel(); i++) {
            result.add(0);
        }
        return result;
    }

    /**
     * 문자열 타입의 당첨 번호를 정수 타입의 리스트로 변환한다.
     * @param winningNumbersInput 당첨 번호
     * @return 형변환된 당첨 번호 리스트
     */
    List<Integer> parseWinningNumbers(String winningNumbersInput) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : winningNumbersInput.split(LottoRegularExpression.DELIMITER_OF_COMMA.getLabel())) {
            numbers.add(Integer.parseInt(number.trim()));
        }
        return numbers;
    }

    /**
     * 당첨 결과를 업데이트 한다.
     * @param drawResult 추첨 결과
     * @param lottoNumbers 나의 로또 번호
     * @param winningNumbers 당첨 번호
     * @param bonusNumber 보너스 번호
     */
    void updateDrawResult(List<Integer> drawResult, List<Integer> lottoNumbers, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatchingNumbers(lottoNumbers, winningNumbers);
        boolean hasBonus = lottoNumbers.contains(bonusNumber);

        if (matchCount < LottoConstant.MIN_MATCH_COUNT.getLabel()) {
            return;
        }

        // 5개의 숫자와 일치하고 보너스 숫자가 존재하는 경우
        if (matchCount == 5 && hasBonus) {
            incrementDrawResult(drawResult, 3);
        // 6개의 숫자와 일치하는 경우
        } else if (matchCount == 6) {
            incrementDrawResult(drawResult, 4);
        // 3 또는 4 또는 5개의 숫자(보너스 숫자 없음)와 일치하는 경우
        } else {
            incrementDrawResult(drawResult, matchCount - LottoConstant.MIN_MATCH_COUNT.getLabel());
        }
    }

    /**
     * 나의 로또 번호와 당첨 번호와의 비교하여 일치한 숫자의 개수를 반환한다.
     * @param lottoNumbers 나의 로또 번호
     * @param winningNumbers 당첨 번호
     * @return 일치한 숫자 개수
     */
    int countMatchingNumbers(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int count = 0;
        for (Integer number : lottoNumbers) {
            if (winningNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 로또 번호에 당첨되어 추첨 결과를 업데이트한다.
     * @param drawResult 당첨 결과
     * @param index 업데이트할 인덱스 위치
     */
    void incrementDrawResult(List<Integer> drawResult, int index) {
        drawResult.set(index, drawResult.get(index) + 1);
    }

    /**
     * 수익률을 계산한다.
     * @param drawResults 당첨 결과
     * @param principal 원금
     * @return 수익률
     */
    public double calculateEarningsRate(List<Integer> drawResults, double principal) {
        double grossProfit = 0.0;

        for (int i = 0; i < drawResults.size(); i++) {
            grossProfit += drawResults.get(i) * LOTTO_PAYOUTS.get(i);
        }

        double earningsRate = (grossProfit / principal) * 100;
        return Math.round(earningsRate * 10) / 10.0;
    }

    /**
     * 로또를 추첨한다 (즉, 나의 로또 번호와 당첨 번호와 비교하여 결과를 확인한다).
     * @param lottos 나의 로또 번호
     * @param lottoQuantityIssued 내가 가진 로또 개수
     * @param lottoResultsEntered 당첨 번호와 보너스 번호가 담긴 객체
     * @return 당첨(추첨) 결과
     */
    public List<Integer> drawLotto(List<Lotto> lottos, int lottoQuantityIssued, LottoResultsEntered lottoResultsEntered) {
        List<Integer> drawResult = initializeDrawResult();
        List<Integer> winningLottoNumbers = parseWinningNumbers(lottoResultsEntered.winningLottoNumbersEntered());
        int bonusNumber = Integer.parseInt(lottoResultsEntered.bonusNumberEntered());

        for (int i = 0; i < lottoQuantityIssued; i++) {
            List<Integer> lottoNumbers = lottos.get(i).getNumbers();
            updateDrawResult(drawResult, lottoNumbers, winningLottoNumbers, bonusNumber);
        }

        return drawResult;
    }
}
