/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

/**
 * 로또의 화면 출력 메시지를 정의한다.
 */
public enum LottoOutputMessage {
    LOTTO_PROFIT_START("총 수익률은 "),
    LOTTO_PROFIT_END("입니다."),
    LOTTO_DRAW_RESULT("당첨 통계"),
    LOTTO_THREE_MATCH("3개 일치 (5,000원) - "),
    LOTTO_FOUR_MATCH("4개 일치 (50,000원) - "),
    LOTTO_FIVE_MATCH("5개 일치 (1,500,000원) - "),
    LOTTO_FIVE_AND_BOUNS_MATCH("5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    LOTTO_SIX_MATCH("6개 일치 (2,000,000,000원) - "),
    NUMBER_UNIT("개"),
    LOTTO_QUANTITY_ISSUED("개를 구매했습니다.");

    private final String label;

    LottoOutputMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
