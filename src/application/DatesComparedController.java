/*	Date Comparison GUI Application
 * 	
 * 	Asks the user for two valid dates, then displays the full dates, the length of time 
 * 	between those two dates in various units of time. These units being days, weeks, 
 * 	months, years, hours, minutes & seconds.
 * 
 * 	Code Written By Isaiah Wallace
 * 
 * 	Completed Version Date: 2025-09-02
 * 	
 * 	Repository Link : https://github.com/Isaiah1505/Dates-Compared-GUI
 * 
 * 	Built using JavaFX Framework Version : 24.0.2
 * 
 * 	Java Version : 22.0.1
 * 
 * */

package application;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DatesComparedController {

    @FXML
    private TextField dateDiff, daysBetween, yearsBetween, monthsBetween, weeksBetween, hoursBetween, minsBetween, secBetween;
    
    @FXML
    private Text fromDateFull, toDateFull, errorTxt;
        
    @FXML
    private DatePicker fromCalDate, toCalDate;
    
    @FXML
    private CheckBox endDateCheck;
    
    
    public void counterBtn(ActionEvent e) {
    	// Java.time API doc https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    	// retrieves values from DatePickers & turns into LocalDate Objs
    	LocalDate fromDate = fromCalDate.getValue();
    	LocalDate toDate = toCalDate.getValue();
    	
    	//Code Functionality
    	
    	//File Hierarchy
    	// Make a dedicated folders, like Controllers, Views, Resources
    	// try catch block for exceptions, main sections of code (mainly NullPointerExceptions)
    	try {
    		if(fromDate.isBefore(toDate)) {
    			
    			// titleCase Conversion for presentation of the full dates
    			String fromWeekDay = titleCase(String.valueOf(fromDate.getDayOfWeek()));
    			String fromMonth = titleCase(String.valueOf(fromDate.getMonth()));
    			String toWeekDay = titleCase(String.valueOf(toDate.getDayOfWeek()));
    			String toMonth = titleCase(String.valueOf(toDate.getMonth()));
    			
    			String fromDateFullStr = String.valueOf(fromWeekDay+", "+fromMonth+" "+fromDate.getDayOfMonth()+" "+fromDate.getYear());
    			String toDateFullStr = String.valueOf(toWeekDay+", "+toMonth+" "+toDate.getDayOfMonth()+" "+toDate.getYear());

    			fromDateFull.setText(fromDateFullStr);
    			toDateFull.setText(toDateFullStr);
    			// date difference in years, months and days
    			Period periodDiff = fromDate.until(toDate);
    			
    			// calls endDate function to see if user wants the end date included
    			periodDiff = periodDiff.plusDays(endDateInclusion());
    			// calculation for different units of time
    			int totalDays = (periodDiff.getYears() * 365) + (periodDiff.getMonths() * 30 + periodDiff.getDays());
    			double totalYears = (double) totalDays / 365;
    			double totalMonths = (double) totalDays / 30;
    			double totalWeeks = (double) totalDays / 7;
    			int totalHours = totalDays * 24;
    			int totalMins = totalHours * 60;
    			int totalSec = totalMins * 60;
    			
    			String periodDiffStr = periodDiff.getYears()+" Years, "+periodDiff.getMonths()+" Months & "+periodDiff.getDays()+" Days";
    			// presenting all units of time to user
    			dateDiff.setText(periodDiffStr);
    			yearsBetween.setText(removeExxZeros(totalYears));
    			monthsBetween.setText(removeExxZeros(totalMonths));
    			weeksBetween.setText(removeExxZeros(totalWeeks));
    			daysBetween.setText(String.format("%,d",totalDays));
    			hoursBetween.setText(String.format("%,d",totalHours));
    			minsBetween.setText(String.format("%,d",totalMins));
    			secBetween.setText(String.format("%,d",totalSec));
    			errorTxt.setText("");
    			
    			
    		}
    		else if(fromDate.isAfter(toDate) || fromDate.isEqual(toDate) ) {
    			errorTxt.setText("Given date range is not valid. Either equal to or before from date!");
    		}
    	}
    	// catches any exception, then checks if it's for a null value, if not, generic message is displayed
    	catch(Exception error) {
    		if(error.getClass().equals(NullPointerException.class)) {
    			errorTxt.setText("One or both given dates are null/blank. Enter or select valid dates!");
    		}
    		else {
    			errorTxt.setText("An Error Has Occurred.");
    		}
			System.out.println(error.getMessage());

    	}
    	
    }
    
    // if check box is selected, end date is included in calculation
    public int endDateInclusion() {
    	int endDate = 0;
    	if(endDateCheck.isSelected()) {
    		endDate = 1;
    	}else {
    		endDate = 0;
    	}
    	System.out.println(endDate);
    	return endDate;
    }
    // removes non-significant zeros (ie .000, .200,.020) and adds commas where needed
    public String removeExxZeros(double toRound) {
    	Pattern zeroPattern1 = Pattern.compile(".[1-9]00"), zeroPattern2 = Pattern.compile(".[0-9][1-9]0");
    	String formatDouble = String.format("%,.3f", toRound);
    	
    	for(int i = 0; i < formatDouble.length(); i++) {
    		if(formatDouble.charAt(i) == '.'&& formatDouble.contains(".000")) {
    			formatDouble = formatDouble.substring(0, i);
    			break;
    			
    		}else if(formatDouble.charAt(i) == '.' && zeroPattern1.matcher(formatDouble).find()) {
    			formatDouble = formatDouble.substring(0, i+2);
    			break;
    			
    		}else if(formatDouble.charAt(i) == '.' && zeroPattern2.matcher(formatDouble).find()) {
    			formatDouble = formatDouble.substring(0, i+3);
    			break;
    				
    		}
    	}
    	
    	System.out.println(formatDouble);
    	return formatDouble;
    }
    
    
    
    // resets all fields to defaultS
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
    
    // shows user a quick example of application in use & for quick testing & debugging
    public void exampleDate(ActionEvent e) {
    	fromCalDate.setValue(LocalDate.parse("2021-05-11"));
    	toCalDate.setValue(LocalDate.parse("2025-08-15"));
    	errorTxt.setText("");
    	counterBtn(e);
    }
    // capitalizes first letter of a given string
    public String titleCase(String toNormalise) {
    	String firstChar = String.valueOf(toNormalise.charAt(0)).toUpperCase();
    	toNormalise = firstChar + toNormalise.substring(1).toLowerCase();
    	return toNormalise;
    }
	
}
