/*
 * This is file of the project java-lotto-precourse
 * Copyright (c) 2025 logicallaw
 * Author: Junho Kim
 * Latest Updated Date: 2025-11-03
 */
package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Nested
    @DisplayName("로또 당첨 경우에 대하여")
    class AllLottoCases {
        @Test
        @DisplayName("당첨되지 않아도 잘 수행한다")
        void 당첨되지_않아도_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("2000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "2개를 구매했습니다.",
                                "3개 일치 (5,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 0.0%입니다."
                        );
                    },
                    List.of(10, 11, 12, 13, 14, 15),
                    List.of(16, 17, 18, 19, 20, 21)
            );
        }

        @Test
        @DisplayName("3개의 숫자가 일치하여 잘 수행한다")
        void _3개의_숫자가_일치하여_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("1000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "1개를 구매했습니다.",
                                "[1, 2, 3, 10, 11, 12]",
                                "3개 일치 (5,000원) - 1개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 500.0%입니다."
                        );
                    },
                    List.of(1, 2, 3, 10, 11, 12)
            );
        }

        @Test
        @DisplayName("4개의 숫자가 일치하여 잘 수행한다")
        void _4개의_숫자가_일치하여_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("1000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "1개를 구매했습니다.",
                                "[1, 2, 3, 4, 10, 11]",
                                "3개 일치 (5,000원) - 0개",
                                "4개 일치 (50,000원) - 1개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 5,000.0%입니다."
                        );
                    },
                    List.of(1, 2, 3, 4, 10, 11)
            );
        }

        @Test
        @DisplayName("5개의 숫자가 일치하여 잘 수행한다")
        void _5개의_숫자가_일치하여_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("1000", "1,2,3,4,5,6", "7");

                        assertThat(output()).contains(
                                "1개를 구매했습니다.",
                                "[1, 2, 3, 4, 5, 45]",
                                "3개 일치 (5,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 1개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 150,000.0%입니다."
                        );
                    },
                    List.of(1, 2, 3, 4, 5, 45)
            );
        }

        @Test
        @DisplayName("5개의 숫자와 보너스 번호가 일치하여 잘 수행한다")
        void _5개의_숫자와_보너스_번호가_일치하여_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("1000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "1개를 구매했습니다.",
                                "3개 일치 (5,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                                "6개 일치 (2,000,000,000원) - 0개",
                                "총 수익률은 3,000,000.0%입니다."
                        );
                    },
                    List.of(1, 2, 3, 4, 5, 7)
            );
        }

        @Test
        @DisplayName("6개의 숫자가 모두 일치하여 잘 수행한다")
        void _6개의_숫자가_모두_일치하여_잘_수행한다() {
            assertRandomUniqueNumbersInRangeTest(
                    () -> {
                        run("1000", "1,2,3,4,5,6", "7");
                        assertThat(output()).contains(
                                "1개를 구매했습니다.",
                                "3개 일치 (5,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "6개 일치 (2,000,000,000원) - 1개",
                                "총 수익률은 200,000,000.0%입니다."
                        );
                    },
                    List.of(1, 2, 3, 4, 5, 6)
            );
        }
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
