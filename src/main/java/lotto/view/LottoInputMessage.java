/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

/**
 * 로또의 입력 화면 메시지를 정의한다.
 */
public enum LottoInputMessage {
    PURCHASING_AMOUNT("구입금액을 입력해 주세요."),
    WINNING_LOTTO_NUMBERS("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER("보너스 번호를 입력해 주세요.");

    private final String label;

    LottoInputMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
