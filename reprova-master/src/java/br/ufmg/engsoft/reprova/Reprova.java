package br.ufmg.engsoft.reprova;

import br.ufmg.engsoft.reprova.database.AnswersDAO;
import br.ufmg.engsoft.reprova.database.Mongo;
import br.ufmg.engsoft.reprova.database.QuestionsDAO;
import br.ufmg.engsoft.reprova.database.QuestionnairesDAO;
import br.ufmg.engsoft.reprova.routes.Setup;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Environments;


public class Reprova {
  public static void main(String[] args) {
    var json = new Json();
    
    Mongo db;

    try {
    	db = new Mongo("reprova");
    } catch(Exception e) {
    	System.out.println(e);
    	return;
    }

    var questionsDAO = new QuestionsDAO(db, json);    

    Setup.routes(json, questionsDAO);
    
    Environments envs = Environments.getInstance();
    
    if (envs.getEnableAnswers()) {
        var answersDAO = new AnswersDAO(db, json);
        Setup.answerRoutes(json, answersDAO);
    }

    if (envs.getEnableQuestionnaires()) {
        var questionnairesDAO = new QuestionnairesDAO(db, json);
        Setup.questionnaireRoutes(json, questionnairesDAO, questionsDAO);
    }
  }
}
