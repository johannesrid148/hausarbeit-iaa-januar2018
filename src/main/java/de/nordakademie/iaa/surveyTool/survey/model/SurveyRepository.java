package de.nordakademie.iaa.surveyTool.survey.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SurveyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Survey survey) {
        entityManager.persist(survey);
    }

    public List<Survey> findAll() {
        return entityManager.createQuery("SELECT r FROM Survey r", Survey.class).getResultList();
    }

    public Survey findOne(final long surveyId) {
        return entityManager.find(Survey.class, surveyId);
    }

    public Survey update(Survey survey) {
       entityManager.merge(survey);
        return survey;
    }


    public void delete(Survey survey) {
        entityManager.remove(survey);
    }

    public Survey endSurvey(Survey survey) {
        survey.setActive(false);
        return survey;
    }
}
