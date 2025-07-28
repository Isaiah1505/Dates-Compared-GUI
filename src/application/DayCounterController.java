package application;

import java.time.LocalDate;
import java.time.Period;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DayCounterController {
	@FXML
    private TextField fromDays, fromMonths, fromYears, toDays, toMonths, toYears;

    @FXML
    private TextField dateDiff, daysBetween, yearsBetween, monthsBetween, weeksBetween, hoursBetween, minsBetween, secBetween;
    
    @FXML
    private Text fromDateFull, toDateFull, errorTxt;
    
    public void counterBtn(ActionEvent e) {
    	// Use LocalDate.minus methods to get days between
    	// Java.time API doc https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    	String fromDateStr = fromYears.getText()+"-"+fromMonths.getText()+"-"+fromDays.getText();
    	String toDateStr = toYears.getText()+"-"+toMonths.getText()+"-"+toDays.getText();

    	LocalDate fromDate = LocalDate.parse(fromDateStr);
    	LocalDate toDate = LocalDate.parse(toDateStr);
    	
    	System.out.println(fromDate);
    	System.out.println(toDate);
    	
    	String fromWeekDay = titleCase(String.valueOf(fromDate.getDayOfWeek()));
    	String fromMonth = titleCase(String.valueOf(fromDate.getMonth()));
    	String toWeekDay = titleCase(String.valueOf(toDate.getDayOfWeek()));
    	String toMonth = titleCase(String.valueOf(toDate.getMonth()));
    	
    	String fromDateFullStr = String.valueOf(fromWeekDay+", "+fromMonth+" "+fromDate.getDayOfMonth()+" "+fromDate.getYear());
    	String toDateFullStr = String.valueOf(toWeekDay+", "+toMonth+" "+toDate.getDayOfMonth()+" "+toDate.getYear());

    	fromDateFull.setText(fromDateFullStr);
    	toDateFull.setText(toDateFullStr);
    	
    	// dateDifference doesn't include end date and is accurate
    	// days conversion isn't correct
    	// no error handling when inputs are empty or not correct format (ie not numbers, wrong ranges)
    	if(fromDate.isBefore(toDate)) {
    		
    		Period periodDiff = fromDate.until(toDate);
    		LocalDate dateDifference = LocalDate.of(periodDiff.getYears(), periodDiff.getMonths(), periodDiff.getDays());
    		
    		System.out.println(dateDifference);
    		System.out.println(dateDifference.getDayOfYear());
    		System.out.println(dateDifference.getYear());
    		
    		int totalDays = (dateDifference.getYear() * 365) + (dateDifference.getMonthValue() * 30 + dateDifference.getDayOfMonth());
    		double totalYears = (double) totalDays / 365;
    		double totalMonths = (double) totalDays / 30;
    		double totalWeeks = (double) totalDays / 7;
    		int totalHours = totalDays * 24;
    		int totalMins = totalHours * 60;
    		int totalSec = totalMins * 60;
    		
    		String periodDiffStr = periodDiff.getYears()+" Years, "+periodDiff.getMonths()+" Months & "+periodDiff.getDays()+" Days";
    		
    		dateDiff.setText(periodDiffStr);
    		yearsBetween.setText(String.valueOf(totalYears));
    		monthsBetween.setText(String.valueOf(totalMonths));
    		weeksBetween.setText(String.valueOf(totalWeeks));
    		daysBetween.setText(String.valueOf(totalDays));
    		hoursBetween.setText(String.valueOf(totalHours));
    		minsBetween.setText(String.valueOf(totalMins));
    		secBetween.setText(String.valueOf(totalSec));
    		errorTxt.setText("");

    		
    	}else if(fromDate.isAfter(toDate) || fromDate.isEqual(toDate)) {
    		errorTxt.setText("Not a valid range. Equal to or before from date");
    	}
    	
    }

    public void clearBtn(ActionEvent e) {
    	fromYears.setText("");
    	fromMonths.setText("");
    	fromDays.setText("");
    	toYears.setText("");
    	toMonths.setText("");
    	toDays.setText("");
    	fromDateFull.setText("dd/mm/yyyy");
    	toDateFull.setText("dd/mm/yyyy");
    	dateDiff.setText("");
		yearsBetween.setText("");
		monthsBetween.setText("");
		weeksBetween.setText("");
		daysBetween.setText("");
		hoursBetween.setText("");
		minsBetween.setText("");
		secBetween.setText("");
		errorTxt.setText("");
    }
    
    // mainly for quick testing & debugging
    public void exampleDate(ActionEvent e) {
    	fromYears.setText("2021");
    	fromMonths.setText("05");
    	fromDays.setText("11");
    	toYears.setText("2025");
    	toMonths.setText("08");
    	toDays.setText("15");
    	errorTxt.setText("");
    	counterBtn(e);
    }
    
    public String titleCase(String toNormalise) {
    	String firstChar = String.valueOf(toNormalise.charAt(0)).toUpperCase();
    	toNormalise = firstChar + toNormalise.substring(1).toLowerCase();
    	return toNormalise;
    }
	
}
