package junit1;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * - 테스트 대상 클래스 - 타겟 클래스
 * - @Test 어노테이션이 붙어있지 않은 메서드는 테스트 대상에서 제외
 */
class ScoreCollectionTest {

    @Test
    public void test() {
    }

    @Test
    public void answersArtihmeticMeanOfTwoNumbers() {
        // given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // when
        int actualResult = collection.arithmeticMean();

        // then
        assertThat(actualResult).isEqualTo(6);
    }

    @Test
    public void answersArithmeticMeanOfTwoNumbersFail() {
        // given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 4);
        
        // when
        int actualResult = collection.arithmeticMean();

        // then
        assertThat(actualResult).isNotEqualTo(5);
        assertThat(actualResult).isEqualTo(4);
    }
}