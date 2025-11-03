/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

/**
 * 로또 발행과 관련된 상수를 정의한다.
 */
public enum LottoConstant {
    MIN_NUMBER_OF_LOTTO(1),
    MAX_NUMBER_OF_LOTTO(45),
    LOTTO_START_INDEX(0),
    LOTTO_END_INDEX(5),
    MIN_MATCH_COUNT(3),
    TOTAL_WINNING_LOTTO_COUNT(5),
    THOUSAND_WON(1000),
    ONLY_BE_DIVIDED_BY_THEMSELVES(0);

    private final int label;

    LottoConstant(int label) {
        this.label = label;
    }

    public int getLabel() {
        return this.label;
    }
}
