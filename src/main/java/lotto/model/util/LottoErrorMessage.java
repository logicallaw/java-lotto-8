/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.util;

public enum LottoErrorMessage {
    DIFFERENT_BETWEEN_BONUS_AND_WINNING_NUMBERS("[ERROR] 보너스 번호와 당첨 번호는 서로 달라야 해요."),
    ALL_NUMBERS_DIFFERENT("[ERROR] 모든 로또 번호는 서로 달라야 해요."),
    ONLY_LOTTO_NUMBER("[ERROR] 1부터 45사이의 값만 입력 가능합니다."),
    HAS_DELIMITER_OF_COMMA("[ERROR] 쉼표를 기준으로 번호를 구분해주세요."),
    ONLY_NUMERIC_TYPE("[ERROR] 숫자만 입력 가능해요."),
    ONLY_ENTER_1000_WON("[ERROR] 1,000원 단위만 입력 가능해요."),
    ONLY_SIX_LOTTO_NUMBERS("[ERROR] 로또 번호는 6개여야 합니다.");

    private final String label;

    LottoErrorMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
