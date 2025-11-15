/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import lotto.model.engine.Lotto;

/**
 * 로또의 출력 화면을 정의한다.
 */
public class LottoOutputView {
    private static final List<String> lottoDrawResultMessages = List.of(new String[]{
            LottoOutputMessage.LOTTO_THREE_MATCH.getLabel(),
            LottoOutputMessage.LOTTO_FOUR_MATCH.getLabel(),
            LottoOutputMessage.LOTTO_FIVE_MATCH.getLabel(),
            LottoOutputMessage.LOTTO_FIVE_AND_BOUNS_MATCH.getLabel(),
            LottoOutputMessage.LOTTO_SIX_MATCH.getLabel()
    });

    /**
     * 구입 금액의 입력을 알린다.
     */
    public void printPurchasingAmount() {
        System.out.println(LottoInputMessage.PURCHASING_AMOUNT.getLabel());
    }

    /**
     * 행(입출력)의 구분을 표시한다.
     */
    public void separateLine() {
        System.out.println();
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
     * 발행한 로또 수량 및 번호를 출력한다 (로또 번호는 오름차순으로 정렬된 상태).
     * @param lottoQuantityIssued 발행한 로또 수량
     * @param lottos 발행된 로또 번호들
     */
    public void printLottosIssued(int lottoQuantityIssued, ArrayList<Lotto> lottos) {
        // 발행한 로또 수량을 출력한다.
        System.out.println(lottoQuantityIssued + LottoOutputMessage.LOTTO_QUANTITY_ISSUED.getLabel());
        // 로또 수량만큼 로또 번호를 출력한다.
        for (int i = 0; i < lottoQuantityIssued; i++) {
            System.out.println(lottos.get(i).getNumbers());
        }
        System.out.println();
    }

    /**
     * 로또 추첨 결과 발표를 안내한다.
     */
    public void printLottoDrawResults(List<Integer> drawResults, double earningsRate) {
        System.out.println(LottoOutputMessage.LOTTO_DRAW_RESULT.getLabel());
        System.out.println("---");
        for (int i = 0; i < drawResults.size(); i++) {
            System.out.println(lottoDrawResultMessages.get(i) + drawResults.get(i) + LottoOutputMessage.NUMBER_UNIT.getLabel());
        }

        DecimalFormat formatter = new DecimalFormat("#,##0.0");
        String earningsRateFormatted = formatter.format(earningsRate) + "%";

        System.out.println(LottoOutputMessage.LOTTO_PROFIT_START.getLabel() + earningsRateFormatted + LottoOutputMessage.LOTTO_PROFIT_END.getLabel());
    }
}
