package br.ufmg.engsoft.reprova.model.generator;

import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.model.Questionnaire;

public class EstimatedTimeCalculator extends ChainQuestionnaireGeneration{

  public Questionnaire generate(Questionnaire questionnaire){
    int totalEstimatedTime = 0;
    
    for (Question question : questionnaire.questions){
      totalEstimatedTime += question.estimatedTime;
    }

    questionnaire.totalEstimatedTime = totalEstimatedTime;
    return handleGeneration(questionnaire);
  }
}