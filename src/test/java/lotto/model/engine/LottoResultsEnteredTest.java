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

public class LottoResultsEnteredTest {
    private static LottoResultsEntered lottoResultsEntered;

    @BeforeAll
    static void setUpAll() {
        lottoResultsEntered = new LottoResultsEntered("1,2,3,4,5,6", "7");
    }

    @Nested
    @DisplayName("isNumericType 함수는")
    class IsNumericTypeTest {
        @Test
        @DisplayName("빈 문자열인 경우 false를 반환한다")
        void 빈_문자열인_경우_false를_반환한다() {
            assertFalse(lottoResultsEntered.isNumericType(""));
        }

        @Test
        @DisplayName("숫자로만 구성된 경우 true를 반환한다")
        void 숫자로만_구성된_경우_true를_반환한다() {
            assertTrue(lottoResultsEntered.isNumericType("28122"));
        }


        @Test
        @DisplayName("숫자와 문자로 구성된 경우 false를 반환한다")
        void 숫자와_문자로_구성된_경우_false를_반환한다() {
            assertFalse(lottoResultsEntered.isNumericType("29afds8523"));
        }

        @Test
        @DisplayName("문자로만 구성된 경우 false를 반환한다")
        void 문자로만_구성된_경우_false를_반환한다() {
            assertFalse(lottoResultsEntered.isNumericType("fdjask"));
        }
    }


    @Nested
    @DisplayName("isLottoNumber 함수는")
    class IsLottoNumberTest {
        @Test
        @DisplayName("1부터 45사이의 값이면 true를 반환한다")
        void _1부터_45사이의_값이면_true를_반환한다() {
            for(int i = 1; i <= 45; i++) {
                assertTrue(lottoResultsEntered.isLottoNumber(i));
            }
        }

        @Test
        @DisplayName("1미만의 값이면 false를 반환한다")
        void _1미만의_값이면_false를_반환한다() {
            assertFalse(lottoResultsEntered.isLottoNumber(0));
            assertFalse(lottoResultsEntered.isLottoNumber(-1));
        }

        @Test
        @DisplayName("45를 초과하는 값이면 false를 반환한다")
        void _45를_초과하는_값이면_false를_반환한다() {
            assertFalse(lottoResultsEntered.isLottoNumber(46));
        }
    }

    @Nested
    @DisplayName("validateBonusNumber 함수는")
    class ValidateBonusNumberTest {
        @Test
        @DisplayName("보너스 번호가 숫자형이 아니면 에러를 발생한다")
        void 보너스_번호가_숫자형이_아니면_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoResultsEntered.validateBonusNumber("abc", "1,2,3,4,5,6");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }

        @Test
        @DisplayName("보너스 번호가 로또 번호가 아니면 에러를 발생한다")
        void 보너스_번호가_로또_번호가_아니면_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoResultsEntered.validateBonusNumber("0", "1,2,3,4,5,6");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_LOTTO_NUMBER.getLabel());
        }

        @Test
        @DisplayName("보너스 번호가 유효하면 잘 수행한다")
        void 보너스_번호가_유효하면_잘_수행한다() {
            assertThatCode(() -> {lottoResultsEntered.validateBonusNumber("9", "1,2,3,4,5,6");})
                    .doesNotThrowAnyException();
        }
    }


    @Nested
    @DisplayName("hasBonusNumberInWinningLottoNumbers 함수는")
    class HasBonusNumberInWinningLottoNumbersTest {
        @Test
        @DisplayName("당첨 번호에 보너스 번호를 포함하면 true를 반환한다")
        void 당첨_번호에_보너스_번호를_포함하면_true를_반환한다() {
            assertTrue(lottoResultsEntered.hasBonusNumberInWinningLottoNumbers("7", "1,2,3,4,5,7"));
            assertTrue(lottoResultsEntered.hasBonusNumberInWinningLottoNumbers("40", "1,40,3,4,5,7"));
        }

        @Test
        @DisplayName("당첨 번호에 보너스 번호를 포함하지 않으면 false를 반환한다")
        void 당첨_번호에_보너스_번호를_포함하지_않으면_false를_반환한다() {
            assertFalse(lottoResultsEntered.hasBonusNumberInWinningLottoNumbers("4", "1,2,3,5,6,7"));
        }
    }

    @Nested
    @DisplayName("hasDelimiterForParsing 함수는")
    class HasDelimiterForParsingTest {

        @Test
        @DisplayName("빈 문자열인 경우 false를 반환한다")
        void 빈_문자열인_경우_false를_반환한다() {
            assertFalse(lottoResultsEntered.hasDelimiterForParsing(""));
        }
        @Test
        @DisplayName("쉼표 구분자를 포함하면 true를 반환한다")
        void 쉼표_구분자를_포함하면_true를_반환한다() {
            assertTrue(lottoResultsEntered.hasDelimiterForParsing("1,2,4,5,6,7"));
        }
        @Test
        @DisplayName("쉼표 이외의 구분자를 포함하면 false를 반환한다")
        void 쉼표_이외의_구분자를_포함하면_true를_반환한다() {
            assertFalse(lottoResultsEntered.hasDelimiterForParsing("1=2]4+5!6)7"));
        }
    }

    @Nested
    @DisplayName("validateWinningLottoNumbers 함수는")
    class ValidateWinningLottoNumbersTest {
        @Test
        @DisplayName("쉼표 구분자를 포함하지 않으면 에러를 발생한다")
        void 쉼표_구분자를_포함하지_않으면_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoResultsEntered.validateWinningLottoNumbers("123456");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.HAS_DELIMITER_OF_COMMA.getLabel());
        }

        @Test
        @DisplayName("당첨 번호가 정수가 아니면 에러를 발생한다")
        void 당첨_번호가_정수가_아니면_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoResultsEntered.validateWinningLottoNumbers("a,b,c,d,e,f");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_NUMERIC_TYPE.getLabel());
        }

        @Test
        @DisplayName("당첨 번호가 로또 번호가 아니면 에러를 발생한다")
        void 당첨_번호가_로또_번호가_아니면_에러를_발생한다() {
            assertThatThrownBy(() -> {lottoResultsEntered.validateWinningLottoNumbers("-1,4,99,100,12,2");})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(LottoErrorMessage.ONLY_LOTTO_NUMBER.getLabel());
        }

        @Test
        @DisplayName("당첨 번호가 유효하면 잘 수행한다")
        void 당첨_번호가_유효하면_잘_수행한다() {
            assertThatCode(() -> {lottoResultsEntered.validateWinningLottoNumbers("1,2,5,7,44,45");})
                    .doesNotThrowAnyException();
        }
    }
}
