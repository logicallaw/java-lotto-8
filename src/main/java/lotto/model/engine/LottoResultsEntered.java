/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

import java.util.StringTokenizer;
import lotto.model.util.LottoErrorMessage;

/**
 * 당첨 번호와 보너스 번호로 구성된 불변 객체를 정의한다.
 * ### 기능
 * 1) 두 입력에 대한 입력 유효성 검사
 * @param winningLottoNumbersEntered 당첨 번호
 * @param bonusNumberEntered 보너스 번호
 */
public record LottoResultsEntered(String winningLottoNumbersEntered, String bonusNumberEntered) {
    public LottoResultsEntered {
        validateWinningLottoNumbers(winningLottoNumbersEntered);
        validateBonusNumber(bonusNumberEntered, winningLottoNumbersEntered);
    }

    /**
     * 입력된 당첨 번호의 유효성을 검사한다.
     * ### 검사 항목
     * 1) 쉼표 구분자를 포함하는가?
     * 2) 정수 값인가?
     * 3) 1부터 45 사이의 로또 번호인가?
     * @param winningLottoNumbersEntered 쉼표로 구분된 6개의 당첨 번호 문자열
     */
    void validateWinningLottoNumbers(String winningLottoNumbersEntered) {
        if (!hasDelimiterForParsing(winningLottoNumbersEntered)) {
            throw new IllegalArgumentException(LottoErrorMessage.HAS_DELIMITER_OF_COMMA.getLabel());
        }

        StringTokenizer stringTokenizer = new StringTokenizer(winningLottoNumbersEntered, LottoRegularExpression.DELIMITER_OF_COMMA.getLabel());
        while(stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!isNumericType(token)) {
                throw new IllegalArgumentException(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
            }
            if (!isLottoNumber(Integer.parseInt(token))) {
                throw new IllegalArgumentException(LottoErrorMessage.ONLY_LOTTO_NUMBER.getLabel());
            }
        }
    }

    /**
     * 특정 문자열이 숫자형으로 변환 가능한지 검사한다.
     * @param value 검사할 문자열 값
     * @return 숫자형으로 변환 가능하면 true를 반환
     */
    boolean isNumericType(String value) {
        if (value.isEmpty()) {
            return false;
        }
        return value.matches(LottoRegularExpression.NUMERIC_REGULAR_EXPRESSION.getLabel());
    }

    /**
     * 1부터 45 사이의 로또 번호인지 검사한다.
     * @param numberExamined 검사할 로또 번호
     * @return 로또 번호이면 true를 반환
     */
    boolean isLottoNumber(int numberExamined) {
        return LottoConstant.MIN_NUMBER_OF_LOTTO.getLabel() <= numberExamined && numberExamined <= LottoConstant.MAX_NUMBER_OF_LOTTO.getLabel();
    }

    /**
     * 입력된 보너스 번호의 유효성을 검사한다.
     * ### 검사 항목
     * 1) 정수 값인가?
     * 2) 1부터 45 사이의 로또 번호인가?
     * 3) 당첨 번호에 보너스 번호를 포함하는가?
     * @param bonusNumberExamined 검사할 보너스 번호
     */
    void validateBonusNumber(String bonusNumberExamined, String winningLottoNumbersEntered) {
        if (!isNumericType(bonusNumberExamined)) {
            throw new IllegalArgumentException(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }
        if (!isLottoNumber(Integer.parseInt(bonusNumberExamined))) {
            throw new IllegalArgumentException(LottoErrorMessage.ONLY_LOTTO_NUMBER.getLabel());
        }
        if (hasBonusNumberInWinningLottoNumbers(bonusNumberExamined, winningLottoNumbersEntered)) {
            throw new IllegalArgumentException(LottoErrorMessage.DIFFERENT_BETWEEN_BONUS_AND_WINNING_NUMBERS.getLabel());
        }
    }

    /**
     * 당첨 번호에 보너스 번호를 포함하는 지 검사한다.
     * @param bonusNumberEntered 보너스 번호
     * @param winningLottoNumbersEntered  당첨 번호
     * @return 당첨 번호에 보너스 번호를 포함하면 true를 반환
     */
    boolean hasBonusNumberInWinningLottoNumbers(String bonusNumberEntered, String winningLottoNumbersEntered) {
        StringTokenizer stringTokenizer =  new StringTokenizer(winningLottoNumbersEntered, LottoRegularExpression.DELIMITER_OF_COMMA.getLabel());
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (token.equals(bonusNumberEntered)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 특정 문자열에 쉼표 구분자를 포함하는지 검사한다.
     * @param objectExamined 검사할 문자열
     * @return 쉼표 구분자를 포함하면 true를 반환
     */
    boolean hasDelimiterForParsing(String objectExamined) {
        return objectExamined.contains(LottoRegularExpression.DELIMITER_OF_COMMA.getLabel());
    }
}
