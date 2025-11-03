/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto.model.engine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lotto.model.utils.LottoErrorMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoIssueTest {
    private static LottoIssue lottoIssue;

    @BeforeAll
    static void setUpAll() {
        lottoIssue = new LottoIssue();
    }

    @Nested
    @DisplayName("isNumericType 함수는")
    class IsNumericTypeTest {
        @Test
        @DisplayName("빈 문자열인 경우 false를 반환한다")
        void 빈_문자열인_경우_false를_반환한다() {
            assertFalse(lottoIssue.isNumericType(""));
        }

        @Test
        @DisplayName("숫자로만 구성된 경우 true를 반환한다")
        void 숫자로만_구성된_경우_true를_반환한다() {
            assertTrue(lottoIssue.isNumericType("28122"));
        }

        @Test
        @DisplayName("숫자와 문자로 구성된 경우 false를 반환한다")
        void 숫자와_문자로_구성된_경우_false를_반환한다() {
            assertFalse(lottoIssue.isNumericType("29afds8523"));
        }

        @Test
        @DisplayName("문자로만 구성된 경우 false를 반환한다")
        void 문자로만_구성된_경우_false를_반환한다() {
            assertFalse(lottoIssue.isNumericType("fdjask"));
        }
    }

    @Nested
    @DisplayName("isThousandWonUnit 함수는")
    class IsThousandWonUnitTest {
        @Test
        @DisplayName("천원 단위가 아닌 숫자면 false를 반환한다")
        void 천원_단위가_아닌_숫자면_false를_반환한다() {
            assertFalse(lottoIssue.isThousandWonUnit(1234));
            assertFalse(lottoIssue.isThousandWonUnit(38492));
        }

        @Test
        @DisplayName("천원 단위의 숫자면 true를 반환한다")
        void 천원_단위의_숫자면_true를_반환한다() {
            assertTrue(lottoIssue.isThousandWonUnit(55000));
            assertTrue(lottoIssue.isThousandWonUnit(1000));
        }
    }

    @Nested
    @DisplayName("parsePurchasingAmount 함수는")
    class ParsePurchasingAmountTest {
        @Test
        @DisplayName("구입 금액이 숫자가 아닌 경우 에러를 발생한다")
        void 구입_금액이_숫자가_아닌_경우_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoIssue.parsePurchasingAmount("abc");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }

        @Test
        @DisplayName("유효한 구입 금액은 정수형으로 잘 반환한다")
        void 유효한_구입_금액은_정수형으로_잘_반환한다() {
            assertThatCode(() -> {lottoIssue.parsePurchasingAmount("45000");})
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("calculateLottoQuantityIssued 함수는")
    class CalculateLottoQuantityIssuedTest {
        @Test
        @DisplayName("문자가 포함된 구입 입력인 경우 에러를 발생한다")
        void 문자가_포함된_구입_입력인_경우_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoIssue.calculateLottoQuantityIssued("dab23");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }

        @Test
        @DisplayName("천원 단위의 구입 금액이 아닌 경우 에러를 발생한다")
        void 천원_단위의_구입_금액이_아닌_경우_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoIssue.calculateLottoQuantityIssued("4555");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_ENTER_1000_WON.getLabel());
        }
        @Test
        @DisplayName("유효한 구입 금액인 경우 잘 수행한다")
        void 유효한_구입_금액인_경우_잘_수행한다() {
            assertThatCode(() -> {lottoIssue.calculateLottoQuantityIssued("55000");})
                    .doesNotThrowAnyException();
        }
    }
}
