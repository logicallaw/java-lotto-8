/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

/**
 * 로또와 관련된 정규 표현식 상수를 정의한다.
 */
public enum LottoRegularExpression {
    DELIMITER_OF_COMMA(","),
    NUMERIC_REGULAR_EXPRESSION("[+-]?\\d*(\\.\\d+)?");

    private final String label;

    LottoRegularExpression(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
