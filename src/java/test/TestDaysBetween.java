/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author yisus
 */
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.ChronoUnit.DAYS;
public class TestDaysBetween {
    public static void main(String[] args) {
 
        long days = DAYS.between(LocalDate.of(2021, Month.OCTOBER, 15), LocalDate.now());
        System.out.println(days);
    }   
}