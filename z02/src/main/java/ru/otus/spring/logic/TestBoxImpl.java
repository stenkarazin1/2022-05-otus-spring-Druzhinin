package ru.otus.spring.logic;

import ru.otus.spring.dao.TestBoxDao;
import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TestBoxImpl implements TestBox {

    private final TestBoxDao testBoxDao;
    // Список заданных вопросов и список вопросов, на которые ответили - не одно и то же. Так я решил
    private List<TestItem> testItemList;
    private List<AnsweredTestItem> answeredTestItemList;
    private String fileName; // = "/test.csv";

    public TestBoxImpl( String fileName, TestBoxDao dao ) {
        this.fileName = fileName;
        this.testBoxDao = dao;
        testItemList = new LinkedList<TestItem>();
        answeredTestItemList= new LinkedList<AnsweredTestItem>();
    }

    public List<TestItem> getTestItemList( int requiredTestItemListSize ) {
        testItemList.clear();
        testItemList = testBoxDao.getTestItemList( fileName );
        // Потому что набор вопросов должен определяться бизнес-логикой
        Collections.shuffle( testItemList );
        int testItemListSize = testItemList.size();
        for( int i=requiredTestItemListSize; i<testItemListSize; i++ ) { testItemList.remove( i ); }
        return testItemList;
    }

    public void setAnswer( TestItem testItem, int response ) {
        // Здесь можно добавить проверку на предмет подлинности testItem
        AnsweredTestItem answeredTestItem = new AnsweredTestItem( testItem, response );
        answeredTestItemList.add( answeredTestItem );
    }

    public int getAnsweredTestItemCount() {
        return answeredTestItemList.size();
    }

    public AnsweredTestItem getAnsweredTestItem( int i ) {
        return answeredTestItemList.get( i );
    }

    public int getRightAnsweredTestItemCount() {
        int count = 0;
        for ( AnsweredTestItem answeredTestItem : answeredTestItemList ) {
            if ( answeredTestItem.getRightAnswer() == answeredTestItem.getUserAnswer() ) count++;
        }
        return count;
    }

}
