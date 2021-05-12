package com.springboot.propelrr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RESTAPI {

    String fullname, email, mobile, gender, birthdate;
    List<String> errors = new ArrayList<>();


    public RESTAPI(String fullname, String email, String mobile, String birthdate, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.birthdate = birthdate;
        this.gender = gender;
        doValidate();

    }

    private void doValidate()
        {

            Validate("^[A-Za-z ,.]+$", this.fullname, "Full name is missing or invalid format.");
            Validate("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", this.email, "Email address is missing or invalid."); // Using RFC 5322 Standard
            Validate("^(09|\\+639)\\d{9}$", this.mobile, "Mobile must be valid 11 digit Philippine Mobile Number.Value starts with 09 or +639");
            Validate("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", this.birthdate, "Birth Date is required and must follow YYYY-mm-dd format");
            Validate("^M(ale)?$|^F(emale)?$", this.gender, "Gender is required. Value accepted is Male or Female or M or F");

        }
        
   
    private void Validate(String regex, String reference, String error) {
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(reference);
    
        if (this.isNullEmpty(reference) || !matcher.find())
            errors.add(error);
    }


    private boolean isNullEmpty(String str) {

        // check if string is null
        if (str == null) {
            return true;
        }

        // check if string is empty
        else if (str.isEmpty()) {
            return true;
        }

        else {
            return false;
        }
    }

    public int getAge()
        {
            LocalDate bdate =  LocalDate.of(Integer.parseInt(this.birthdate.substring(0, 4)), Integer.parseInt(this.birthdate.substring(5, 7)), Integer.parseInt(this.birthdate.substring(8, 10)));
            var today = LocalDate.now();
            int age = today.getYear() - bdate.getYear();
            return age;
        }

    public List<String> GetErrors() {
        return errors;
    }
    
}
