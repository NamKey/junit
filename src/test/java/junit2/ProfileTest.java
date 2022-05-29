package junit2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    public void create() {
        profile = new Profile("Bull Hockey, Inc");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    /**
     * 이런식으로 테스트를 작성하게 되면 우리가 원하는 profile.matches라는 method에 대한 test 조건이 많이 생긴다.
     * - 근데 모든 테스트를 이렇게 관리하면 힘듬
     * 1. 공통 코드를 뽑아내자
     */
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got bonuses?");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);

        criteria.add(criterion);

        boolean matches = profile.matches(criteria);

        assertThat(matches).isFalse();
    }

    @Test
    public void matchAnswersTrueForANyDontCareCriteria() {
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got Milk?");
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);

        criteria.add(criterion);

        boolean matches = profile.matches(criteria);

        assertThat(matches).isTrue();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMetRefactor() {
        profile.add(new Answer(question, Bool.FALSE));
        Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch);

        criteria.add(criterion);

        boolean matches = profile.matches(criteria);

        assertThat(matches).isFalse();
    }

    @Test
    public void matchAnswersTrueForANyDontCareCriteriaRefactor() {
        profile.add(new Answer(question, Bool.FALSE));
        Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);

        criteria.add(criterion);

        boolean matches = profile.matches(criteria);

        assertThat(matches).isTrue();
    }
}