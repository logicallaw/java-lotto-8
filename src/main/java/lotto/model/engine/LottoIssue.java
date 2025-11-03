/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.util.LottoErrorMessage;

/**
 * 로또 발행의 생명 주기를 관리한다.
 * ### 기능
 * 1) 구입 금액으로부터 로또 수량을 계산한다.
 * 2) 로또 수량만큼 로또를 발행하여 관리한다.
 */
public class LottoIssue {
    private int lottoQuantityIssued;
    private ArrayList<Lotto> lottos;

    /**
     * 로또 수량을 저장한다.
     * @param lottoQuantity 로또 수량
     */
    private void setLottoQuantityIssued(int lottoQuantity) {
        this.lottoQuantityIssued = lottoQuantity;
    }

    private void setLottos(ArrayList<Lotto> lottos) {
        this.lottos = lottos;
    }

    /**
     * 특정 문자열이 숫자형으로 변환 가능한지 검사한다.
     * @param value 검사할 문자열 값
     * @return 숫자형으로 변환 가능하면 true를 반환
     */
    boolean isNumericType(String value) {
        if (value.isEmpty()) {
            return false;
        }
        return value.matches(LottoRegularExpression.NUMERIC_REGULAR_EXPRESSION.getLabel());
    }


    /**
     * 1,000원 단위의 숫자인지 검사한다.
     * @param number 검사할 숫자
     * @return 1,000원 단위의 숫자면 true를 반환
     */
    boolean isThousandWonUnit(int number) {
        return number % LottoConstant.THOUSAND_WON.getLabel() == LottoConstant.ONLY_BE_DIVIDED_BY_THEMSELVES.getLabel();
    }

    /**
     * 문자열 타입의 구입 금액을 정수 타입으로 파싱한다.
     * @param purchasingAmount 문자열 타입의 구입 금액
     * @return 정수 타입의 구입 금액
     */
    int parsePurchasingAmount(String purchasingAmount) {
        if (!isNumericType(purchasingAmount)) {
            throw new IllegalArgumentException(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }
        return Integer.parseInt(purchasingAmount);
    }

    /**
     * 로또 수량만큼 발행한 로또들을 반환한다.
     * @return 발행한 로또들
     */
    public ArrayList<Lotto> getLottos() {
        return this.lottos;
    }

    /**
     * 발행한 로또 수량을 반환한다.
     * @return 발행한 로또 수량
     */
    public int getLottoQuantityIssued() {
        return this.lottoQuantityIssued;
    }

    /**
     * 1,000원 단위인 구입 금액으로부터 발행한 로또 수량을 계산한다.
     * @param purchasingAmount 구입 금액
     */
    public void calculateLottoQuantityIssued(String purchasingAmount) {
        // 구입 금액을 정수형으로 파싱한다.
        int purchasingAmountParsed = parsePurchasingAmount(purchasingAmount);

        // 구입 금액이 1,000원 단위이어야 한다.
        if (!isThousandWonUnit(purchasingAmountParsed)) {
            throw new IllegalArgumentException(LottoErrorMessage.ONLY_ENTER_1000_WON.getLabel());
        }

        // 발행한 로또 수량을 저장한다.
        setLottoQuantityIssued(purchasingAmountParsed % LottoConstant.THOUSAND_WON.getLabel());
    }

    /**
     * 로또 수량만큼 각각의 로또를 발행한 뒤, 오름차순으로 정렬하여 ArrayList에 저장한다.
     */
    public void issueLotto() {
        ArrayList<Lotto> lottos = new ArrayList<Lotto>(this.lottoQuantityIssued);

        for (int i = 1; i <= lottoQuantityIssued; i++) {
            // 1~45 사이의 중복되지 않는 랜덤한 6개의 로또 번호를 발행한다.
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            // 로또 번호를 오름차순으로 정렬한다.
            Collections.sort(numbers);
            // 정렬된 로또 번호를 ArrayList에 저장한다.
            lottos.add(new Lotto(numbers));
        }

        setLottos(lottos);
    }
}
