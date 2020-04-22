/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputVerification {
    
    
    
    
    public  InputVerification() throws SQLException, IOException{
        
    }


    public boolean verifyEmails(String emailText) {

        try {

            String emailPatternForPersonalAccountsEmails = "(([a-zA-Z0-9]+)(@)([a-zA-Z]+)([.]+)([a-zA-Z]{2,3}+))";
            String emailPatternForInstitutionDormainsEmails = "([a-zA-Z0-9]+)([.]+)([a-zA-Z0-9]+)(@)([a-zA-Z]+)([.]+)([a-zA-Z]+)([.]+)([a-zA-Z]{2}+)([.]+)([a-zA-Z]{2}+)";
            String emailPatternForWebHostingServerSMTPEmails = "([a-zA-Z]+)(@)([a-zA-Z]+)([.]+)([a-zA-Z]+)([.]+)([a-zA-Z]{2}+)([.]+)([a-zA-Z]{2}+)";
            String emailPatternForWeblevelTowSMTPEmails = "([a-zA-Z]+)(@)([a-zA-Z]+)([.]+)([a-zA-Z]+)([.]+)([a-zA-Z]{2}+)";
            String emailPatternForInstitutionDormainsEmails2 = "([a-zA-Z0-9]+)(@)([a-zA-Z]+)([.]+)([a-zA-Z]+)([.]+)([a-zA-Z]+)([.]+)([a-zA-Z]{2}+)([.]+)([a-zA-Z]{2}+)";
            
            
            Pattern pp1 = Pattern.compile(emailPatternForPersonalAccountsEmails);
            Matcher m1 = pp1.matcher(emailText);
            Pattern pp2 = Pattern.compile(emailPatternForInstitutionDormainsEmails);
            Matcher m2 = pp2.matcher(emailText);
            Pattern pp3 = Pattern.compile(emailPatternForWebHostingServerSMTPEmails);
            Matcher m3 = pp3.matcher(emailText);
            Pattern pp4 = Pattern.compile(emailPatternForWeblevelTowSMTPEmails);
            Matcher m4 = pp4.matcher(emailText);
            Pattern pp5 = Pattern.compile(emailPatternForInstitutionDormainsEmails2);
            Matcher m5 = pp5.matcher(emailText);

            return m1.matches() || m2.matches() || m3.matches()|| m4.matches() || m5.matches();

        } catch (Exception e) {
            System.out.println("ERR: " + e.getMessage());
            return false;
        }

    }

    public boolean verifyNumbersOnlyFields(Object enteredString) {
        
        Pattern numbersOnlyPattern = Pattern.compile("\\d*");
        Matcher numbersOnlyMatcher = numbersOnlyPattern.matcher(enteredString.toString());
        return numbersOnlyMatcher.matches();
    }

    public boolean verifyPasswordStrength(final String enteredPassword) {

        String passwordStrength = "";
        String message = "";
        Pattern passwordRegex = Pattern.compile("[a-zA-Z]+");
        Pattern passwordRegexLevelTwo = Pattern.compile("[0-9]+");
        Matcher passwordStrengthMatcher = passwordRegex.matcher(enteredPassword);
        Matcher passwordStrengthMatcherLevelTwo = passwordRegexLevelTwo.matcher(enteredPassword);

        if (passwordStrengthMatcher.find() && passwordStrengthMatcherLevelTwo.find()) {

            passwordStrength = "100% STRONG!!";
            return  true;

        } else if (!passwordStrengthMatcher.find() && !passwordStrengthMatcherLevelTwo.find()) {
          
            passwordStrength = "4% WEAK";
            message = "Upper case and lowercase characters required!";
            return false;

        } else if (!passwordStrengthMatcher.find()) {
           
            passwordStrength = "40% WEAK!!";
            message = "Upper case and lowercase characters required!";
            return false;

        } else if (!passwordStrengthMatcherLevelTwo.find()) {

            passwordStrength = "60%";
            message = "A digit between 0-9 required";
            return false;
        }else{
            return false;
        }

    }

    public  boolean verifyTextOnlyFields(Object firstNames) {

        Pattern textOnlyPattern = Pattern.compile("[a-zA-Z' ]+");
        Matcher textOnlyMatcher = textOnlyPattern.matcher(firstNames.toString());
        return textOnlyMatcher.matches();
    }

    public boolean passwordConfirmation(String enteredPassword, String confirmedPassword) {

        return enteredPassword.equals(confirmedPassword);

    }

    public boolean verifiyNumbersWithTex(Object ID) {
        Pattern IDPattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher IDMatcher = IDPattern.matcher(ID.toString());
        return IDMatcher.matches();

    }

    public boolean verifyWebAddress(Object webAddress) {
        Pattern webAddressPattern = Pattern.compile("[a-zA-Z0-9:\\.//]*");
        Matcher webAddressMatcher = webAddressPattern.matcher(webAddress.toString());
        return webAddressMatcher.matches();
    }

    public boolean verifyAddress(Object physicalAddress) {
        Pattern addressPattern = Pattern.compile("[a-zA-Z0-9 ]*");
        Matcher addressMatcher = addressPattern.matcher(physicalAddress.toString());
        return addressMatcher.matches();
    }
    
    public boolean verifyNamesWithInitials(Object nameWithInitials){
        Pattern fullNamesWithInititlsPattern = Pattern.compile("[a-zA-Z\\. ]*");
        Matcher fullNamesWithInitialsMatcher = fullNamesWithInititlsPattern.matcher(nameWithInitials.toString());
        return fullNamesWithInitialsMatcher.matches();
    }
    public boolean verifyAmount(Object CashEntries){
        Pattern cashEntriesPattern = Pattern.compile("[0-9]*[?.][00]");
        Matcher cashEntriesMatcher = cashEntriesPattern.matcher(CashEntries.toString());
        return cashEntriesMatcher.matches();
        
    }
    
}
