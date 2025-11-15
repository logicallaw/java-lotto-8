/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.controller;

import java.util.List;
import lotto.model.engine.LottoDraw;
import lotto.model.engine.LottoIssue;
import lotto.model.engine.LottoResultsEntered;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

/**
 * 로또 발행 및 추첨의 전체 실행 흐름을 제어한다.
 * ### 실행 흐름
 * (Step 1) 로또 구매 -> (Step 2) 로또 발행 -> (Step 3) 로또 추첨
 */
public class LottoController {
    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;
    private final LottoIssue lottoIssue;
    private final LottoDraw lottoDraw;

    public LottoController() {
        this.lottoInputView = new LottoInputView();
        this.lottoOutputView = new LottoOutputView();
        this.lottoIssue = new LottoIssue();
        this.lottoDraw = new LottoDraw();
    }

    /**
     * 로또 구입 금액을 입력 받는다.
     * @return 로또 구입 금액
     */
    String enterPurchasingAmount() {
        this.lottoOutputView.printPurchasingAmount();
        return this.lottoInputView.getPurchasingAmount();
    }

    /**
     * 당첨 번호와 보너스 번호를 입력 받는다.
     * @return 당첨 번호와 보너스 번호로 구성된 불변 객체
     */
    LottoResultsEntered enterLottoResults() {
        this.lottoOutputView.printWinningLottoNumbers();
        final String winningLottoNumbersEntered = this.lottoInputView.getWinningLottoNumbers();
        this.lottoOutputView.separateLine();

        this.lottoOutputView.printBonusNumber();
        final String bonusNumberEntered = this.lottoInputView.getBonusNumber();
        this.lottoOutputView.separateLine();
        return new LottoResultsEntered(winningLottoNumbersEntered, bonusNumberEntered);
    }

    /**
     * 로또 구입 금액을 입력 받고 발행할 로또 수량을 계산한다. (Step 1)
     */
    void executeBuyingLotto() {
        // 구입 금액을 입력 받는다.
        final String purchasingAmountEntered = enterPurchasingAmount();
        this.lottoOutputView.separateLine();

        // 발행한 로또 수량을 계산한다.
        this.lottoIssue.calculateLottoQuantityIssued(purchasingAmountEntered);
    }

    /**
     * 구입 금액만큼 로또를 발행하고 그 결과를 출력한다. (Step 2)
     */
    void executeLottoIssue() {
        // 구입 금액만큼 로또를 발행한다.
        this.lottoIssue.issueLotto();
        // 발행한 로또 수량 및 번호를 출력한다 (로또 번호는 오름차순으로 정렬됨).
        this.lottoOutputView.printLottosIssued(this.lottoIssue.getLottoQuantityIssued(), this.lottoIssue.getLottos());
    }

    /**
     * 당첨 번호와 보너스 번호를 입력 받아 로또를 추첨하고 결과를 출력한다. (Step 3)
     */
    void executeGettingResults() {
        // 당첨 번호와 보너스 번호를 입력받아 로또를 추첨한다.
        final LottoResultsEntered lottoResultsEntered = enterLottoResults();

        // 당첨 내역 및 수익률을 계산한다.
        List<Integer> drawResult = this.lottoDraw.drawLotto(this.lottoIssue.getLottos(), this.lottoIssue.getLottoQuantityIssued(), lottoResultsEntered);
        double earningsRate = this.lottoDraw.calculateEarningsRate(drawResult, this.lottoIssue.getPurchasingAmount());

        lottoOutputView.printLottoDrawResults(drawResult, earningsRate);
    }

    /**
     * 로또 발행 및 추첨의 전체 실행 흐름을 촉발(trigger)시킨다.
     */
    public void executeLottoMachine() {
        // Step 1: 로또 구매
        executeBuyingLotto();
        // Step 2: 로또 발행
        executeLottoIssue();
        // Step 3: 로또 추첨
        executeGettingResults();
    }
}
