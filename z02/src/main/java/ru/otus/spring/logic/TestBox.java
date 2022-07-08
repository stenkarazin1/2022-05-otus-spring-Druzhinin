package ru.otus.spring.logic;

import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;

import java.util.List;

public interface TestBox {

    List<TestItem> getTestItemList(int N);

    void setAnswer(TestItem testItem, int response);

    int getAnsweredTestItemCount();

    AnsweredTestItem getAnsweredTestItem(int i);

    int getRightAnsweredTestItemCount();

}
