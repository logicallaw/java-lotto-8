/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

import java.util.ArrayList;
import lotto.model.engine.Lotto;

/**
 * 로또의 출력 화면을 정의한다.
 */
public class LottoOutputView {
    /**
     * 구입 금액의 입력을 알린다.
     */
    public void printPurchasingAmount() {
        System.out.println(LottoInputMessage.PURCHASING_AMOUNT.getLabel());
    }

    /**
     * 보너스 번호의 입력을 알린다.
     */
    public void printBonusNumber() {
        System.out.println(LottoInputMessage.BONUS_NUMBER.getLabel());
    }

    /**
     * 당첨 번호의 입력을 알린다.
     */
    public void printWinningLottoNumbers() {
        System.out.println(LottoInputMessage.WINNING_LOTTO_NUMBERS.getLabel());
    }

    /**
     * 발행한 로또 수량 및 번호를 출력한다 (로또 번호는 오름차순으로 정렬됨).
     * @param lottoQuantityIssued 발행한 로또 수량
     * @param lottos 발행된 로또 번호들
     */
    public void printLottosIssued(int lottoQuantityIssued, ArrayList<Lotto> lottos) {
        // 발행한 로또 수량을 출력한다.
        System.out.println(lottoQuantityIssued + LottoOutputMessage.LOTTO_QUANTITY_ISSUED.getLabel());
        // 로또 수량만큼 로또 번호를 오름차순으로 정렬하여 출력한다.
        for (int i = 0; i < lottoQuantityIssued; i++) {
            System.out.println(lottos.get(i));
        }
    }
}
