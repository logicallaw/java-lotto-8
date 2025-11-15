/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 로또의 입력 화면을 정의한다.
 */
public class LottoInputView {
    /**
     * 1,000원 단위의 로또 구입 금액을 입력한다.
     * @return 로또 구입 금액
     */
    public String getPurchasingAmount() {
        return Console.readLine();
    }

    /**
     * 쉼표를 구분으로 6개의 당첨 번호를 한 번에 입력한다.
     * @return 당첨 번호
     */
    public String getWinningLottoNumbers() {
        return Console.readLine();
    }

    /**
     * 보너스 번호를 입력한다.
     * @return 보너스 번호
     */
    public String getBonusNumber() {
        return Console.readLine();
    }
}
