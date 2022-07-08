package ru.otus.spring.dao;

import ru.otus.spring.domain.TestItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestBoxDaoSimple implements TestBoxDao {

    List<TestItem> testItemList;

    public TestBoxDaoSimple() { }

    public List<TestItem> getTestItemList( String fileName ) {
        // По понятным причинам файлы открываются не в конструкторе
        testItemList = new ArrayList<>();

        InputStream inputStream = null;
        inputStream = getClass().getResourceAsStream( fileName );

        if( inputStream != null ) {
            BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );
            String line = null;
            try {
                while ( (line = reader.readLine()) != null ) {
                    String[] str = line.split( "," );
                    /*  str[0] - номер правильного варианта ответа
                        str[1] - вопрос
                        остальные элементы str - варианты ответа
                    */
                    List<String> variants = new ArrayList<>();
                    for( int i = 2; i < str.length; i++ ) {
                        variants.add( str[i] );
                    }
                    TestItem testItem = new TestItem( str[1], variants, Integer.parseInt( str[0] ) );
                    testItemList.add( testItem );
                }
                reader.close();
            } catch( IOException e ) {
            }
        }
        return testItemList;
    }

}
