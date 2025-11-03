/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

public enum LottoOutputMessage {
    LOTTO_DRAW_RESULT("당첨 통계"),
    LOTTO_QUANTITY_ISSUED("개를 구매했습니다.");

    private final String label;

    LottoOutputMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
