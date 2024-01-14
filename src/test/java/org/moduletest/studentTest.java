package org.moduletest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.management.ConstructorParameters;
import java.util.List;

 /*NSA точка расширения функциолальности теста
 если базовой функционалности нехватает рождаем свой класс
 */
@ExtendWith(EducationExtantion.class)

class studentTest {
     /*создаем тест заглушку Mockito*/
    @Test
    public void testRatingMokito(){
        Student stud= new Student("Vasa");
        System.out.println("студент до:"+stud.toString());

        stud.addMark(4);
        System.out.println("студент после:"+stud.toString());
        StudentRepo repo= Mockito.mock(StudentRepoMock.class);  //создаем обертку для нашего класса наследника интерфейса репозхитория
        Mockito.when(repo.getRatingForGradeSum(Mockito.any())).thenReturn(10);//.добавляем логику свою в запроксеванный объект

        stud.setRepo(repo); //создаем тест заглушку
        Assertions.assertEquals(stud.rating(),10);
    }

     @Test
     public void testRating(){
         Student stud= new Student("Vasa");
         System.out.println("студент до:"+stud.toString());

         stud.addMark(4);
         System.out.println("студент после:"+stud.toString());


         stud.setRepo(new StudentRepoMock()); //создаем тест заглушку
         Assertions.assertEquals(stud.rating(),10);
     }
    /*NSA выполяем перед каждым тестом выполение теста должно быть автономным
    поэтому для каждого тест выполяем настроку ее параметров и входящих данных
    * */
    @BeforeEach
    public void crateFile()
    {
        System.out.println("Создаем файл");

    }
    //NSA выполяем после каждого теста
    @AfterEach
    public void deleteFile()
    {
        System.out.println("Удаляем файл");
    }

    /* анотация для повторяющегося теста параметры последовательные данные*/
   //поскольку проеряем на корретные оценки то значения должны быть 2,3,4,5
    @RepeatedTest(value = 4,name="Проверка класса на корректные оценки")
     public void marksInRange(RepetitionInfo repetitionInfo)
    {


        Student stud= new Student("Vasa");
        System.out.println("студент до:"+stud.toString());
        int num = repetitionInfo.getCurrentRepetition()+1;
        stud.addMark(num);
        System.out.println("студент после:"+stud.toString());
        Assertions.assertEquals(stud.getMarks().get(0),num);
     }

     /*в качестве источника данных делаем класс MarksGenerator*/
    @ParameterizedTest(name = "Проверка класса на НЕ корректные оценки")
    @MethodSource("org.moduletest.MarksGenerator#ints")
        public void marksNotInRange(int x)
    {
        System.out.println("проверяем значение оценки:"+x);
        Student stud= new Student("Vasa");
        System.out.println("студент до:"+stud.toString());
        Assertions.assertThrows(IllegalArgumentException.class, ()->stud.addMark(x));
        System.out.println("студент после:"+stud.toString());

    }

}

class EducationExtantion implements BeforeEachCallback
{
    @Override
    public void beforeEach(ExtensionContext context) throws Exception
    {
        System.out.println();
        System.out.println("Провкрка расширения test Extantion");
    }
}
