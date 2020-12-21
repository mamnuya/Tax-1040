/**
 * This is a program that lets you input your answers to the 1040 tax form.
 * After collection of all answers, answers are printed to you.
 * This helps organize and visualize information prior to completing official paperwork
 * You have the option to edit any answers until no edits are desired.
 * @Author Mamnuya Rinki
 */

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.HashMap;


public class Tax1040{

  private ArrayList<String> questionBank; //List of question numbers in 1040 form
  private HashMap <String,Long> hash; //Hashmap connecting question numbers and the user's inputted answers
  private ArrayList<InfoBank> questionAns; //List of InfoBank objects storing questions and data (see InfoBank class below)

  /**
   * Constructor for Tax1040 class
   * Adds the question numbers into questionBank ArrayList
   * Then calls collectInfo() method,
   * and prints out resulting hashmap with question numbers and answers
   * Prompts the finalTouch() method
   */
  public Tax1040(){ //empty constructor
    System.out.println("Hello! I can help you visualize and edit your information for filing a 1040 tax form. Please wait as I set up for collecting your information.");
    //System.out.println("Remember to fill in your personal information, found above question #1");
    questionBank=new ArrayList<String>();
    questionBank.add("#1");
    questionBank.add("#2a"); // if 2a is not "" then warn that sched B may be required
    questionBank.add("#2b");
    questionBank.add("#3a"); // if 2a is not "" then warn that sched B may be required
    questionBank.add("#3b");
    questionBank.add("#4a");
    questionBank.add("#4b");
    questionBank.add("#5a");
    questionBank.add("#5b");
    questionBank.add("#6a");
    questionBank.add("#6b");
    for (int i=7; i<=9; i++){
      questionBank.add("#"+Integer.toString(i));
    }
    questionBank.add("#10a");
    questionBank.add("#10b");
    questionBank.add("#10c");
    for (int i=11; i<=24; i++){
      questionBank.add("#"+Integer.toString(i));
    }
    questionBank.add("#25a");
    questionBank.add("#25b");
    questionBank.add("#25c");
    questionBank.add("#25d");
    for (int i=26; i<=34; i++){
      questionBank.add("#"+Integer.toString(i));
    }
    questionBank.add("#35a");
    questionBank.add("#35b");
    questionBank.add("#35c");
    questionBank.add("#35d");
    questionBank.add("#36");
    questionBank.add("#37");
    questionBank.add("#38");
    System.out.println(collectInfo().entrySet());
    System.out.println("-");
    System.out.println();
    finalTouch();

  }


  /**
   * InfoBank stores the question number and the corresponding answer
   */
  private class InfoBank{
    private String question;
    private Long data; //string or Long..?

    public InfoBank(String q, Long d){
      this.question=q;
      this.data=d;
    }
  }

  /**
   * Method that will ask user for input to questions then place into hashmap
   * @return Hashmap of question matched to answer
   */
  public Map collectInfo(){
    hash=new HashMap<String,Long>();
    questionAns=new ArrayList<InfoBank>();
    Scanner scan= new Scanner(System.in);
    System.out.println("-");
    System.out.println("Thank you for your patience. Let's begin!");
    System.out.println("Please enter numerical values. If you have no value to enter, simply press 'Enter' on your keyboard.");
    System.out.println("-");
    System.out.println();
    System.out.println("Enter total wages/salaries/tips from your W2 form :  ");
    questionAns.add(new InfoBank(questionBank.get(0), scan.nextLong()));
    System.out.println("Enter your tax-exempt interest (income not subject to income tax) :  ");
    questionAns.add(new InfoBank(questionBank.get(1), scan.nextLong()));
    System.out.println("Enter your taxable interest (income from investements subject income tax) :  ");
    questionAns.add(new InfoBank(questionBank.get(2), scan.nextLong()));
    System.out.println("Enter your taxable interest (income from investements subject income tax) :  ");
    questionAns.add(new InfoBank(questionBank.get(3), scan.nextLong()));
    System.out.println("Enter your qualified dividends :  ");
    questionAns.add(new InfoBank(questionBank.get(4), scan.nextLong()));
    System.out.println("Enter your ordinary dividends :  ");
    questionAns.add(new InfoBank(questionBank.get(5), scan.nextLong()));
    System.out.println("Enter your IRA distributions :  ");
    questionAns.add(new InfoBank(questionBank.get(6), scan.nextLong()));
    System.out.println("Enter your IRA distributions' taxable amount :  ");
    questionAns.add(new InfoBank(questionBank.get(7), scan.nextLong()));
    System.out.println("Enter your pensions and annuities :  ");
    questionAns.add(new InfoBank(questionBank.get(8), scan.nextLong()));
    System.out.println("Enter your pensions and annuities' taxable amount :  ");
    questionAns.add(new InfoBank(questionBank.get(9), scan.nextLong()));
    System.out.println("Enter your social security benefits :  ");
    questionAns.add(new InfoBank(questionBank.get(10), scan.nextLong()));
    System.out.println("Enter your social security benefits' taxable amount :  ");
    questionAns.add(new InfoBank(questionBank.get(11), scan.nextLong()));
    System.out.println("Enter your capital gain/loss  :  ");
    questionAns.add(new InfoBank(questionBank.get(12), scan.nextLong())); //if 7 is "" warn to check the 7 checkbox on form. if not "" then warn to attach schedule default:
    System.out.println("Enter any other income from your Schedule 1 Form on Line 22 : ");
    questionAns.add(new InfoBank(questionBank.get(13), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(14), getData("#1")+getData("#2b")+getData("#3b")+getData("#4b")+getData("#5b")+getData("#6b")+getData("#7")+getData("#8")));
    System.out.println("Enter any adjustment to income from your Schedule 1 Form on Line 22 : ");
    questionAns.add(new InfoBank(questionBank.get(15), scan.nextLong()));
    System.out.println("Enter any charitable contributions in your adjustment to income from your Schedule 1 Form on Line 22 : ");
    questionAns.add(new InfoBank(questionBank.get(16), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(17), getData("#10a")+getData("#10b")));
    questionAns.add(new InfoBank(questionBank.get(18), getData("#10c")-getData("#9")));
    System.out.println("Enter your standard deduction or itemized deductions from your Schedule A : ");
    questionAns.add(new InfoBank(questionBank.get(19), scan.nextLong()));
    System.out.println("Enter your qualified business income deduction : ");
    questionAns.add(new InfoBank(questionBank.get(20), scan.nextLong())); //if 12 is not "" warn to attach form "8995" or "8995-A"
    questionAns.add(new InfoBank(questionBank.get(21), getData("#12")+getData("#13")));
    if (getData("#11")-getData("#14")  >0){
      questionAns.add(new InfoBank(questionBank.get(22), getData("#11")-getData("#14")));
    }
    else{
      questionAns.add(new InfoBank(questionBank.get(22), Long.valueOf(0)));
    }
    System.out.println("Enter any taxes from form 8814, form 4972, or other form :  ");
    questionAns.add(new InfoBank(questionBank.get(23), scan.nextLong())); //if 16 is not "" warn to check a checkbox
    System.out.println("Enter amount from Schedule 2 Line 3 : ");
    questionAns.add(new InfoBank(questionBank.get(24), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(25), getData("#16")+getData("#17")));
    System.out.println("Enter child/dependent tax credits : ");
    questionAns.add(new InfoBank(questionBank.get(26), scan.nextLong()));
    System.out.println("Enter amount from Schedule 3 Line 7 : ");
    questionAns.add(new InfoBank(questionBank.get(27), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(28), getData("#19")+getData("#20")));
    if (getData("#21")-getData("#18") >0){
      questionAns.add(new InfoBank(questionBank.get(29), getData("#21")-getData("#18")));
    }
    else{
      questionAns.add(new InfoBank(questionBank.get(29), Long.valueOf(0)));
    }
    System.out.println("Enter any other taxes (including self-employment tax) from Schedule 2 Line 10 : ");
    questionAns.add(new InfoBank(questionBank.get(30), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(31), getData("#22")+getData("#23")));
    System.out.println("Enter total federal tax withheld from your W2 : ");
    questionAns.add(new InfoBank(questionBank.get(32), scan.nextLong()));
    System.out.println("Enter total federal tax withheld from your 1099 : ");
    questionAns.add(new InfoBank(questionBank.get(33), scan.nextLong()));
    System.out.println("Enter total federal tax withheld from any other forms : ");
    questionAns.add(new InfoBank(questionBank.get(34), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(35), getData("#25a")+getData("#25b")+getData("#25c")));
    System.out.println("Enter estimated tax payments/amounts from your past year's tax return : ");
    questionAns.add(new InfoBank(questionBank.get(36), scan.nextLong()));
    System.out.println("Enter your earned income credit (Also known as EIC) : ");
    questionAns.add(new InfoBank(questionBank.get(37), scan.nextLong()));
    System.out.println("Enter any additional child tax credit : ");
    questionAns.add(new InfoBank(questionBank.get(38), scan.nextLong())); //if 28 is not "" or zero warn to attach form 8812
    System.out.println("Enter total American Opportunity credit from Form 8863 Line 8: ");
    questionAns.add(new InfoBank(questionBank.get(39), scan.nextLong()));
    System.out.println("Enter total recovery rebate credit : ");
    questionAns.add(new InfoBank(questionBank.get(40), scan.nextLong()));
    System.out.println("Enter amount from Schedule 3 Line 13 : ");
    questionAns.add(new InfoBank(questionBank.get(41), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(42), getData("#27")+getData("#28")+getData("#29")+getData("#30")+getData("#31")));
    questionAns.add(new InfoBank(questionBank.get(43), getData("#25d")+getData("#26")+getData("#32")));

    if (getData("#33")>getData("#24")){
      questionAns.add(new InfoBank(questionBank.get(44), getData("#33")-getData("#24")));
    }
    else{
      questionAns.add(new InfoBank(questionBank.get(44), Long.valueOf(0)));
    }
    long overpay=questionAns.get(44).data;
    System.out.println("Enter the amount out of "+ overpay+ " that you want refunded to you (The maximum you can enter is " +overpay + ") : ");
    long q34=scan.nextLong();
    questionAns.add(new InfoBank(questionBank.get(45), q34)); //warn that if form 8888 is attached, user needs to check checkbox at question 35a
    System.out.println("(OPTIONAL: enter 0 to skip) Input a routing number : ");
    questionAns.add(new InfoBank(questionBank.get(46), scan.nextLong())); //warn user to check type of bank account in 35c of 1040 form
    System.out.println("(OPTIONAL: enter 0 to skip) Input an account number : ");
    questionAns.add(new InfoBank(questionBank.get(47), scan.nextLong()));
    System.out.println("Enter an amount out of " + q34 + " that you want added to your next year's estimated tax : ");
    questionAns.add(new InfoBank(questionBank.get(48), scan.nextLong()));
    questionAns.add(new InfoBank(questionBank.get(49), getData("#24")-getData("#33")));
    System.out.println("Enter estimated tax penalty : ");
    questionAns.add(new InfoBank(questionBank.get(50), scan.nextLong()));
    for (int i=0; i<questionAns.size();i++){
      hash.put(questionAns.get(i).question, questionAns.get(i).data);
    }
    return hash;
  }

  /**
   * Finds user's typed answer to a given question number
   * @param  s String representation of question number from 1040 form
   * @return   Answer to question
   */
  public Long getData(String s){
    for (int i=0; i<questionAns.size();i++){
      if (questionAns.get(i).question.equals(s)){
        return questionAns.get(i).data;
      }
    }
    return null;
  }

  /**
   * Recursive method
   * Checks if user is satisfied with their hashmap of answers
   * Once satisfied, final messages to consider based on their tax answers are printed
   * Prompts edit() method if user is unsatisfied
   */
  public void finalTouch(){
    Scanner scan= new Scanner(System.in);
    System.out.println("Would you like to edit any of your answers? enter Y or N. ");
    String ans=scan.nextLine();
    if (ans.equals("y")||ans.equals("Y")){
      System.out.println("Okay! Please type the question number (include any letters in lowercase ie. 123d)");
      //in editing method
      String editQ="#"+scan.nextLine();
      if (questionExists(editQ)){
        edit(editQ);
      }
      else {
        System.out.println("Sorry, I did not recognize your answer. " );
          finalTouch();
      }
    }
    else if (ans.equals("n") || ans.equals("N") ){
      System.out.println("WARNING MESSAGES BASED ON YOUR ANSWERS ");
      int warnCount=0;
      System.out.println("-----");
      if (getData("#2b")!=0 ){
        warnCount++;
        System.out.println("! Based on your answer to #2b, consider attaching a Schedule B to your 1040 Form.");
      }
      if (getData("#3b")!=0 ){
        warnCount++;
        System.out.println("! Based on your answer to #3b, consider attaching a Schedule B to your 1040 Form.");
      }
      if (getData("#7")==null || getData("#7")==0){
        warnCount++;
        System.out.println("! Based on your answer to #7, consider attaching a Schedule D to your 1040 Form.");
      }
      else {
        warnCount++;
        System.out.println("! Based on your answer to #7, consider checking a checkbox in #7 of your 1040 Form.");
      }
      if (getData("#12")!=0 ){
        warnCount++;
        System.out.println("! Based on your answer to #12, consider attaching Form 8995 -OR- Form 8995-A to your 1040 Form.");
      }
      if (getData("#16")!=0 ){
        warnCount++;
        System.out.println("! Based on your answer to #16, consider checking a checkbox in #16 of your 1040 Form.");
      }
      if (getData("#28")!=0 ){
        warnCount++;
        System.out.println("! Based on your answer to #28, consider attaching Form 8812 to your 1040 Form.");
      }

      if (warnCount>0){
        System.out.println("Wow, you have "+ warnCount + " warning messages!!!");
      }
      System.out.println("Perfect! Good luck filing your taxes :) ");
    }
    else{
      System.out.println("Sorry, I did not recognize your answer. " );
        finalTouch();
      }
    }

    /**
     * Changes the answer in the hashmap and InfoBank instance
     * @param s Question number associated with answer to be changed
     */
    public void edit(String s){
      Scanner scan= new Scanner(System.in);
      for (int i=0; i<questionAns.size();i++){
        if (questionAns.get(i).question.equals(s)){
          System.out.println("Input the new answer for question "+ s + " : ");
          long newAns=scan.nextLong();
          questionAns.get(i).data=newAns;
          hash.put(s, newAns);
        }
      }
      System.out.println("Here's your updated information : ");
      System.out.println(hash.entrySet());
      finalTouch();
    }

    /**
     * Checks if the question number input by user is valid
     * @param  s Question number with answer to be changed
     * @return   True if question number is a valid question
     */
    public boolean questionExists(String s){
      for (int i=0; i<questionAns.size();i++){
        if (questionAns.get(i).question.equals(s)){
          return true;
        }
      }
      return false;
    }

  public static void main(String[] args){
    Tax1040 test=new Tax1040();
  }
}
