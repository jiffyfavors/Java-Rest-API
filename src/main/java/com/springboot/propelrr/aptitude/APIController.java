package com.springboot.propelrr.aptitude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.springboot.propelrr.RESTAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/fibo")
    @ResponseBody
    public String index(@RequestParam int range) {
        if (range > 20 || range < 1)
            return "Range Error: Please set range between 1 and 20";
        else
            return Fibo(range);
    }

    @PostMapping("/sort")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> index(@RequestBody Map<String, Object> payload) {

        int[] numbers = ((Collection<Integer>) payload.get("params")).stream().mapToInt(i -> i).toArray();
        BubbleSort sort = new BubbleSort(numbers);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("original", sort.getOriginal());
        response.put("sorted", sort.getSortedValue());
        response.put("largest", sort.getLargest());
        response.put("median", sort.getMedian());

        return ResponseEntity.ok(response);

    }


    @GetMapping("/all")
    public @ResponseBody Iterable<Profile> getAllUsers() {
        // This returns a JSON or XML with the users
        return profileRepository.findAll();
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> API(@RequestBody Map<String, Object> payload) {
       
                String fullname = (String) payload.get("fullname");
                String email = (String) payload.get("email");
                String mobile = (String) payload.get("mobile");
                String birthdate = (String) payload.get("birthdate");
                String gender = (String) payload.get("gender");


                RESTAPI rest = new RESTAPI(fullname, email, mobile, birthdate, gender);

        Map<String, Object> response = new HashMap<String, Object>();
        if(rest.GetErrors().size()>0){
            response.put("message","Errors Found. Please see error list.");     
            response.put("errors", rest.GetErrors().toArray(new String[0]));
            response.put("status", 400);

        }
        else{    
            //No Errors Save to MySQL 
            Profile profile = new Profile();
            profile.setFullname(fullname);
            profile.setEmail(email);
            profile.setMobile(mobile);
            profile.setGender(gender);
            profile.setBirthDate(birthdate);
            profile.setAge(rest.getAge());  
            profileRepository.save(profile);
            response.put("message", "Data saved to MySQL Database");
            response.put("status", 200);

        }
        return ResponseEntity.ok(response);
        
    }

    private String Fibo(int range) {
        int n1 = 0, n2 = 1, next;
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            seq.add(n1);
            next = n1 + n2;
            n1 = n2;
            n2 = next;
        }
        return seq.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

}
