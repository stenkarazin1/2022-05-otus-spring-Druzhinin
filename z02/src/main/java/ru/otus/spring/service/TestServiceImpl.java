package ru.otus.spring.service;

import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.logic.TestBox;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;

public class TestServiceImpl implements TestService {

    private final TestBox testBox;

    public TestServiceImpl( TestBox box ) {
        this.testBox = box;
    }
    @Value("${configfile}")
    String path;

    public void start() {
        Scanner in = new Scanner( System.in );
        PrintStream out = new PrintStream( System.out );
        List<TestItem> testItemList = testBox.getTestItemList( 5 );

        out.println( "Введите ФИО:" );
        String fio = in.nextLine();
        // Яйцо пасхальное обыкновенное :- )
        fio = ( fio.equals( "" ) ? "Христо Стоичков" : fio );

        int testItemListSize = testItemList.size();
        TestItem testItem;
        for( int j=0; j<testItemListSize; j++ ) {
            testItem = testItemList.get( j );
            out.println( "\n----------" );
            out.println( testItem.getQuestion() ); // + "\n   " + testItem.getRightAnswer() + "\n   " + testItem.getRightAnswer() );
            List<String> variants = testItem.getVariants();
            for ( int i = 0; i < variants.size(); i++ ) {
                out.printf( "   %2d ) %s\n", ( i + 1 ), variants.get( i ) );
            }
            out.println( "Введите номер, соответствующий выбранному Вами ответу:" );
            // Проверка ввода не производится. Укажите один из предложенных вариантов ответа
            int response = in.nextInt();
            testBox.setAnswer( testItem, response );
        }

        out.println(  "\n==========\nТестуемый: " + fio  );
        out.println(  "Результат: Верно " + testBox.getRightAnsweredTestItemCount() + " из " + testItemListSize );
        for(  int i = 0; i < testBox.getAnsweredTestItemCount(); i++  ) {
            AnsweredTestItem answeredTestItem = testBox.getAnsweredTestItem( i );
            out.println( "\n----------\n" + answeredTestItem.getQuestion() );
            out.println( "   Вы ответили:      " + answeredTestItem.getVariant( answeredTestItem.getUserAnswer() ) );
            out.println( "   Правильный ответ: " + answeredTestItem.getVariant( answeredTestItem.getRightAnswer() ) );
        }
    }

}
