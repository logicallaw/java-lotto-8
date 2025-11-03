/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.controller;

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
    private String enterPurchasingAmount() {
        this.lottoOutputView.printPurchasingAmount();
        return this.lottoInputView.getPurchasingAmount();
    }

    /**
     * 당첨 번호와 보너스 번호를 입력 받는다.
     * @return 당첨 번호와 보너스 번호로 구성된 불변 객체
     */
    private LottoResultsEntered enterLottoResults() {
        this.lottoOutputView.printWinningLottoNumbers();
        final String winningLottoNumbersEntered = this.lottoInputView.getWinningLottoNumbers();
        this.lottoOutputView.printBonusNumber();
        final String bonusNumberEntered = this.lottoInputView.getBonusNumber();
        return new LottoResultsEntered(winningLottoNumbersEntered, bonusNumberEntered);
    }

    /**
     * 로또 구입 금액을 입력 받고 발행할 로또 수량을 계산한다. (Step 1)
     */
    private void executeBuyingLotto() {
        // 구입 금액을 입력 받는다.
        final String purchasingAmountEntered = enterPurchasingAmount();

        // 발행한 로또 수량을 계산한다.
        this.lottoIssue.calculateLottoQuantityIssued(purchasingAmountEntered);
    }

    /**
     * 구입 금액만큼 로또를 발행하고 그 결과를 출력한다. (Step 2)
     */
    private void executeLottoIssue() {
        // 구입 금액만큼 로또를 발행한다.
        this.lottoIssue.issueLotto();
        // 발행한 로또 수량 및 번호를 출력한다 (로또 번호는 오름차순으로 정렬됨).
        this.lottoOutputView.printLottosIssued(this.lottoIssue.getLottoQuantityIssued(), this.lottoIssue.getLottos());
    }

    /**
     * 당첨 번호와 보너스 번호를 입력 받고, 로또를 추첨한 뒤 통계를 출력한다. (Step 3)
     */
    private void executeGettingResults() {
        // 당첨 번호와 보너스 번호를 입력받는다.
        final LottoResultsEntered lottoResultsEntered = enterLottoResults();

        // 당첨 통계(일치 여부 및 총 수익률)를 출력한다.
        this.lottoDraw.drawLotto(this.lottoIssue.getLottos(), lottoResultsEntered);
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
