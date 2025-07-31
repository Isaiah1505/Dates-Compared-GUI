package application;

import java.time.LocalDate;
import java.time.Period;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DayCounterController {

    @FXML
    private TextField dateDiff, daysBetween, yearsBetween, monthsBetween, weeksBetween, hoursBetween, minsBetween, secBetween;
    
    @FXML
    private Text fromDateFull, toDateFull, errorTxt;
    
    @FXML
    private DatePicker fromCalDate, toCalDate;
    
    public void counterBtn(ActionEvent e) {
    	// Use LocalDate.minus methods to get days between
    	// Java.time API doc https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    	
    	System.out.println("New DatePicker from Obj: "+fromCalDate.getValue());
    	System.out.println("New DatePicker to Obj: "+toCalDate.getValue());

    	LocalDate fromDate = fromCalDate.getValue();
    	LocalDate toDate = toCalDate.getValue();
    	
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
    	
    	//Code Functionality
    	// dateDifference doesn't include end date and is accurate
    	// days conversion isn't correct and other units based off of days conversion
    	// no error handling when inputs are empty or not correct format (ie not numbers, wrong ranges)
    	// Change inputs to 1 string per date to shrink code and simplify error handling for wrong inputs
    	// Format date difference time units (commas for big numbers & truncate or round decimals)
    	//GUI Style & Layout
    	// Add CSS styling to page to improve appeal
    	// Change element layouts on page for a better look
    	//File Hierarchy
    	// Make a dedicated folders, like Controllers, Views, Resources
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

    		
    	}else if(fromDate.isAfter(toDate) || fromDate.isEqual(toDate) ) {
    		errorTxt.setText("Not a valid range. Equal to or before from date");
    	}
    	
    }

    public void clearBtn(ActionEvent e) {
    	fromCalDate.setValue(null);
    	toCalDate.setValue(null);
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
    	fromCalDate.setValue(LocalDate.parse("2021-05-11"));
    	toCalDate.setValue(LocalDate.parse("2025-08-15"));
    	errorTxt.setText("");
    	counterBtn(e);
    }
    
    public String titleCase(String toNormalise) {
    	String firstChar = String.valueOf(toNormalise.charAt(0)).toUpperCase();
    	toNormalise = firstChar + toNormalise.substring(1).toLowerCase();
    	return toNormalise;
    }
	
}
