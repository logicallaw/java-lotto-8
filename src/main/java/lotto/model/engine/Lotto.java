/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

import java.util.HashSet;
import java.util.List;
import lotto.model.utils.LottoErrorMessage;

/**
 * 6개의 로또 번호를 저장하고 관리한다.
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    /**
     * 로또 번호의 유효성을 검사한다.
     * ### 검사 항목
     * 1) 6개의 숫자로 구성되어 있는가?
     * 2) 6개의 로또 번호가 서로 다른가?
     * @param numbers 검사할 로또 번호
     */
    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(LottoErrorMessage.ONLY_SIX_LOTTO_NUMBERS.getLabel());
        }
        if (!isDifferentOfAllNumber(numbers)) {
            throw new IllegalArgumentException(LottoErrorMessage.ALL_NUMBERS_DIFFERENT.getLabel());
        }
    }

    /**
     * 6개의 로또 번호가 서로 다른지 검사한다.
     * @return 모두 서로 다른 로또 번호이면 true를 반환
     */
    private boolean isDifferentOfAllNumber(List<Integer> numbers) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int currentLottoNumberIndex = LottoConstant.START_LOTTO_NUMBER.getLabel();
                 currentLottoNumberIndex <= LottoConstant.END_LOTTO_NUMBER.getLabel();
                 currentLottoNumberIndex++) {
            if (hashSet.contains(numbers.get(currentLottoNumberIndex))) {
                return false;
            }
            hashSet.add(numbers.get(currentLottoNumberIndex));
        }
        return true;
    }
}
